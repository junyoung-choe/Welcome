import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

import { userConfirm, findById, tokenRegeneration, logout, userMyPage } from "@/api/user";
import { httpStatusCode } from "@/util/http-status";

export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter();

  const isLogin = ref(false);
  const isLoginError = ref(false);
  const userInfo = ref(null);
  const isValidToken = ref(false);

  /// 패키저 확인
  const isAgency = ref(false);

  // const agencyConfirm = async (userid) => {
  //   await userMyPage(
  //     userid,
  //     (response) => {
  //       if (response.status === httpStatusCode.CREATE && response.resdata.user_role === "agency") {
  //         isAgency.value = true;
  //       } else {
  //         isAgency.value = false;
  //       }
  //     },
  //     (error) => {
  //       console.error(error);
  //       // 에러시 다시 로그인 화면으로 보낸다
  //       router.push({ path: '/user/login' })
  //     }
  //   )
  // }

  const trueAgency = () => {
      isAgency.value = true;
  }
  const falseAgency = () => {
      isAgency.value = false;

  }
  ///

    const userLogin = async (loginUser) => {
      // login 홈페이지에서 호출되어 userConfirm (api/user 에 있는) 를 호출하여 서버에게 login 으로 post 보내본다 ! 
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let { data } = response;
          let accessToken = data["access-token"];
          let refreshToken = data["refresh-token"];
          isLogin.value = true;
          isLoginError.value = false;
          isValidToken.value = true;
          sessionStorage.setItem("accessToken", accessToken);
          sessionStorage.setItem("refreshToken", refreshToken);
        } else {
          isLogin.value = false;
          isLoginError.value = true;
          isValidToken.value = false;
        }
      },
      (error) => {
        console.error(error);
        // 에러시 다시 로그인 화면으로 보낸다
        router.push({ path: '/user/login' })
      }
    );
  };

  const getUserInfo = (token) => {
    // login 과정에서 여기가 호출되고
    let decodeToken = jwtDecode(token);
    findById(
      // user 의 pk 가 들어있고 이 pk 가지고 axios 로 info 호출을 보내서 해당 유저의 객체를 받아온다
      decodeToken.userId,
      (response) => {
        // info 로 다녀왔을때 상태가 ok 로 전달 받았다면
        if (response.status === httpStatusCode.OK) {
          // 가져온 객체를 userInfo에 담는다 (위에 선언 되어있다)
          userInfo.value = response.data.userInfo;
        } else {
          console.log("유저 정보 없음!!!!");
          // 다시 로그인 페이지로 보내야 한다
          router.push({ path: '/user/login' })
        }
      },
      async (error) => {
        // 서버에서 에러를 던져버렸고 즉 토큰이 유효하지 않다는것 
        console.error(
          "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
          error.response.status
        );
        isValidToken.value = false;

        // 그러면 refresh 는 유효한지 확인하고  있으면 acess로 다시 들어온다 
        await tokenRegenerate();
      }
    );
  };

  const tokenRegenerate = async () => {
    await tokenRegeneration(
      JSON.stringify(userInfo.value),
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let accessToken = response.data["access-token"];
          sessionStorage.setItem("accessToken", accessToken);
          isValidToken.value = true;
        }
      },
      async (error) => {
        // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
        if (error.response.status === httpStatusCode.UNAUTHORIZED) {
          // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
          await logout(
            userInfo.value.userid,
            (response) => {
              if (response.status === httpStatusCode.OK) {
                console.log("리프레시 토큰 제거 성공");
              } else {
                console.log("리프레시 토큰 제거 실패");
              }
              alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
              isLogin.value = false;
              userInfo.value = null;
              isValidToken.value = false;
              router.push({ name: "user-login" });
            },
            (error) => {
              console.error(error);
              isLogin.value = false;
              userInfo.value = null;
            }
          );
        }
      }
    );
  };

  // 여기서 Json 호출한다 즉 외부에서는 이 함수를 호출하면 된다
  const userLogout = async (userid) => {
    await logout(
      userid,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          isLogin.value = false;
          userInfo.value = null;
          isValidToken.value = false;
        } else {
          console.error("유저 정보 없음!!!!");
        }
      },
      (error) => {
        console.log(error);
      }
    );
  };

  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    falseAgency,
    // agencyConfirm,
    trueAgency,
    userLogin,
    getUserInfo,
    tokenRegenerate,
    userLogout,
  };
});

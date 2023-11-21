<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  user_account: "",
  user_password: "",
});

const login = async () => {
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");

  // 토큰을 발급 받았다 완료 !
  if (isLogin) {
    // vue 에서 해당 회원의 정보를 알기 위해서 가져온다
    // access 만료되면 refresh 까지 시도하고 온다
    getUserInfo(token);
    // 로그인이 됬으니 화면 로그인 된걸로 바꿔 (navi 부분)
    console.log("로그인 됬으니까 정보 바꿔 녀석아");
    changeMenuState();
    // 그리고 그 이후에는 router 에서 before 에서 로그인 체크하는것 !
    // 로그인 안되있으면 또 로그인 화면 가야해
  }
  router.push("/");
};

const goRegist = () => {
  router.push({
    path: "join",
  });
};
</script>

<template>
  <div class="main">
    <div class="login-box">
      <p class="welcome">Welcome</p>
      <div class="">
        <form>
          <!-- <div class="form-check mb-3 float-end">
            <input class="form-check-input" type="checkbox" />
            <label class="form-check-label" for="saveid"> 아이디저장 </label>
          </div> -->
          <div class="">
            <!-- <label for="userid" class="form-label">아이디 : </label> -->
            <input type="text" v-model="loginUser.user_account" placeholder="ID" />
          </div>
          <div class="">
            <!-- <label for="userpwd" class="form-label">비밀번호 : </label> -->
            <input
              type="password"
              v-model="loginUser.user_password"
              @keyup.enter="login"
              placeholder="Password"
            />
          </div>
          <div>
            <button type="button" class="login-btn" @click="login">LOGIN</button>
          </div>
          <div class="regist-box">
            <span>Don't have account? </span>
            <button type="button" class="regist-btn" @click="goRegist">Click Here</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.login-box {
  margin-top: 100px;
  padding: 30px;
  background-color: white;
  border-radius: 5px;
  box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.welcome {
  font-size: 30px;
  font-weight: 900;
  margin: 40px;
}

input {
  outline: none;
  border: none;
  border-radius: 0px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  padding: 10px;
  width: 260px;
  margin-top: 20px;
}

input:focus {
  border-bottom: 2px solid #2184d6;
}

.login-btn {
  border-radius: 20px;
  width: 200px;
  margin-top: 30px;
  border: none;
  background: linear-gradient(-225deg, #7de2fc 0%, #b9b6e5 100%);
  padding: 7px;
  color: white;
  font-size: 20px;
  font-weight: 900;
}

.regist-box {
  margin-top: 50px;
}

.regist-btn {
  border: none;
  background-color: white;
  color: blue;
}
</style>

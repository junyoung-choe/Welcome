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
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">로그인</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <form>
          <div class="form-check mb-3 float-end">
            <input class="form-check-input" type="checkbox" />
            <label class="form-check-label" for="saveid"> 아이디저장 </label>
          </div>
          <div class="mb-3 text-start">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              type="text"
              class="form-control"
              v-model="loginUser.user_account"
              placeholder="아이디..."
            />
          </div>
          <div class="mb-3 text-start">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input
              type="password"
              class="form-control"
              v-model="loginUser.user_password"
              @keyup.enter="login"
              placeholder="비밀번호..."
            />
          </div>
          <div class="col-auto text-center">
            <button
              type="button"
              class="btn btn-outline-primary mb-3"
              @click="login"
            >
              로그인
            </button>
            <button
              type="button"
              class="btn btn-outline-success ms-1 mb-3"
              @click="goRegist"
            >
              회원가입
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

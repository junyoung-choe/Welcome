<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

import { userRegister } from "@/api/user";

const router = useRouter();

const loginUser = ref({
  user_name: "",
  user_account: "",
  user_password: "",
  user_phone: "",
});

const join = async () => {
  await userRegister(loginUser.value);
  router.push("/user/login");
};
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <form>
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input
              type="text"
              class="form-control"
              v-model="loginUser.user_name"
              placeholder="이름..."
            />
          </div>
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              type="text"
              class="form-control"
              v-model="loginUser.user_account"
              placeholder="아이디..."
            />
          </div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input
              type="password"
              class="form-control"
              v-model="loginUser.user_password"
              placeholder="비밀번호..."
            />
          </div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">핸드폰번호 : </label>
            <input
              type="text"
              class="form-control"
              v-model="loginUser.user_phone"
              placeholder="핸드폰번호..."
            />
          </div>

          <!-- <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" v-model="loginUser.user_password" id="pwdcheck" placeholder="비밀번호확인..." />
          </div> -->
          <!-- <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input type="text" class="form-control" v-model="loginUser.user_password" placeholder="이메일아이디" />
              <span class="input-group-text">@</span>
              <select class="form-select" aria-label="이메일 도메인 선택">
                <option selected>선택</option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
              </select>
            </div>
          </div> -->

          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-primary mb-3" @click="join">
              회원가입
            </button>
            <button type="button" class="btn btn-outline-success ms-1 mb-3">초기화</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

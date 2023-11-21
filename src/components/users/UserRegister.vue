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

const goLogin = () => {
  router.push({
    path: "login",
  });
};
</script>

<template>
  <div class="main">
    <div class="regist-box">
      <p class="welcome">Welcome</p>
      <div class="">
        <form>
          <div class="">
            <input type="text" class="" v-model="loginUser.user_name" placeholder="Name" />
          </div>
          <div class="">
            <input type="text" class="" v-model="loginUser.user_account" placeholder="ID" />
          </div>
          <div class="">
            <input
              type="password"
              class=""
              v-model="loginUser.user_password"
              placeholder="Password"
            />
          </div>
          <div class="">
            <input type="text" class="" v-model="loginUser.user_phone" placeholder="PhoneNumber" />
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
          <div>
            <button type="button" class="regist-btn" @click="join">Register</button>
          </div>
          <div class="login-box">
            <span>Already have an account? </span>
            <button type="button" class="login-btn" @click="goLogin">Click Here</button>
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

.regist-box {
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

.regist-btn {
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

.login-box {
  margin-top: 50px;
}

.login-btn {
  border: none;
  background-color: white;
  color: blue;
}
</style>

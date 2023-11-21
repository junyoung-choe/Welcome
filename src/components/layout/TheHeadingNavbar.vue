<script setup>
import { ref, onMounted, computed } from "vue";
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { jwtDecode } from "jwt-decode";

// 상단바의 로그인 로그아웃을 위해서
const memberStore = useMemberStore();

const menuStore = useMenuStore();
const { userLogout } = memberStore;

const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const isLogout = ref({});

onMounted(() => {
  let token = sessionStorage.getItem("accessToken");
  // 토큰이 존재한다면 -> 새로고침 시에도 로그인이 유지 되야한다
  if (token != null) {
    menuList.value[0].show = false;
    menuList.value[1].show = false;
    menuList.value[2].show = true;
    menuList.value[3].show = true;
  }
  console.log("======================");
  console.log(menuList.value);
  isLogout.value = menuList.value[3].show;
  console.log(isLogout.value);
});

isLogout.value = computed(() => {
  return menuList.value[3].show == true ? true : false;
});

// 로그아웃
// const logoutButton = document.getElementById("logoutButton");

// if (logoutButton) {
//   logoutButton.addEventListener("click", () => {
//     logout(); // 클릭되었을 때 logout 함수 호출
//   });
// }

const logout = async () => {
  let token = sessionStorage.getItem("accessToken");
  let decodeToken = jwtDecode(token);
  await userLogout(decodeToken.userId);
  console.log("로그아웃!!!!");
  changeMenuState();
  sessionStorage.removeItem("accessToken");
  sessionStorage.removeItem("refreshToken");
  // 로그아웃 후에 로그인 화면으로 이동한다
};
// 로그아웃
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
    <div class="container-fluid">
      <router-link :to="{ name: 'main' }" class="navbar-brand">
        <h1>Logo</h1>
      </router-link>
      <div class="collapse navbar-collapse" id="navbarScroll">
        <ul
          class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item">
            <router-link to="/board" class="nav-link">소통</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/map" class="nav-link">요즘국내여행</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/tour" class="nav-link">여행상품</router-link>
          </li>
        </ul>

        <!-- <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item">
            <router-link to="/user/login">로그인</router-link>
          </li>
          <div style="width: 10px"></div>
          <li class="nav-item">
            <router-link to="/user/mypage">마이페이지</router-link>
          </li> -->
        <!-- <li class="nav-item" id="logoutButton">로그아웃</li> -->

        <!-- <button v-if="isLogout.value" class="nav-item" id="logoutButton" @click="logout">
            로그아웃
          </button>
        </ul> -->
        <!-- {{ menuList[0] }}
        {{ menuList[1] }}
        {{ menuList[2] }}
        {{ menuList[3] }} -->
        <template v-for="menu in menuList" :key="menu.routeName">
          <template v-if="menu.show">
            <template v-if="menu.routeName === 'user-logout'">
              <router-link to="/" @click.prevent="logout" class="nav-link">
                {{ menu.name }}
              </router-link>
            </template>
            <template v-else>
              <router-link :to="{ name: menu.routeName }" class="nav-link">
                {{ menu.name }}
              </router-link>
            </template>
          </template>
        </template>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>

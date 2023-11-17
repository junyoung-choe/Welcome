<script setup>
import { useMenuStore } from '@/stores/menu';
import { storeToRefs } from 'pinia';

const menuStore = useMenuStore();

const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const logout = () => {
  console.log('로그아웃!!!!');
  changeMenuState();
};
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
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              HELP DESK
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">공지사항</a></li>
              <li><a class="dropdown-item" href="#">FAQ</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#">학사규정</a></li>
            </ul>
          </li>
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
        <!-- <form class="d-flex" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="검색..."
            aria-label="Search"
          />
          <button class="btn btn-outline-success" type="button">search</button>
        </form> -->
        <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <!-- <router-link to="/" @click.prevent="logout" class="nav-link">{{
                    menu.name
                  }}</router-link> -->
                  로그인
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <!-- <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                    menu.name
                  }}</router-link> -->
                  회원가입
                </li>
              </template>
            </template>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>

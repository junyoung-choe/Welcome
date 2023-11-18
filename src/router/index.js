import { createRouter, createWebHistory } from 'vue-router';
import TheMainView from '../views/TheMainView.vue';

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "user-login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: TheMainView,
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
      ],
    },
    {
      path: '/board',
      name: 'board',
      component: () => import('../views/TheBoardView.vue'),
      redirect: { name: 'board-list' },
      children: [
        {
          path: 'list',
          name: 'board-list',
          component: () => import('../components/board/BoardList.vue'),
        },
        {
          path: 'view/:board_id',
          name: 'board-view',
          component: () => import('../components/board/BoardView.vue'),
        },
        {
          path: 'modify/:board_id',
          name: 'board-modify',
          component: () => import('../components/board/BoardModify.vue'),
        },
        {
          path: 'write',
          name: 'board-write',
          component: () => import('../components/board/BoardWrite.vue'),
        },
      ],
    },
    {
      path: '/map',
      name: 'map',
      component: () => import('../views/TheMapView.vue'),
    },
    {
      path: '/tour',
      name: 'tour',
      component: () => import('../views/TheTourView.vue'),
      redirect: { name: 'tour-list' },
      children: [
        {
          path: 'list',
          name: 'tour-list',
          component: () => import('../components/tour/TourBoardList.vue'),
        },
        {
          path: 'write',
          name: 'tour-wirte',
          component: () => import('../components/tour/TourBoardWrite.vue'),
        },
      ],
    },
  ],
});

export default router;

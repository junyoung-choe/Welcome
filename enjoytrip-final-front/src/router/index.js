import { createRouter, createWebHistory } from 'vue-router';
import TheMainView from '../views/TheMainView.vue';

import { storeToRefs } from 'pinia';
import { useMemberStore } from '@/stores/member';

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem('accessToken');
  console.log(token);
  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  console.log(userInfo.value, isValidToken.value);
  // if (!isValidToken.value && userInfo.value === null) {
  // 임시 방편
  if (token === null) {
    console.log('로그인 화면으로 가자');
    next({ name: 'user-login' });
  } else {
    console.log(userInfo.value, isValidToken.value);
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
      path: '/user',
      name: 'user',
      component: () => import('@/views/TheUserView.vue'),
      children: [
        {
          path: 'login',
          name: 'user-login',
          component: () => import('@/components/users/UserLogin.vue'),
        },
        {
          path: 'agencyLogin',
          name: 'agency-login',
          component: () => import('@/components/users/AgencyLogin.vue'),
        },
        {
          path: 'join',
          name: 'user-join',
          component: () => import('@/components/users/UserRegister.vue'),
        },
        {
          path: 'AgencyJoin',
          name: 'agency-join',
          component: () => import('@/components/users/AgencyRegister.vue'),
        },
        {
          path: 'mypage',
          name: 'user-mypage',
          beforeEnter: onlyAuthUser,
          component: () => import('@/components/users/UserMyPage.vue'),
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
          beforeEnter: onlyAuthUser,
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
      path: '/gpt',
      name: 'gpt',
      component: () => import('../components/gpt/gptTest.vue'),
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
        {
          path: 'toursearchresult/:depa/:dest',
          name: 'tour-search',
          component: () => import('../components/tourMain/TourSearchResult.vue'),

          beforeEnter: async (to, next) => {
            const depa = to.params.depa;
            const dest = to.params.dest;

            console.log(depa);

            if (depa == 'undefined' || dest == 'undefined') {
              console.log('asdasdasd');
              alert('출발지 또는 도착지를 입력해 주세요');
              next({ name: 'main' });
            }
          },
        },
        {
          path: 'detail/:tourboard_id',
          name: 'tour-detail',
          component: () => import('../components/tour/TourBoardView.vue'),
        },
        {
          path: 'AIlist',
          name: 'ai-list',
          component: () => import('../components/tourMain/AiTourList.vue'),
        },
      ],
    },
  ],
});

export default router;

import { createRouter, createWebHistory } from 'vue-router';
import TheMainView from '../views/TheMainView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: TheMainView,
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
  ],
});

export default router;

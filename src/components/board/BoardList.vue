<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { listArticle } from '@/api/board.js';

import BoardListItem from '@/components/board/item/BoardListItem.vue';
const router = useRouter();

const boards = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: '',
  word: '',
});

onMounted(() => {
  getArticleList();
});

const getArticleList = () => {
  console.log('서버에서 글목록 얻어오자!!!', param.value);
  listArticle(
    param.value,
    ({ data }) => {
      boards.value = data.boards;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div>
    <BoardListItem v-for="board in boards" :key="board.board_id" :board="board" />
  </div>
</template>

<style scoped></style>

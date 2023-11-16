<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { detailArticle, deleteArticle } from '@/api/board';

const route = useRoute();
const router = useRouter();

const { board_id } = route.params;
const board = ref({});

onMounted(() => {
  console.log(board_id);
  getBoard();
});

const getBoard = () => {
  // const { articleno } = route.params;
  console.log(board_id + '번글 얻으러 가자!!!');
  detailArticle(
    board_id,
    ({ data }) => {
      board.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: 'board-list' });
}

function moveModify() {
  router.push({ name: 'board-modify', params: { board_id } });
}

function onDeleteArticle() {
  // const { articleno } = route.params;
  console.log(board_id + '번글 삭제하러 가자!!!');
  deleteArticle(
    board_id,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      console.log(error);
    }
  );
}
</script>

<template>
  <div>{{ board }}</div>
  <div class="d-flex justify-content-end">
    <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">글목록</button>
    <button type="button" class="btn btn-outline-success mb-3 ms-1" @click="moveModify">
      글수정
    </button>
    <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="onDeleteArticle">
      글삭제
    </button>
  </div>
</template>

<style scoped></style>

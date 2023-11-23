<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/board.js";

import VSelect from "@/components/common/VSelect.vue";
import BoardListItem from "@/components/board/item/BoardListItem.vue";
import VPageNavigation from "@/components/common/VPageNavigation.vue";
const router = useRouter();

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "글번호", value: "board_id" },
  { text: "제목", value: "board_title" },
  { text: "작성자아이디", value: "user_id" },
]);

const boards = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getArticleList();
});

const changeKey = (val) => {
  console.log("BoarList에서 선택한 조건 : " + val);
  param.value.key = val;
};

const getArticleList = () => {
  console.log("서버에서 글목록 얻어오자!!!", param.value);
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

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getArticleList();
};

const moveWrite = () => {
  router.push({ name: "board-write" });
};
</script>

<template>
  <div class="col-md-5 offset-7 search-box">
    <form class="d-flex" @submit.prevent="getArticleList">
      <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
      <div class="input-group input-group-sm ms-1">
        <input type="text" class="form-control" v-model="param.word" placeholder="검색어" />
        <button class="btn btn-dark" type="submit">검색</button>
      </div>
    </form>
  </div>

  <table class="table">
    <thead>
      <tr class="text-center">
        <th scope="col">글번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">등록일</th>
        <th scope="col">조회수</th>
      </tr>
    </thead>
    <tbody>
      <BoardListItem v-for="board in boards" :key="board.board_id" :board="board" />
    </tbody>
  </table>
  <div style="text-align: right">
    <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">글쓰기</button>
  </div>
  <VPageNavigation
    :current-page="currentPage"
    :total-page="totalPage"
    @pageChange="onPageChange"
  ></VPageNavigation>
</template>

<style scoped>
.table {
  width: 100%;
  border-top: 1px solid black;
}

.search-box {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>

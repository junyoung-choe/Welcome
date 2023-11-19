<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listTourBoard } from "@/api/tourboard.js";

import TourBoardListItem from "@/components/tour/item/TourBoardListItem.vue";
import VPageNavigation from "@/components/common/VPageNavigation.vue";

const router = useRouter();

const tourBoards = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_TOURBOARD_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_TOURBOARD_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getTourBoardList();
});

const getTourBoardList = () => {
  console.log("서버에서 글목록 얻어오자!!!", param.value);
  listTourBoard(
    param.value,
    ({ data }) => {
      console.log(data);
      tourBoards.value = data.tourboards;
      console.log(tourBoards.value);
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log("error");
      console.log(error);
    }
  );
};

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getTourBoardList();
};

const sortListCheap = () => {
  tourBoards.value.sort(function (a, b) {
    return a.tourboard_salePrice - b.tourboard_salePrice;
  });
};
</script>

<template>
  <div class="main">
    <div class="section-left">
      <form class="d-flex" @submit.prevent="getTourBoardList">
        <div class="input-group input-group-sm ms-1">
          <input
            type="text"
            class="form-control"
            v-model="param.word"
            placeholder="키워드검색"
          />
          <button class="btn btn-dark" type="submit">검색</button>
        </div>
      </form>
      <button @click="sortListCheap">낮은가격순</button>
    </div>
    <div class="section-right">
      <div>
        <TourBoardListItem
          v-for="tourBoard in tourBoards"
          :key="tourBoard.tourboard_id"
          :tourBoard="tourBoard"
        />
      </div>
      <VPageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @pageChange="onPageChange"
      ></VPageNavigation>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
}
.section-left {
  flex-grow: 1;
}

.section-right {
  flex-grow: 1;
}
</style>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { listTourSerachBoard } from "@/api/tourboard";

import TourBoardListItem from "@/components/tour/item/TourBoardListItem.vue";

const route = useRoute();
const router = useRouter();
const { VITE_TOURBOARD_LIST_SIZE } = import.meta.env;
const cnt = ref(10);
const curCnt = ref(10);

const tourList = ref([]);
const tourListView = ref([]);

const { depa, dest } = route.params;

const param = {
  depa: depa,
  dest: dest,
};

onMounted(() => {
  getTourSearchResultList();
  console.log("===========================");
  console.log(tourList.value);
  console.log(tourListView.value);
});

const getTourSearchResultList = () => {
  listTourSerachBoard(
    param,
    ({ data }) => {
      console.log(data);
      tourList.value = data;
      for (let index = 0; index < cnt.value; index++) {
        if (tourList.value[index] == undefined) {
          break;
        }
        tourListView.value.push(tourList.value[index]);
      }
      console.log(tourListView.value);
    },
    (error) => {
      console.log(error);
    }
  );
};

const sortListCheap = () => {
  tourList.value.sort(function (a, b) {
    return a.tourboard_salePrice - b.tourboard_salePrice;
  });
  tourListView.value.length = 0;
  for (let index = 0; index < cnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
};

const sortListExpensive = () => {
  tourList.value.sort(function (a, b) {
    return b.tourboard_salePrice - a.tourboard_salePrice;
  });
  tourListView.value.length = 0;
  for (let index = 0; index < cnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
};

const getMore = () => {
  if (curCnt.value >= tourList.value.length) {
    alert("마지막 입니다!");
    return;
  }

  for (let index = curCnt.value; index < cnt.value + curCnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
  curCnt.value += cnt.value;
};
</script>

<template>
  <div class="main">
    <div class="section-left">
      <div class="box">
        <div class="btn-box">
          <button @click="sortListCheap">낮은가격순</button>
          <button @click="sortListExpensive">높은가격순</button>
          <button @click="sortListExpensive">평점순</button>
          <button @click="sortListExpensive">예약순</button>
        </div>
      </div>
    </div>
    <div class="section-right">
      <div>
        <p>{{ depa }} -> {{ dest }}</p>
        <TourBoardListItem
          v-for="tour in tourListView"
          :key="tour.tourboard_id"
          :tourBoard="tour"
        />
      </div>
      <div class="more-btn-box">
        <button @click="getMore">더보기▽</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
}
.section-left {
  flex-grow: 1;
  padding-top: 10px;
  width: 250px;
}
.section-right {
  flex-grow: 1;
}
.more-btn-box {
  text-align: center;
}
.more-btn-box button {
  width: 100px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  background-color: rgb(255, 249, 237);
}
button:hover {
  background-color: rgba(0, 0, 0, 0.2) !important;
}

.btn-box {
  display: flex;
  flex-direction: column;
}

.btn-box button {
  background-color: white;
  border-radius: 5px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  color: rgba(0, 0, 0, 0.8);
  margin-top: 10px;
}

.btn {
  background-color: rgba(0, 0, 0, 0.2);
  border: 1px solid rgba(0, 0, 0, 0.2);
  color: rgba(0, 0, 0, 0.7);
}
.box {
  position: fixed;
  top: 140px;
  left: 10px;
  width: 200px !important;
}
</style>

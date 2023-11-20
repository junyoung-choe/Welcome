<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { listTourSerachBoard } from '@/api/tourboard';

import TourBoardListItem from '@/components/tour/item/TourBoardListItem.vue';

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
});

const getTourSearchResultList = () => {
  listTourSerachBoard(
    param,
    ({ data }) => {
      tourList.value = data;
      for (let index = 0; index < cnt.value; index++) {
        tourListView.value.push(tourList.value[index]);
      }
      console.log(tourListView.value);
    },
    (error) => {
      console.log(error);
    }
  );
};

const getMore = () => {
  for (let index = curCnt.value; index < cnt.value + curCnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
  curCnt.value += cnt.value;
  console.log(tourListView.value);
};
</script>

<template>
  <div>
    <p>{{ depa }} -> {{ dest }}</p>
    <TourBoardListItem v-for="tour in tourListVdiew" :key="tour.tourboard_id" :tourBoard="tour" />
  </div>
  <div class="btn-box">
    <button @click="getMore">더보기</button>
  </div>
</template>

<style scoped>
.btn-box {
  text-align: center;
}
.btn-box button {
  width: 100px;
  border: none;
  background-color: #cff0fa;
}
</style>

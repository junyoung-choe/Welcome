<script setup>
import { ref, onMounted } from 'vue';
import { getCheapList } from '@/api/tourboard.js';

import TourListInMainItem from './item/TourListInMainItem.vue';

const cheapList = ref([]);

onMounted(() => {
  getList();
});

const getList = () => {
  getCheapList(({ data }) => {
    cheapList.value = data;
    console.log(cheapList);
  });
};
</script>

<template>
  <p class="subject">💵놓치면 후회할 가격!</p>
  <div class="main">
    <TourListInMainItem
      v-for="popularItem in cheapList"
      :key="popularItem.tourboard_id"
      :item="popularItem"
    />
  </div>
</template>

<style scoped>
.main {
  display: flex;
  justify-content: space-around;
}
.subject {
  margin: 20px;
  margin-top: 50px;
  font-size: 30px;
  font-weight: 600;
}
</style>

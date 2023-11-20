<script setup>
import { ref, onMounted } from 'vue';
import { getPopularList } from '@/api/tourboard.js';
import { Swiper, SwiperSlide } from 'swiper/vue';

import TourListInMainItem from './item/TourListInMainItem.vue';

const popularList = ref([]);

onMounted(() => {
  getList();
});

const getList = () => {
  getPopularList(({ data }) => {
    popularList.value = data;
    console.log(popularList);
  });
};
</script>

<template>
  <p class="subject">🌟요즘 뜨는 여행지</p>
  <div class="main">
    <TourListInMainItem
      v-for="popularItem in popularList"
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

<!-- <script setup>
import { Swiper, SwiperSlide } from "swiper/vue";
import { ref, onMounted } from "vue";
</script>

<template>
  <h2>🌟요즘 뜨는 여행지</h2>
  <swiper class="movie-swiper" @swiper="setSwiperRef">
    <swiper-slide>123123</swiper-slide>
    <swiper-slide>123123</swiper-slide>
    <swiper-slide>123123</swiper-slide>
  </swiper>
</template>

<style scoped></style> -->

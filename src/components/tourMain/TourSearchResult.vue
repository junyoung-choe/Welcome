<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { listTourSerachBoard } from "@/api/tourboard";

import TourBoardListItem from "@/components/tour/item/TourBoardListItem.vue";

const route = useRoute();
const router = useRouter();
const tourList = ref([]);

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
      console.log(tourList.value);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div>
    <TourBoardListItem
      v-for="tour in tourList"
      :key="tour.tourboard_id"
      :tourBoard="tour"
    />
  </div>
</template>

<style scoped></style>

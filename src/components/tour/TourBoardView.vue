<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { tourboardView } from "@/api/tourboard";

const route = useRoute();
const router = useRouter();

const { tourboard_id } = route.params;
const tourboard = ref({});

onMounted(() => {
  getTourBoard();
});

const getTourBoard = () => {
  tourboardView(
    tourboard_id,
    ({ data }) => {
      console.log(data.tourboard);
      tourboard.value = data.tourboard;
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div>
    {{ tourboard }}
  </div>
</template>

<style scoped></style>

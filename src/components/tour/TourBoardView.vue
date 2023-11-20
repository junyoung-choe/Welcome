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
  <div class="main">
    <div class="section-left">
      <!-- 이미지 -->
      <img class="main-img" src="/src/img/winter.png" alt="" />
    </div>
    <div class="section-right">
      <div>
        <p>{{ tourboard.tourboard_tourName }}</p>
        <p>{{ tourboard.tourboard_keyword }}</p>
        <p>{{ tourboard.tourboard_price }}</p>
        <p>{{ tourboard.tourboard_salePrice }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main {
  padding: 0px 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.main-img {
  width: 400px;
}
</style>

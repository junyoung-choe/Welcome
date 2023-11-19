<script setup>
import { ref, onMounted } from "vue";
const props = defineProps({ item: Object });

onMounted(() => {
  //   console.log(props.item.tourboard_id);
});
</script>

<template>
  <div class="main">
    <div class="img-section">
      <img src="src\img\winter.png" alt="" />
    </div>
    <div class="content-section">
      <p>{{ item.tourboard_tourName }}</p>
      <p>{{ item.tourboard_keyword }}</p>
      <p>{{ item.tourboard_salePrice }}</p>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: block !important;
}
img {
  width: 90%;
  object-fit: cover;
}
</style>

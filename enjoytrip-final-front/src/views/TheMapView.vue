<script setup>
import { ref, onMounted } from "vue";

import VKakaomap from "../components/common/VKakaomap.vue";
import VSelect from "../components/common/VSelect.vue";

const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

const chargingStations = ref([]);
const selectStation = ref({});

const param = ref({
  serviceKey: VITE_OPEN_API_SERVICE_KEY,
  pageNo: 1,
  numOfRows: 20,
  zscode: 0,
});
</script>

<template>
  <div>
    <VKakaomap :stations="chargingStations" :selectStation="selectStation" />
  </div>
</template>

<style scoped></style>

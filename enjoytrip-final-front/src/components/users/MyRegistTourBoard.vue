<script setup>
import { localAxios } from '@/util/http-commons';
import { ref, onMounted } from 'vue';
import { jwtDecode } from 'jwt-decode';
import TourListInMainItem from '../tourMain/item/TourListInMainItem.vue';

const local = localAxios();
// const props = defineProps({ myid: Object });
const packages = ref([]);

onMounted(() => {
  let token = sessionStorage.getItem('accessToken');
  let decodeToken = jwtDecode(token);
  console.log(decodeToken.userId);

  getPackage(decodeToken.userId);
});

const getPackage = (user_id) => {
  local
    .get(`tourboard/user/${user_id}`)
    .then((response) => {
      console.log(response.data.list);
      packages.value = response.data.list;
    })
    .catch((error) => {
      console.error(error);
    });
};
</script>

<template>
  <div class="user-reservation-list">
    <TourListInMainItem v-for="item in packages" :key="item.tourboard_id" :item="item" />
  </div>
</template>

<style scoped>
.user-reservation-list {
  margin-top: 50px;
  display: grid;
  grid-template-rows: repeat(3, 180px);
  grid-template-columns: repeat(3, 1fr);
}
</style>

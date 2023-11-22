<script setup>
import { localAxios } from "@/util/http-commons";
import { ref, onMounted } from "vue";
import { jwtDecode } from "jwt-decode";

const local = localAxios();
// const props = defineProps({ myid: Object });
const packages = ref({});

onMounted(() => {
  let token = sessionStorage.getItem("accessToken");
  let decodeToken = jwtDecode(token);

  console.log("sdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf");
  console.log(decodeToken.userId);

  getPackage(decodeToken.userId);
  console.log("확인하러 드가자 ~");
  console.log(packages.value);
});

const getPackage = (user_id) => {
  local
    .get(`tourboard/user/${user_id}`)
    .then((response) => {
      console.log("! 확인하러 드가자 ~");
      console.log(response.data.list);
      packages.value = response.data.list[0];
    })
    .catch((error) => {
      console.error(error);
    });
};
</script>

<template>
  <div>this is myregisttourboard</div>
  {{ packages }}
</template>

<style scoped></style>

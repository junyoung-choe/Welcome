<script setup>
import { localAxios } from "@/util/http-commons";

import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { tourboardView } from "@/api/tourboard";

const route = useRoute();
const local = localAxios();

const { tourboard_id } = route.params;
const tourboard = ref({});
// 여러 사진으로 받기 위해서 배열로 선언
const file = ref([]);
const imageUrl = ref([]);

onMounted(() => {
  getTourBoard();
});
// 해당 투어 보드 번호로 -> 투어 정보와 파일 정보를 불러온다
const getTourBoard = () => {
  tourboardView(
    tourboard_id,
    ({ data }) => {
      tourboard.value = data.tourboard;
      // 이 부분에 for 문으로 반복을 시켜야겠다
      // for (var i = 0; i < data.tourboard.fileInfos.value.
      for (var j = 0; j < data.tourboard.fileInfos.length; j++) {
        console.log("welcome");
        file.value.push(data.tourboard.fileInfos[j]);
        getFile(j);
      }
    },
    (error) => {
      console.log(error);
    }
  );
};

const getFile = (j) => {
  const sfolder = file.value[j].saveFolder; // 서버에서 필요한 폴더명
  const ofile = file.value[j].originalFile; // 서버에서 필요한 원본 파일명
  const sfile = file.value[j].saveFile; // 서버에서 필요한 저장된 파일명
  // console.log("--------");
  // console.log(file.value.saveFolder);

  local
    .get(`/file/${sfolder}/${ofile}/${sfile}`, { responseType: "arraybuffer" })
    .then((response) => {
      const blob = new Blob([response.data], { type: "image/jpeg" });
      imageUrl.value[j] = URL.createObjectURL(blob);
    })
    .catch((error) => {
      console.error(error);
    });
};

// 파일 정보를 back 으로 보내고 binary 파일로 받아온다
</script>

<template>
  <div>
    {{ tourboard }}

    <!-- {{ file }}
    {{ file.saveFolder }}
    {{ file.originalFile }}
    {{ file.saveFile }} -->

    <div v-for="(url, index) in imageUrl" :key="index">
      <img :src="url" alt="Uploaded Image" />
    </div>
    <!-- <img :src="imageUrl" alt="Uploaded Image" /> -->
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

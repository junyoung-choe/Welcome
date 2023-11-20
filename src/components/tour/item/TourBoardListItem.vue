<script setup>
import { ref, onMounted, onUnmounted } from "vue";

/// 사진 업로드를 위한 코드
import { localAxios } from "@/util/http-commons";
const local = localAxios();
const imageUrl = ref("");

const photo = ref();

onMounted(() => {
  if (props.tourBoard.fileInfos.length > 0) {
    getFile();
  }
  console.log(photo.value);
  // console.log("--------=-=-=-==--=-");
  // console.log(props.tourBoard.fileInfos[0]);
});

onUnmounted(() => {
  photo.value = false; // Vue 컴포넌트가 해제될 때 photo를 다시 false로 초기화
});

const getFile = () => {
  const sfolder = props.tourBoard.fileInfos[0].saveFolder; // 서버에서 필요한 폴더명
  const ofile = props.tourBoard.fileInfos[0].originalFile; // 서버에서 필요한 원본 파일명
  const sfile = props.tourBoard.fileInfos[0].saveFile; // 서버에서 필요한 저장된 파일명
  // console.log("--------");
  // console.log(props.tourBoard.fileInfos[0]);

  local
    .get(`/file/${sfolder}/${ofile}/${sfile}`, { responseType: "arraybuffer" })
    .then((response) => {
      const blob = new Blob([response.data], { type: "image/jpeg" });
      imageUrl.value = URL.createObjectURL(blob);
      console.log("imageUrl.value = ");
      // console.log(photo.value);
      photo.value = true;
    })
    .catch((error) => {
      console.error(error);
    });
};
///
const props = defineProps({ tourBoard: Object });

const keywords = ref([]);
const startDate = ref("");
const endDate = ref("");

keywords.value = props.tourBoard.tourboard_keyword.split(", ");
startDate.value = `${props.tourBoard.tourboard_startDate[0]}/${props.tourBoard.tourboard_startDate[1]}/${props.tourBoard.tourboard_startDate[2]}`;
endDate.value = `${props.tourBoard.tourboard_endDate[0]}/${props.tourBoard.tourboard_endDate[1]}/${props.tourBoard.tourboard_endDate[2]}`;
</script>

<template>
  <!-- <router-link
        :to="{ name: 'board-view', params: { board_id: board.board_id } }"
        class="article-title link-dark"
      >
        {{ board.board_title }}
      </router-link> -->
  <div class="main">
    <div class="img-section">
      <template v-if="photo">
        <img :src="imageUrl" alt="Uploaded Image" />
      </template>
      <template v-else>
        <img src="/src/img/winter.png" alt="Uploaded Image" />
      </template>
      <!-- <img :src="imageUrl" alt="Uploaded Image" /> -->
      <!-- <img src="/src/img/winter.png" alt="" /> -->
    </div>
    <div class="product-section">
      <div class="title-name">
        {{ tourBoard.tourboard_tourName }}
      </div>
      <div class="keyword">
        <span v-for="keyword in keywords" :key="keyword">#{{ keyword }}</span>
      </div>
      <div class="content">
        <span
          >가성비 좋은 시내 호텔에서 투숙하며, 다낭 핵심 시내 관광코스와 인기
          관광지인 바나힐 테마파크를 관광할 수 있는 특가 상품입니다.</span
        >
      </div>
      <div class="date">
        <span>여행날짜</span>
        <span>{{ startDate }}</span>
        <span>~</span>
        <span>{{ endDate }}</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="price-section">
      <div style="height: 100%; position: relative">
        <p class="price">{{ tourBoard.tourboard_price }}원</p>
        <p class="sale-price">{{ tourBoard.tourboard_salePrice }}원</p>
        <router-link
          class="btn-box"
          :to="{
            name: 'tour-detail',
            params: { tourboard_id: tourBoard.tourboard_id },
          }"
          >상세보기</router-link
        >
      </div>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  border-top: 1px solid rgba(0, 0, 0, 0.2);
}

img {
  width: 200px;
}

.price {
  text-decoration: line-through;
  color: rgba(0, 0, 0, 0.5);
  margin-bottom: 0px;
}
.sale-price {
  font-size: 22px;
  font-weight: 700;
  position: relative;
}

.btn-box {
  position: absolute;
  bottom: 0;
  text-decoration: none;
  background-color: #cff0fa;
  width: 100px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
}

.line {
  width: 10px;
  height: inherit;
  border-left: 1px solid rgba(0, 0, 0, 0.2);
  margin: 10px;
}

.title-name {
  font-size: 22px;
  font-weight: 600;
}

.keyword {
  color: rgba(0, 0, 0, 0.5);
}
.date {
  font-size: 12px;
  color: rgba(0, 0, 0, 0.5);
}
.price-section {
  min-width: 150px;
}
.img-section {
  margin: 30px 10px;
}

.price-section {
  margin: 30px 10px;
}

.product-section {
  margin: 30px 10px;
}
</style>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
import { localAxios } from "@/util/http-commons";
const props = defineProps({ item: Object });

const arr = ref([]);
const tag = ref("");

const router = useRouter();
const itemData = ref({});

/// 사진 업로드
const local = localAxios();
const imageUrl = ref("");

const photo = ref();

onMounted(() => {
  console.log(props.item[0]);
  makeTag();
  if (props.item.fileInfos[0] != null) {
    getFile();
  }
});

onUnmounted(() => {
  photo.value = false; // Vue 컴포넌트가 해제될 때 photo를 다시 false로 초기화
});

const getFile = () => {
  const sfolder = props.item.fileInfos[0].saveFolder; // 서버에서 필요한 폴더명
  const ofile = props.item.fileInfos[0].originalFile; // 서버에서 필요한 원본 파일명
  const sfile = props.item.fileInfos[0].saveFile; // 서버에서 필요한 저장된 파일명
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

const makeTag = () => {
  arr.value = props.item.tourboard_keyword.split(", ");

  for (let i = 0; i < arr.value.length; i++) {
    tag.value += "#" + arr.value[i] + " ";
  }
};

const mvView = () => {
  router.push({ path: "/tour/detail/" + props.item.tourboard_id });
};

const moveWrite = () => {
  router.push({ name: "board-write" });
};
</script>

<template>
  <div class="main" @click="mvView">
    <div class="img-section">
      <template v-if="photo">
        <img :src="imageUrl" alt="Uploaded Image" />
      </template>
      <template v-else>
        <img src="/src/img/winter.png" alt="Uploaded Image" />
      </template>
    </div>
    <div class="content-section">
      <p class="title">{{ item.tourboard_tourName }}</p>
      <p class="tag">{{ tag }}</p>
      <p class="price">{{ item.tourboard_salePrice }}원</p>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: block !important;
  width: 300px;
  box-shadow: 1px 5px 5px rgba(0, 0, 0, 0.2);
}
.main:hover {
  cursor: pointer;
}
img {
  width: 100%;
  object-fit: cover;
}
.content-section {
  padding: 10px;
}
.title {
  font-size: 30px;
  font-weight: 600;
  margin-bottom: 0px;
}
.tag {
  color: rgba(0, 0, 0, 0.5);
  margin-bottom: 0px;
}
.price {
  text-align: right;
  margin-bottom: 0px;
  font-size: 20px;
  font-weight: 600;
}
</style>

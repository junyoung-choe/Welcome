<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const props = defineProps({ item: Object });

const arr = ref([]);
const tag = ref('');

const router = useRouter();
const itemData = ref({});

onMounted(() => {
  console.log(props.item[0]);
  makeTag();
});

const makeTag = () => {
  arr.value = props.item.tourboard_keyword.split(', ');

  for (let i = 0; i < arr.value.length; i++) {
    tag.value += '#' + arr.value[i] + ' ';
  }
};

const mvView = () => {
  router.push({ path: '/tour/detail/' + props.item.tourboard_id });
};

const moveWrite = () => {
  router.push({ name: 'board-write' });
};
</script>

<template>
  <div class="main" @click="mvView">
    <div class="img-section">
      <img src="src/img/winter.png" alt="" />
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

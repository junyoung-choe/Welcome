<script setup>
import { ref, onMounted } from "vue";
defineProps({ selectOption: Array });
const emit = defineEmits(["onKeySelect"]);

const key = ref("");

const onSelect = () => {
  emit("onKeySelect", key);
};
</script>

<template>
  <select v-model="key" class="select-box" @change="onSelect" onfocus="">
    <option value="" disabled selected hidden>선택</option>
    <option v-for="option in selectOption" :key="option" :value="option">
      {{ option }}
    </option>
  </select>
</template>

<style scoped>
.select-box {
  /* border-radius: 15px; */
  border: none;
  padding: 10px;
  margin-bottom: 10px;
  -moz-appearance: none;
  -webkit-appearance: none;
  appearance: none;
}

.select-box:focus {
  outline: none;
}
</style>

<script setup>
import { ref, onMounted } from 'vue';
defineProps({ selectOption: Array });
const emit = defineEmits(['onKeySelect']);

const key = ref('');

const onSelect = () => {
  emit('onKeySelect', key);
};

onMounted(() => {
  console.log('asdasdsa');
});
</script>

<template>
  <select v-model="key" class="form-select form-select-sm w-50" @change="onSelect">
    <option
      v-for="option in selectOption"
      :key="option"
      :value="option"
      :disabled="option === '' ? true : false"
    >
      {{ option }}
    </option>
  </select>
</template>

<style scoped></style>

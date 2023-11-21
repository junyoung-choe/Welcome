<script setup>
import { ref } from "vue";
import { question } from "@/api/tourboard";

const text = ref("");
const que = ref({
  question: "",
});

const answer = () => {
  question(que.value, ({ data }) => {
    console.log(data.choices);
    text.value = data.choices[0].text;
  });
};
</script>

<template>
  <div>
    <h1>this is gpt test</h1>
    <input type="text" name="" id="" v-model="que.question" />
    <button @click="answer">질문!</button>
    {{ text }}
  </div>
</template>

<style scoped></style>

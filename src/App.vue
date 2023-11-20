<script setup>
import { onMounted } from 'vue';
import { RouterView } from 'vue-router';
import TheHeadingNavbar from './components/layout/TheHeadingNavbar.vue';

onMounted(() => {
  console.log("        .--'''''''''--.");
  console.log("     .'      .---.      '.");
  console.log('    /    .-----------.    \\');
  console.log('   |        .-----.        |');
  console.log('   |       .-.   .-.       |');
  console.log('   |      /   \\ /   \\      |');
  console.log('    \\    | .-. | .-. |    /');
  console.log("     '-._| | | | | | |_.-'");
  console.log("         | '-' | '-' |");
  console.log('          \\___/ \\___/');
  console.log("       _.-'  /   \\  `-._");
  console.log("     .' _.--|     |--._ '.");
  console.log("     ' _...-|     |-..._ '");
  console.log('            |     |');
  console.log("            '.___.'");
});
</script>

<template>
  <div>
    <the-heading-navbar></the-heading-navbar>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>

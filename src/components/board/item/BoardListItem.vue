<!-- 게시판 리스트 글 하나하나 -->
<script setup>
defineProps({ board: Object });
</script>

<template>
  <tr class="text-center">
    <th scope="row">{{ board.board_id }}</th>
    <td class="text-start">
      <router-link
        :to="{ name: 'board-view', params: { board_id: board.board_id } }"
        class="article-title link-dark"
      >
        {{ board.board_title }}
      </router-link>
    </td>
    <td>{{ board.user_id }}</td>
    <td>{{ board.board_views }}</td>
    <td>{{ board.board_regDate }}</td>
  </tr>
</template>

<style scoped>
a {
  text-decoration: none;
}
</style>

<!-- 게시판 리스트 글 하나하나 -->
<script setup>
import { ref, onMounted } from "vue";

const props = defineProps({ board: Object });

const year = props.board.board_regDate[0];
const month = props.board.board_regDate[1];
const day = props.board.board_regDate[2];

const date = `${year}/${month}/${day}`;

onMounted(() => {
  console.log(props.board);
});
</script>

<template>
  <tr class="text-center">
    <th scope="row" style="width: 100px">{{ board.board_id }}</th>
    <td class="text-center">
      <router-link
        :to="{ name: 'board-view', params: { board_id: board.board_id } }"
        class="article-title link-dark"
      >
        {{ board.board_title }}
      </router-link>
    </td>
    <td class="font-setting" style="width: 100px">{{ board.user_name }}</td>
    <td class="font-setting" style="width: 100px">{{ date }}</td>
    <td class="font-setting" style="width: 100px">{{ board.board_views }}</td>
  </tr>
</template>

<style scoped>
a {
  text-decoration: none;
}

.font-setting {
  color: rgba(0, 0, 0, 0.5);
  font-size: 14px;
}
</style>

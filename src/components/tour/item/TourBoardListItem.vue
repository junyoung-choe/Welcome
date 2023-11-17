<script setup>
defineProps({ tourBoard: Object });
</script>

<template>
  <tr class="text-center">
    <th scope="row">{{ tourBoard.tourboard_id }}</th>
    <td class="text-start">
      <!-- <router-link
        :to="{ name: 'board-view', params: { board_id: board.board_id } }"
        class="article-title link-dark"
      >
        {{ board.board_title }}
      </router-link> -->
      {{ tourBoard.tourboard_tourName }}
    </td>
    <td>{{ tourBoard.tourboard_keyword }}</td>
    <td>{{ tourBoard.tourboard_startDate }}</td>
    <td>{{ tourBoard.tourboard_endDate }}</td>
    <td>{{ tourBoard.tourboard_deadLineDate }}</td>
    <td>{{ tourBoard.tourboard_price }}</td>
    <td>{{ tourBoard.tourboard_salePrice }}</td>
    <td>{{ tourBoard.tourboard_departure }}</td>
    <td>{{ tourBoard.tourboard_destination }}</td>
  </tr>
</template>

<style scoped></style>

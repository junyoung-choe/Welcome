<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";
import { jwtDecode } from "jwt-decode";
import { localAxios } from "@/util/http-commons";

const route = useRoute();
const router = useRouter();
const local = localAxios();

const { board_id } = route.params;
const board = ref({});
const comment = ref({
  user_id: 0,
  board_id: 0,
  comment_content: "댓글",
});

// 로그인한 회원과 토큰에 저장된 회원이 같을때만 삭제 수정 버튼이 생성된다
const possible = ref(false);

const commentsPossible = ref(false);

const getUserInformation = () => {
  let token = sessionStorage.getItem("accessToken");
  let decodeToken = jwtDecode(token);
  console.log(decodeToken.userId + " , " + board.value);
  if (decodeToken.userId == board.value.user_id) {
    possible.value = true;
  }
  if (decodeToken.userId != null) {
    commentsPossible.value = true;
  }
  comment.value.user_id = decodeToken.userId;
};
///

onMounted(() => {
  console.log(board_id);
  comment.value.board_id = board_id;
  getBoard();
});

const getBoard = () => {
  // const { articleno } = route.params;
  console.log(board_id + "번글 얻으러 가자!!!");
  detailArticle(
    board_id,
    ({ data }) => {
      // console.log("1");
      board.value = data;
      getUserInformation();
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: "board-list" });
}

function moveModify() {
  router.push({ name: "board-modify", params: { board_id } });
}

function onDeleteArticle() {
  // const { articleno } = route.params;
  console.log(board_id + "번글 삭제하러 가자!!!");
  deleteArticle(
    board_id,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      console.log(error);
    }
  );
}

function commentRegButton() {
  regComment(comment.value);
}

function regComment(param, success, fail) {
  console.log("해당 글에 댓글 등록하러 갑니데이");
  local.post(`board/comment`, param).then(success).catch(fail);
}
</script>

<template>
  <!-- <div>{{ board }}</div> -->
  <div>
    제목 :
    {{ board.board_title }}
  </div>
  <div>
    내용 :
    {{ board.board_content }}
  </div>
  <div>
    작성자 :
    {{ board.user_name }}
  </div>
  <div>
    작성일자 :
    {{ board.board_regDate }}
  </div>
  <div class="d-flex justify-content-end">
    <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">글목록</button>
    <button
      v-if="possible"
      type="button"
      class="btn btn-outline-success mb-3 ms-1"
      @click="moveModify"
    >
      글수정
    </button>
    <button
      v-if="possible"
      type="button"
      class="btn btn-outline-danger mb-3 ms-1"
      @click="onDeleteArticle"
    >
      글삭제
    </button>
  </div>

  <hr />
  <!--  댓글 부분   -->
  <input
    v-if="commentsPossible"
    type="text"
    placeholder="댓글내용..."
    v-model="comment.comment_content"
  />
  <button
    v-if="commentsPossible"
    type="button"
    class="btn btn-outline-danger mb-3 ms-1"
    @click="commentRegButton"
  >
    댓글작성
  </button>
  <!-- <div>{{ board.commentDtos }}</div> -->
  <br />
  <br />
  <template v-for="(comments, index) in board.commentDtos" :key="comments.comment_id">
    {{ index + 1 }}
    댓글내용
    {{ comments.comment_content }}
    작성시간
    {{ comments.comment_regDate }}
    <br />
    <br />
  </template>
  <!-- {{ comment }} -->
  <!-- <template v-for="index in board.commentDtos.length">
    {{ board.commentDtos[index - 1].comment_id }}
  </template> -->
</template>

<style scoped></style>

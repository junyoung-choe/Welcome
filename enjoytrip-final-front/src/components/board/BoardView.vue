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
  comment_content: "",
});

// 로그인한 회원과 토큰에 저장된 회원이 같을때만 삭제 수정 버튼이 생성된다
const possible = ref(false);

const commentsPossible = ref(false);
const date = ref("");

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
      date.value = `${board.value.board_regDate[0]}/${board.value.board_regDate[1]}/${board.value.board_regDate[2]} ${board.value.board_regDate[3]}:${board.value.board_regDate[4]}`;
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
  <div class="main-content">
    <div class="title">
      {{ board.board_title }}
    </div>
    <div class="dateANDname">
      <div class="userName">
        {{ board.user_name }}
      </div>
      <div class="date">
        {{ date }}
      </div>
    </div>
    <div class="content">
      {{ board.board_content }}
    </div>
  </div>
  <div class="d-flex justify-content-end">
    <button type="button" class="list-btn" @click="moveList">글목록</button>
    <button v-if="possible" type="button" class="list-btn" @click="moveModify">글수정</button>
    <button v-if="possible" type="button" class="list-btn" @click="onDeleteArticle">글삭제</button>
  </div>

  <hr />
  <!--  댓글 부분   -->
  <div class="comment-input-box">
    <input
      v-if="commentsPossible"
      type="text"
      class="comment-input"
      placeholder="댓글내용..."
      v-model="comment.comment_content"
    />
    <button v-if="commentsPossible" type="button" class="comment-add-btn" @click="commentRegButton">
      댓글작성
    </button>
  </div>
  <!-- <div>{{ board.commentDtos }}</div> -->
  <div class="comment-box">
    <template v-for="(comments, index) in board.commentDtos" :key="comments.comment_id">
      <div class="comment-each">
        {{ comments.comment_content }}
      </div>
    </template>
  </div>
  <!-- {{ comment }} -->
  <!-- <template v-for="index in board.commentDtos.length">
    {{ board.commentDtos[index - 1].comment_id }}
  </template> -->
</template>

<style scoped>
.title {
  font-size: 35px;
  font-weight: 700;
}
.dateANDname {
  display: flex;
}
.userName {
  font-size: 13px;
  margin-right: 10px;
}
.date {
  font-size: 13px;
  color: rgba(0, 0, 0, 0.6);
}
.content {
  margin-top: 20px;
  font-size: 18px;
  margin-bottom: 20px;
}
.list-btn {
  border: none;
  background-color: white;
  color: rgba(0, 0, 0, 0.7);
}
.comment-input-box {
  display: flex;
}
.comment-input {
  width: 500px;
  border-radius: 10px;
  border: 1px solid rgba(0, 0, 0, 0.4);
  padding: 5px;
  height: 40px;
}
.comment-add-btn {
  border: 1px solid rgba(0, 0, 0, 0.4);
  background-color: white;
  margin-left: 20px;
  border-radius: 10px;
}

.comment-add-btn:hover {
  background-color: rgba(0, 0, 0, 0.1);
}

.comment-box {
  margin-top: 30px;
}
.comment-each {
  margin-bottom: 10px;
  margin-left: 10px;
}
</style>

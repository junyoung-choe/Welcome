<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { listTourBoard, question } from "@/api/tourboard.js";

import TourBoardListItem from "@/components/tour/item/TourBoardListItem.vue";

const router = useRouter();
const route = useRoute();

const text = ref("");
const que = ref({
  question: "",
});

const answer = () => {
  question(que.value, ({ data }) => {
    text.value = data.choices[0].text;
    param.value.word = text.value.substring(2);
    getTourBoardList();
  });
};

const param = ref({
  key: "",
  word: "",
});

const tourList = ref([]);
const tourListView = ref([]);
const cnt = ref(10);
const curCnt = ref(10);

const props = defineProps({ text: Object });

onMounted(() => {
  //   getTourBoardList();
});

const getTourBoardList = () => {
  tourListView.value.length = 0;
  console.log("서버에서 글목록 얻어오자!!!", param.value);
  listTourBoard(
    param.value,
    ({ data }) => {
      console.log("==============");
      console.log(data);
      tourList.value = data;
      // currentPage.value = data.currentPage;
      // totalPage.value = data.totalPageCount;
      console.log(tourList.value);
      for (let index = 0; index < cnt.value; index++) {
        if (tourList.value[index] == undefined) {
          break;
        }
        tourListView.value.push(tourList.value[index]);
      }
    },
    (error) => {
      console.log("error");
      console.log(error);
    }
  );
};

// const onPageChange = (val) => {
//   console.log(val + '번 페이지로 이동 준비 끝!!!');
//   currentPage.value = val;
//   param.value.pgno = val;
//   getTourBoardList();
// };

const sortListCheap = () => {
  tourList.value.sort(function (a, b) {
    return a.tourboard_salePrice - b.tourboard_salePrice;
  });
  tourListView.value.length = 0;
  for (let index = 0; index < cnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
};

const sortListExpensive = () => {
  tourList.value.sort(function (a, b) {
    return b.tourboard_salePrice - a.tourboard_salePrice;
  });
  tourListView.value.length = 0;
  for (let index = 0; index < cnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
};

const getMore = () => {
  if (curCnt.value >= tourList.value.length) {
    alert("마지막 입니다!");
  }

  for (let index = curCnt.value; index < cnt.value + curCnt.value; index++) {
    if (tourList.value[index] == undefined) {
      break;
    }
    tourListView.value.push(tourList.value[index]);
  }
  curCnt.value += cnt.value;
};
</script>

<template>
  <div class="search-box">
    <div class="ai-msg">
      <p class="main-text">경험해 보고 싶은 것을 AI에게 물어보세요!</p>
    </div>
    <div class="question-box">
      <input
        class="question-input"
        type="text"
        name=""
        id=""
        v-model="que.question"
        placeholder="ex) 자연이 아름다운 나라"
      />
      <!-- <button @click="answer" class="question-btn">검색</button> -->

      <button class="answer-btn" @click="answer">검색</button>
    </div>
  </div>
  <div style="text-align: center">
    <p v-if="text != ''" class="p-tag">
      AI추천 결과 <span class="result">{{ text }}</span
      >(으)로 검색한 여행입니다
    </p>
  </div>
  <div class="main">
    <div class="section-left">
      <div class="box">
        <div class="btn-box">
          <button @click="sortListCheap">낮은가격순</button>
          <button @click="sortListExpensive">높은가격순</button>
          <button @click="sortListExpensive">평점순</button>
          <button @click="sortListExpensive">예약순</button>
        </div>
      </div>
    </div>
    <div class="section-right" v-if="tourList.length > 0">
      <div>
        <TourBoardListItem
          v-for="tourBoard in tourListView"
          :key="tourBoard.tourboard_id"
          :tourBoard="tourBoard"
        />
      </div>
      <div class="more-btn-box">
        <button @click="getMore">더보기▽</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
}
.main-text {
  font-size: 30px;
  font-weight: 700;
}
.section-left {
  border-top: 1px solid rgba(0, 0, 0, 0.2);
  flex-grow: 1;
  padding-top: 10px;
  width: 250px;
}

.section-right {
  flex-grow: 1;
}
.more-btn-box {
  text-align: center;
}
.more-btn-box button {
  width: 100px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  background-color: rgb(255, 249, 237);
}

button:hover {
  background-color: rgba(0, 0, 0, 0.2) !important;
}

.btn-box {
  display: flex;
  flex-direction: column;
  margin-top: 100px;
}

.btn-box button {
  background-color: white;
  border-radius: 5px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  color: rgba(0, 0, 0, 0.8);
  margin-top: 10px;
}

.btn {
  background-color: rgba(0, 0, 0, 0.2);
  border: 1px solid rgba(0, 0, 0, 0.2);
  color: rgba(0, 0, 0, 0.7);
}
.box {
  position: fixed;
  top: 140px;
  left: 10px;
  width: 200px !important;
}
.search-box {
  text-align: center;
  margin-bottom: 20px;
}
.question-input {
  width: 500px;
  height: 30px;
  padding: 20px;
  outline: none;
}
.answer-btn {
  margin-left: 10px;
  padding: 8px;
  border: none;
  background-color: #cff0fa;
  border-radius: 10px;
}
.p-tag {
  font-size: 20px;
}
.result {
  font-weight: 900;
  font-size: 25px;
}
</style>

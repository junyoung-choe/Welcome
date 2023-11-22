<script setup>
import { localAxios } from '@/util/http-commons';

import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { tourboardView, plusPeople, makeReserve } from '@/api/tourboard';
import { jwtDecode } from 'jwt-decode';

const route = useRoute();
const router = useRouter();
const local = localAxios();

const { tourboard_id } = route.params;
const tourboard = ref({});
// 여러 사진으로 받기 위해서 배열로 선언
const file = ref([]);
const imageUrl = ref([]);

const tag = ref([]);

const startDate = ref('');
const endDate = ref('');

/// 예약자의 숫자
const reserveNum = ref(0);

const add = () => {
  reserveNum.value++;
};

const subtract = () => {
  reserveNum.value--;
};
///

onMounted(() => {
  getTourBoard();
});
// 해당 투어 보드 번호로 -> 투어 정보와 파일 정보를 불러온다
const getTourBoard = () => {
  tourboardView(
    tourboard_id,
    ({ data }) => {
      tourboard.value = data.tourboard;
      console.log(tourboard.value.tourboard_startDate[0]);
      // 이 부분에 for 문으로 반복을 시켜야겠다
      // for (var i = 0; i < data.tourboard.fileInfos.value.
      for (var j = 0; j < data.tourboard.fileInfos.length; j++) {
        console.log('welcome');
        file.value.push(data.tourboard.fileInfos[j]);
        getFile(j);
      }

      tag.value = tourboard.value.tourboard_keyword.split(', ');
      for (var j = 0; j < tag.value.length; j++) {
        tag.value[j] = '#' + tag.value[j];
      }
      startDate.value = `${tourboard.value.tourboard_startDate[0]}/${tourboard.value.tourboard_startDate[1]}/${tourboard.value.tourboard_startDate[3]}`;
      endDate.value =
        tourboard.value.tourboard_endDate[0] +
        '/' +
        tourboard.value.tourboard_endDate[1] +
        '/' +
        tourboard.value.tourboard_endDate[3];
    },
    (error) => {
      console.log(error);
    }
  );
};

const getFile = (j) => {
  const sfolder = file.value[j].saveFolder; // 서버에서 필요한 폴더명
  const ofile = file.value[j].originalFile; // 서버에서 필요한 원본 파일명
  const sfile = file.value[j].saveFile; // 서버에서 필요한 저장된 파일명
  // console.log("--------");
  // console.log(file.value.saveFolder);

  local
    .get(`/file/${sfolder}/${ofile}/${sfile}`, { responseType: 'arraybuffer' })
    .then((response) => {
      const blob = new Blob([response.data], { type: 'image/jpeg' });
      imageUrl.value[j] = URL.createObjectURL(blob);
    })
    .catch((error) => {
      console.error(error);
    });
};

// 예약하겠다는 버튼이다
const msg = () => {
  let token = sessionStorage.getItem('accessToken');
  if (token == null) {
    router.push({
      path: '/user/login',
    });
  } else {
    alert('예약완료!!');
    // reserveNum 만큼 현재의 tourboard_id 로 등록자의 숫자를 +
    plusPeople(tourboard_id, reserveNum.value);
    // 현재의 tourboard_id 와 현재 user_id 로 reserve 보드에 새로운 테이블을 등록한다
    let decodeToken = jwtDecode(token);
    makeReserve(tourboard_id, decodeToken.userId);
  }
};
// 파일 정보를 back 으로 보내고 binary 파일로 받아온다
</script>

<template>
  <div>
    <!-- {{ file }}
    {{ file.saveFolder }}
    {{ file.originalFile }}
    {{ file.saveFile }} -->

    <!-- <img :src="imageUrl" alt="Uploaded Image" /> -->
  </div>
  <div class="outside">
    <div class="main">
      <div class="section-left">
        <div v-for="(url, index) in imageUrl" :key="index">
          <img class="main-img" :src="url" alt="Uploaded Image" />
        </div>
        <!-- <img class="main-img" src="/src/img/winter.png" alt="" /> -->
      </div>
      <div class="section-right">
        <div>
          <p class="title">{{ tourboard.tourboard_tourName }}</p>
          <p class="note">
            안전, 청결은 기본. 장가계 핵심 명소 관광으로 구성된 상품입니다. 합리적인 가격으로
            장가계를 여행해보세요.
          </p>
          <hr />
          <div class="tagAndStar">
            <div>
              <span class="hash-tag" v-for="(item, index) in tag" :key="index">{{ item }}</span>
            </div>
            <div>
              <font-awesome-icon icon="fa-solid fa-star" />
              <span class="score">4.5</span>
            </div>
          </div>
          <hr />
          <div class="icon-box">
            <div>
              <font-awesome-icon class="icon" icon="fa fa-calendar" />
              <span>3박 4일</span>
            </div>
            <div>
              <font-awesome-icon class="icon" icon="fas fa-plane" />
              <span>LCC</span>
            </div>
            <div>
              <font-awesome-icon class="icon" icon="fas fa-shopping-bag" />
              <span>쇼핑</span>
            </div>
            <div>
              <font-awesome-icon class="icon" icon="fas fa-users" />
              <span>단체여행</span>
            </div>
            <div>
              <font-awesome-icon class="icon" icon="fas fa-flag" />
              <span>인솔자동행</span>
            </div>
          </div>
          <div class="price-box">
            <p class="price">{{ tourboard.tourboard_price }}</p>
            <span class="price-sale">{{ tourboard.tourboard_salePrice }}</span>
            <span class="won">원</span>
          </div>
        </div>
      </div>
    </div>
    <div class="reservation-btn-box">
      <div class="plusMinus">
        <input type="number" v-model="reserveNum" />
        <button @click="add">+</button>
        <button @click="subtract">-</button>
      </div>
      <button class="reservation-btn" @click="msg">예약하기</button>
    </div>
    <div class="second-box">
      <div class="schedule">
        <p class="schedule-title">여행 주요일정</p>
        <div class="schedule-section01">
          <div><p>일정</p></div>
          <div>
            <p>{{ startDate }}</p>
            <p>{{ endDate }}</p>
          </div>
        </div>
        <div>
          <div><p>여행도시</p></div>
        </div>
        <div class="reservation-section01">
          <div><p>예약현황</p></div>
          <div>
            <span class="reservation-text">예약: {{ tourboard.tourboard_stock }}명</span>
            <span>현재: {{ tourboard.tourboard_stockCnt }}명</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.outside {
  padding: 0px 200px;
}
.main {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 60px;
  padding-bottom: 100px;
}
.main-img {
  width: 600px;
  height: 400px;
  object-fit: cover;
}
.section-right {
  width: 600px;
  padding-left: 50px;
}
.title {
  font-size: 30px;
  font-weight: 600;
}

.note {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.7);
}
.tagAndStar {
  display: flex;
  justify-content: space-between;
}
.hash-tag {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.7);
  background-color: rgba(0, 0, 0, 0.1);
  margin-right: 15px;
}
.score {
  margin-left: 10px;
}

.icon-box {
  display: flex;
}

.icon-box div {
  display: flex;
  flex-direction: column;
  margin-right: 30px;
}

.icon {
  font-size: 20px;
}

.icon-box span {
  font-size: 12px;
  color: rgba(0, 0, 0, 0.7);
}
.price-box {
  position: relative;
  margin-top: 20px;
}
.price {
  right: 0px;
  position: absolute;
  color: rgba(0, 0, 0, 0.4);
  text-decoration: line-through;
}
.price-sale {
  right: 15px;
  position: absolute;
  top: 20px;
  font-size: 30px;
  font-weight: 700;
}

.won {
  right: 0px;
  position: absolute;
  top: 35px;
}
.second-box {
  border-top: 1px solid rgba(0, 0, 0, 0.4);
}
.schedule-title {
  font-size: 25px;
  font-weight: 800;
}
.schedule-section01 {
  display: flex;
}
.reservation-btn-box {
  position: relative;
  margin-bottom: 100px;
}
.reservation-btn {
  position: absolute;
  right: 0px;
  border: none;
  border-radius: 10px;
  background-color: #cff0fa;
  width: 200px;
  height: 40px;
  top: 40px;
}

.plusMinus {
  position: absolute;
  right: 0px;
}

.plusMinus > input {
  width: 50px;
}

.plusMinus button {
  margin-left: 5px;
  background-color: white;
  font-weight: 800;
}

.reservation-section01 {
  display: flex;
}
.reservation-text {
  margin-right: 20px;
}
</style>

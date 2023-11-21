<template>
  <div class="main">
    <div class="title">
      <p>여행 등록</p>
    </div>
    <div class="content">
      <table>
        <tr>
          <th><label>여행 이름 </label></th>
          <th><input type="text" v-model="tourBoardDto.tourboard_tourName.value" /></th>
        </tr>
        <tr>
          <th><label>키워드 등록 </label></th>
          <th><input v-model="tourBoardDto.tourboard_keyword.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>여행 시작일 </label></th>
          <th><input v-model="tourBoardDto.tourboard_startDate.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>여행 도착일</label></th>
          <th><input v-model="tourBoardDto.tourboard_endDate.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>신청 마감 날짜</label></th>
          <th><input v-model="tourBoardDto.tourboard_deadLineDate.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>가격</label></th>
          <th><input v-model="tourBoardDto.tourboard_price.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>총원</label></th>
          <th><input v-model="tourBoardDto.tourboard_stock.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>출발지</label></th>
          <th><input v-model="tourBoardDto.tourboard_departure.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>여행지</label></th>
          <th><input v-model="tourBoardDto.tourboard_destination.value" type="text" /></th>
        </tr>
        <tr>
          <th><label>여행설명</label></th>
          <th><input type="text" /></th>
        </tr>
      </table>
      <input type="file" multiple @change="onFileChange" />
      <!--  -->
      <button @click="upload">등록</button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, watch } from 'vue';
const file = ref(null);
const date = new Date();
// const formattedDate = date.toISOString().slice(0, 16);
const formattedDate = date.toLocaleString;
const tourBoardDto = {
  user_id: ref(2),
  tourboard_tourName: ref(''),
  tourboard_keyword: ref(''),
  tourboard_regDate: ref(''),
  tourboard_startDate: ref(''),
  tourboard_endDate: ref(''),
  tourboard_deadLineDate: ref(''),
  tourboard_price: ref(0),
  tourboard_salePrice: ref(0),
  tourboard_stock: ref(0),
  tourboard_stockCnt: ref(0),
  tourboard_fnishedYn: ref(false),
  tourboard_departure: ref(''),
  tourboard_destination: ref(''),
  tourboard_views: ref(0),
};

// const user_id = ref(0);
// const tourboard_tourName = ref("a");
// const tourboard_keyword = ref("a");
// const tourboard_regDate = ref("");
// const tourboard_startDate = ref("a");
// const tourboard_endDate = ref("a");
// const tourboard_deadLineDate = ref("a");
// const tourboard_price = ref(1000);
// const tourboard_salePrice = ref(500);
// const tourboard_stock = ref(0);
// const tourboard_stockCnt = ref(0);
// const tourboard_fnishedYn = ref(false);
// const tourboard_departure = ref("a");
// const tourboard_destination = ref("a");
// const tourboard_views = ref("0");

const onFileChange = (e) => {
  console.log('onFileChange called'); // 함수 호출 확인
  console.log(e.target.files); // 선택한 파일 객체 확인
  file.value = e.target.files;
};

const upload = async () => {
  let formData = new FormData();

  // console.log(file.value);
  // formData.append("upfile", file.value); // file의 실제 값을 얻음
  if (file.value && file.value.length > 0) {
    for (let i = 0; i < file.value.length; i++) {
      formData.append('upfile', file.value[i]); // 다중 파일 업로드를 위해 배열 형태로 추가
    }
  }

  for (let key in tourBoardDto) {
    formData.append(key, tourBoardDto[key].value); // 각 키와 실제 값을 얻어 FormData에 추가
  }

  try {
    await axios.post('http://localhost:70/tourboard', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    console.log('File and TourBoardDto uploaded successfully.');
  } catch (err) {
    console.error(err);
  }
};

// return {
//   file: null,
//   user_id,
//   tourboard_tourName,
//   tourboard_keyword,
//   tourboard_regDate,
//   tourboard_startDate,
//   tourboard_endDate,
//   tourboard_deadLineDate,
//   tourboard_price,
//   tourboard_salePrice,
//   tourboard_stock,
//   tourboard_stockCnt,
//   tourboard_fnishedYn,
//   tourboard_departure,
//   tourboard_destination,
//   tourboard_views,
// };
</script>

<style scoped>
.main {
  text-align: center;
}
</style>

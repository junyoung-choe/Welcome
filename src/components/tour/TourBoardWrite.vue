<template>
  <div class="main">
    <div class="title">
      <p>여행 등록</p>
    </div>
    <div class="content">
      <table class="input-table">
        <tr>
          <th><label>여행 이름 </label></th>
          <th>
            <input
              class="text-type"
              type="text"
              v-model="tourBoardDto.tourboard_tourName.value"
            />
          </th>
        </tr>
        <tr>
          <th><label>키워드 등록 </label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_keyword.value"
              type="text"
              placeholder=", (콤마)를 기준으로 키워드 입력 (ex 일본, 중국)"
            />
          </th>
        </tr>
        <!-- <tr>
          <v-col cols="12">
            <label class="form-label">여행 시작일 : </label>
            <v-text-field
              type="datetime-local"
              v-model="tourBoardDto.tourboard_startDate.value"
              variant="outlined"
            ></v-text-field>
          </v-col>
        </tr> -->
        <!-- 여행 시작일:
        <input type="datetime-local" value="2077-06-15T13:27" /> -->
        <tr>
          <th><label>여행 시작일 </label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_startDate.value"
              type="datetime-local"
            />
          </th>
        </tr>
        <tr>
          <th><label>여행 도착일</label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_endDate.value"
              type="datetime-local"
            />
          </th>
        </tr>
        <tr>
          <th><label>신청 마감 날짜</label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_deadLineDate.value"
              type="datetime-local"
            />
          </th>
        </tr>
        <tr>
          <th><label>가격</label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_price.value"
              type="text"
            />
          </th>
        </tr>
        <tr>
          <th><label>총원</label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_stock.value"
              type="text"
            />
          </th>
        </tr>
        <tr>
          <th><label>출발지</label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_departure.value"
              type="text"
            />
          </th>
        </tr>
        <tr>
          <th><label>여행지</label></th>
          <th>
            <input
              class="text-type"
              v-model="tourBoardDto.tourboard_destination.value"
              type="text"
            />
          </th>
        </tr>
        <tr>
          <th><label>여행설명</label></th>
          <th>
            <input
              class="text-type"
              type="text"
              placeholder="여행의 테마나 주의사항 등"
            />
          </th>
        </tr>
      </table>
      <input type="file" multiple @change="onFileChange" class="file-type" />
      <!--  -->
      <button class="regist-btn" @click="upload">여행지 등록</button>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { jwtDecode } from "jwt-decode";

let token = sessionStorage.getItem("accessToken");
let decodeToken = jwtDecode(token);

const file = ref(null);
// const date = new Date();
const router = useRouter();
// const formattedDate = date.toISOString().slice(0, 16);
// const formattedDate = date.toLocaleString;

// const sDate = ref("2021-12-31T23:59");
// const eDate = ref("2021-12-31T23:59");
// const dDate = ref("2021-12-31T23:59");

const tourBoardDto = {
  user_id: ref(decodeToken.userId),
  tourboard_tourName: ref(""),
  tourboard_keyword: ref(""),
  tourboard_regDate: ref(""),
  tourboard_startDate: ref("2020-12-31T23:59"),
  tourboard_endDate: ref("2030-12-31T23:59"),
  tourboard_deadLineDate: ref("2030-12-31T23:59"),
  tourboard_price: ref(0),
  tourboard_salePrice: ref(0),
  tourboard_stock: ref(0),
  tourboard_stockCnt: ref(0),
  tourboard_fnishedYn: ref(false),
  tourboard_departure: ref(""),
  tourboard_destination: ref(""),
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
  console.log("onFileChange called"); // 함수 호출 확인
  console.log(e.target.files); // 선택한 파일 객체 확인
  file.value = e.target.files;
};

const upload = async () => {
  if (confirm("여행지를 등록하시겠습니까?")) {
    let formData = new FormData();
    console.log("날짜 타입 확인");
    console.log(tourBoardDto.tourboard_startDate);

    console.log(tourBoardDto.tourboard_startDate);

    // tourBoardDto.tourboard_startDate =
    //   tourBoardDto.tourboard_startDate.toIOSOString();

    // console.log(file.value);
    // formData.append("upfile", file.value); // file의 실제 값을 얻음
    if (file.value && file.value.length > 0) {
      for (let i = 0; i < file.value.length; i++) {
        formData.append("upfile", file.value[i]); // 다중 파일 업로드를 위해 배열 형태로 추가
      }
    }

    for (let key in tourBoardDto) {
      formData.append(key, tourBoardDto[key].value); // 각 키와 실제 값을 얻어 FormData에 추가
    }

    try {
      await axios.post("http://localhost:70/tourboard", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      console.log("File and TourBoardDto uploaded successfully.");
    } catch (err) {
      console.error(err);
    }
    router.push({
      path: "/tour/list",
    });
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
.content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
input {
  width: 350px;
}

.title {
  font-size: 30px;
  font-weight: 700;
}

.text-type {
  border: none;
  outline: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  margin-bottom: 10px;
  margin-left: 10px;
}
.text-type:focus {
  border-bottom: 2px solid #2184d6;
}

.text-type::placeholder {
  font-size: 13px;
}
.regist-btn {
  width: 150px;
  border-radius: 15px;
  border: none;
  background-color: #cff0fa;
  height: 40px;
  margin-top: 20px;
}
input[type="file"]::file-selector-button {
  width: 150px;
  height: 30px;
  background: #fff;
  border: 1px solid rgb(77, 77, 77);
  border-radius: 10px;
  cursor: pointer;
}
.file-type {
  margin-top: 10px;
}
</style>

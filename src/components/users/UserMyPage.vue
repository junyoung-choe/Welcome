<script setup>
import { ref, onMounted } from "vue";

// (getUserInformation) 로그인 정보를 받아오기 위한 필드와 메소드
import { userMyPage, getReservation } from "@/api/user";
import { jwtDecode } from "jwt-decode";

// const user_id = ref();
// const user_password = ref();
// const user_regDate = ref();
// const refreshToken = ref();
const user_account = ref("");
const user_role = ref("");
const user_name = ref("");
const user_phone = ref("");
const user_cash = ref("");
const user_mileage = ref("");

const reservation = ref([]);

onMounted(() => {
  // 페이지 로드전 객체를 불러온다.
  getUserInformation();

  // 회원 ID로 reservation 에서 tourBoard_id 를 찾아서 tourBoard 들을 불러온다
  getReservePackage();
});

const getUserInformation = () => {
  let token = sessionStorage.getItem("accessToken");
  let decodeToken = jwtDecode(token);
  userMyPage(
    decodeToken.userId,
    ({ data }) => {
      console.log(data.resdata);
      user_account.value = data.resdata.user_account;
      user_role.value = data.resdata.user_role;
      user_name.value = data.resdata.user_name;
      user_phone.value = data.resdata.user_phone;
      user_cash.value = data.resdata.user_cash;
      user_mileage.value = data.resdata.user_mileage;
    },
    (error) => {
      console.log(error);
    }
  );
};

const getReservePackage = () => {
  let token = sessionStorage.getItem("accessToken");
  let decodeToken = jwtDecode(token);
  getReservation(
    decodeToken.userId,
    ({ data }) => {
      reservation.value = data.resdata;
      console.log(data.resdata);
    },
    (error) => {
      console.log(error);
    }
  );
};
///
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded-start"
                alt="..."
              />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">{{ user_account }}</li>
                  <li class="list-group-item">{{ user_name }}</li>
                  <li class="list-group-item">{{ user_phone }}</li>
                  <li class="list-group-item">{{ user_cash }}</li>
                  <li class="list-group-item">{{ user_mileage }}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        asdfasdf {{ reservation }}
        <div>
          <!-- <button type="button" class="btn btn-outline-secondary mt-2">수정</button> -->
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

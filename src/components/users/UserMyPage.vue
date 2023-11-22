<script setup>
import { ref, onMounted } from 'vue';

// (getUserInformation) 로그인 정보를 받아오기 위한 필드와 메소드
import { userMyPage, getReservation } from '@/api/user';
import { jwtDecode } from 'jwt-decode';

// menu 확인
import { useMenuStore } from '@/stores/menu';
import { storeToRefs } from 'pinia';
import MyRegistTourBoard from './MyRegistTourBoard.vue';
import TourListInMainItem from '../tourMain/item/TourListInMainItem.vue';

const menuStore = useMenuStore();
const { menuList } = storeToRefs(menuStore);
///

const user_id = ref('');
// const user_password = ref();
// const user_regDate = ref();
// const refreshToken = ref();
const user_account = ref('');
const user_role = ref('');
const user_name = ref('');
const user_phone = ref('');
const user_cash = ref('');
const user_mileage = ref('');

const reservation = ref([]);

onMounted(() => {
  // 페이지 로드전 객체를 불러온다.
  getUserInformation();

  // 회원 ID로 reservation 에서 tourBoard_id 를 찾아서 tourBoard 들을 불러온다
  getReservePackage();
  console.log('=============================');
  console.log(menuList.value[4].show);
  console.log('=============================');
});

const getUserInformation = () => {
  let token = sessionStorage.getItem('accessToken');
  let decodeToken = jwtDecode(token);
  userMyPage(
    decodeToken.userId,
    ({ data }) => {
      console.log(data.resdata);

      user_id.value = data.resdata.user_id;
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
  let token = sessionStorage.getItem('accessToken');
  let decodeToken = jwtDecode(token);
  getReservation(
    decodeToken.userId,
    ({ data }) => {
      reservation.value.push(data.resdata);
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
      <div>
        <p class="title">마이페이지</p>
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

        <!-- {{ reservation }} -->
        <!--  일반 유저일때 -->
        <template v-if="user_role == 'user'">
          <div><p>예약 정보</p></div>
          <div class="user-reservation-list">
            <TourListInMainItem
              v-if="reservation != null"
              v-for="item in reservation"
              :key="item[0].tourboard_id"
              :item="item[0]"
            />
          </div>
        </template>

        <!-- <template v-if="user_role == 'user'"> {{ reservation }} </template> -->
        <!--  여행 에이전트 일때 -->
        <template v-else>
          <MyRegistTourBoard :myid="user_id" />
        </template>

        <div>
          <!-- <button type="button" class="btn btn-outline-secondary mt-2">수정</button> -->
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.title {
  font-size: 35px;
  font-weight: 700;
}

.user-reservation-list {
  margin-top: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

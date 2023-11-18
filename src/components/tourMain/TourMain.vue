<script setup>
import { ref, onMounted } from "vue";
import { listDeparture, listDestination } from "@/api/tourboard";

import VTourSelect from "../common/VTourSelect.vue";

const departureList = ref([]);
const destinationList = ref([]);
const depa = ref("");
const dest = ref("");

const tourList = ref([]);

onMounted(() => {
  getDepartureList();
});

const getDepartureList = () => {
  listDeparture(({ data }) => {
    let option = [];
    for (var i = 0; i < data.length; i++) {
      option.push(data[i].tourboard_departure);
    }
    let uniqueOption = [...new Set(option)];
    console.log(uniqueOption);
    departureList.value = uniqueOption;
  });
};

const onChangeDeparture = (val) => {
  depa.value = val;
  listDestination({ depa: val.value }, ({ data }) => {
    let options = [];
    //   options.push({ text: '도착지', value: '' });
    for (var i = 0; i < data.length; i++) {
      options.push(data[i].tourboard_destination);
    }
    let uniqueOption = [...new Set(options)];
    console.log(uniqueOption);
    destinationList.value = uniqueOption;
    console.log(destinationList);
    //   gugunList.value = options;
  });
};

const onChangeDestination = (val) => {
  dest.value = val;
};
</script>

<template>
  <div class="main-main-box">
    <div class="main-main-box-section01">
      <div class="welcome-msg">
        <p>어디로 떠나볼까요?</p>
      </div>
      <div class="tour-search-box">
        <div class="select-box">
          <VTourSelect
            :selectOption="departureList"
            @onKeySelect="onChangeDeparture"
          />
        </div>
        <div class="select-box">
          <VTourSelect
            :selectOption="destinationList"
            @onKeySelect="onChangeDestination"
          />
        </div>
        <div>
          <router-link
            :to="`/tour/toursearchresult/${depa.value}/${dest.value}`"
            >검색</router-link
          >
        </div>
      </div>
    </div>

    <div class="slide-img"></div>
  </div>
</template>

<style scoped>
.main-main-box {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #cff0fa;
  display: flex;
  justify-content: space-around;
}

.welcome-msg > p {
  font-size: 25px;
  font-weight: 550;
}
.select-box {
  width: 400px;
}
</style>

<script setup>
import { ref, onMounted } from 'vue';
import { listDeparture, listDestination } from '@/api/tourboard';

import VTourSelect from '../common/VTourSelect.vue';

const departure = ref([]);
const destination = ref([]);
const tourList = ref([]);

onMounted(() => {
  getDepartureList();
});

const getDepartureList = () => {
  listDeparture(({ data }) => {
    let option = [];
    console.log(data);
    for (var i = 0; i < data.length; i++) {
      option.push(data[i].tourboard_departure);
    }
    let uniqueOption = [...new Set(option)];
    console.log(uniqueOption);
    // option.push({ text: '출발지 선택', value: '' });
    departure.value = uniqueOption;
  });
};

const onChangeDeparture = (val) => {
  listDestination({ depa: val.value }, ({ data }) => {
    console.log(data);
    let options = [];
    //   options.push({ text: '도착지', value: '' });
    for (var i = 0; i < data.length; i++) {
      options.push(data[i].tourboard_destination);
    }
    let uniqueOption = [...new Set(options)];
    console.log(uniqueOption);
    destination.value = uniqueOption;
    console.log(destination);
    //   gugunList.value = options;
  });
};
</script>

<template>
  <div class="row mb-2">
    <div class="col d-flex flex-row-reverse">
      <VTourSelect :selectOption="departure" @onKeySelect="onChangeDeparture" />
    </div>
    <div class="col"><VTourSelect :selectOption="destination" @onKeySelect="" /></div>
  </div>
</template>

<style scoped></style>

import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listTourBoard(param, success, fail) {
  local.get(`/tourboard`, { params: param }).then(success).catch(fail);
}

function listDeparture(success, fail) {
  local.get(`/tourboard/departureList`).then(success).catch(fail);
}

function listDestination(param, success, fail) {
  local
    .get(`/tourboard/destinationList`, { params: param })
    .then(success)
    .catch(fail);
}

function listTourSerachBoard(param, success, fail) {
  local
    .get(`/tourboard/tourSearchList`, { params: param })
    .then(success)
    .catch(fail);
}

function tourboardView(param, success, fail) {
  local.get(`/tourboard/${param}`).then(success).catch(fail);
}

function getPopularList(success, fail) {
  local.get(`/tourboard/popularList`).then(success).catch(fail);
}

function getCheapList(success, fail) {
  local.get(`/tourboard/cheapList`).then(success).catch(fail);
}

// 파일을 받아온다
function getFile(sfolder, ofile, sfile, success, fail) {
  local.get(`/file/${sfolder}/${ofile}/${sfile}`).then(success).catch(fail);
}

// 투어보드에 등록자를 +
function plusPeople(tourboard_id, reserveNum, success, fail) {
  local
    .get(`/tourboard/plus/${tourboard_id}/${reserveNum}`)
    .then(success)
    .catch(fail);
}
// 사용자 id 와 보드 id를 등록해야한다
function makeReserve(tourboard_id, user_id, reserveNum, success, fail) {
  local
    .get(`/reservation/register/${tourboard_id}/${user_id}/${reserveNum}`)
    .then(success)
    .catch(fail);
}

async function question(param, success, fail) {
  await local.post(`/chat-gpt/question`, param).then(success).catch(fail);
}

export {
  listTourBoard,
  listDeparture,
  listDestination,
  listTourSerachBoard,
  tourboardView,
  getPopularList,
  getCheapList,
  getFile,
  plusPeople,
  makeReserve,
  question,
};

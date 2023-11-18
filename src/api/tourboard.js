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

export { listTourBoard, listDeparture, listDestination, listTourSerachBoard };

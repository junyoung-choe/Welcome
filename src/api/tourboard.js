import { localAxios } from '@/util/http-commons';

const local = localAxios();

function listTourBoard(param, success, fail) {
  local.get(`/tourboard`, { params: param }).then(success).catch(fail);
}

export { listTourBoard };

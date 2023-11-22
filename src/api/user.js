import { localAxios } from "@/util/http-commons";


const local = localAxios();



async function userRegister(param, success, fail) {
  await local.post(`/member/join`, param).then(success).catch(fail);
}

async function userMyPage(userid, success, fail) {
  await local.get(`/member/mypage/${userid}`).then(success).catch(fail);
}

async function userConfirm(param, success, fail) {
  await local.post(`/member/login`, param).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  // 헤더에 Authorization 이라는 이름으로 access 토큰을 담아서 보낸다 즉 토큰이 유효해야 유저 정보를 받아온다 (유효하지 않으면 에러가 나온다)
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/member/info/${userid}`).then(success).catch(fail);
}

// 헤더에 refresh 토큰 담아서 서버에서 유효한지 + db 에 값과 같은지 확인하고 access 를 다시 받아올수도 있다 
async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/member/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/member/logout/${userid}`).then(success).catch(fail);
  // 로그아웃 후에 로그인 화면으로 이동한다
}

async function getReservation(userid, success, fail) {
  await local.get(`/reservation/package/${userid}`).then(success).catch(fail);
  // 로그아웃 후에 로그인 화면으로 이동한다
}

// 패키저 에이전시 확인



export { userConfirm, findById, tokenRegeneration, logout, userRegister, userMyPage, getReservation };

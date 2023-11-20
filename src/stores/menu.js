import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useMenuStore = defineStore('menuStore', () => {
  const menuList = ref([
    { name: '회원가입', show: true, routeName: 'user-join' },
    { name: '로그인', show: true, routeName: 'user-login' },
    { name: '내정보', show: false, routeName: 'user-mypage' },
    { name: '로그아웃', show: false, routeName: 'user-logout' },
  ]);

  // 로그인 된 이후에 메뉴를 변경해준다
  const changeMenuState = () => {
    // true 인것들은 false 로 변경 // false 인것은 true 로 변경
    menuList.value = menuList.value.map((item) => ({ ...item, show: !item.show }));
    console.log("------------------------")
    console.log(menuList.value)
  };

  return {
    menuList,
    changeMenuState,
  };
});

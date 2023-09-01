<template>
  <div class="">
    홈입니다<br>
    <RouterLink to="/admin/adminHome/adminSignUP">
      관리자 관리
    </RouterLink>
    |
    <RouterLink to="/admin/adminHome/adminPhoneList">
      사용자 관리
    </RouterLink>
    |
    <RouterLink to="/admin/adminHome/adminOrderList">
      주문 관리
    </RouterLink>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);
  },
};
</script>

<style>

</style>
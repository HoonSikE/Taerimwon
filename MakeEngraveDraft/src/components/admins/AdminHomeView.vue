<template>
  <div class="app">
    <div class="router">
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
    </div>
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
.router {
  font-size: 20px;
  text-align: center;
  font-family: "BMEULJIROTTF";

  /* background-color: #bf0023; */
}
</style>
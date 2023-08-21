<template>
  <div class="">
    홈입니다<br>
    <RouterLink to="/admin/adminHome/adminSignUP">
      관리자 관리
    </RouterLink>
    |
    <RouterLink to="/admin/adminHome/AdminOrderList">
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
};
</script>

<style>

</style>
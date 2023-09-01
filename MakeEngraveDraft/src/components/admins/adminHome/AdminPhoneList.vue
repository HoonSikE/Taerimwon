<template>
  <div class="">
    휴대폰 관리
  </div>
</template>

<script>
import { getFirestore, collection, query, orderBy, getDocs } from 'firebase/firestore';

export default {
  data() {
    return {
    };
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);
  },
  methods: {
    showOrderDetails(order) {
      // 클릭한 게시물의 세부 정보 표시
      this.selectedOrder = order;
    },
  },
};
</script>

<style>

</style>

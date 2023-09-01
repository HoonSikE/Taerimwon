<template>
  <div class="">
    주문 관리
    <!-- 게시물 목록 생성 -->
    <ul>
      <!-- <li v-for="order in orders" :key="order.id" @click="showOrderDetails(order)">
        {{ order.clientName }} - {{ order.currentDate }}
      </li> -->
    </ul>

    <!-- 세부 정보 표시 -->
    <div v-if="selectedOrder">
      <!-- <h2>{{ selectedOrder.clientName }} 세부 정보</h2> -->
      <!-- 여기에 세부 정보 표시 -->
    </div>
  </div>
</template>

<script>
import { getFirestore, collection, query, orderBy, getDocs } from 'firebase/firestore';

export default {
  data() {
    return {
      orders: [],           // 게시물 목록
      selectedOrder: null,  // 선택된 게시물 세부 정보
    };
  },
  async created() {
    // Firestore 초기화
    const firestore = getFirestore();
    const ordersCollection = collection(firestore, 'orders');

    // currentDate와 leaderName을 기준으로 쿼리 작성
    // const q = query(ordersCollection);
    const q = query(ordersCollection, orderBy('currentDate'), orderBy('leaderName'));

    try {
      // 쿼리 실행 및 데이터 가져오기
      const querySnapshot = await getDocs(q);
      const orders = [];
      querySnapshot.forEach((doc) => {
        // 각 문서에서 데이터를 가져와서 orders 배열에 추가
        orders.push(doc.data());
      });

      // orders 배열 업데이트
      this.orders = orders;
      console.error('데이터 가져오기 성공:', error);
    } catch (error) {
      console.error('데이터 가져오기 오류:', error);
    }
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

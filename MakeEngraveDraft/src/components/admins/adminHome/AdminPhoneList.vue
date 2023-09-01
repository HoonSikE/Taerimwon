<template>
  <div class="app">
    휴대폰 관리
    <h1>전화번호 목록</h1>
    <table>
      <thead>
        <tr>
          <th>전화번호</th>
          <th>인증 상태</th>
          <th>등록 일시</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="phone in phones" :key="phone.id">
          <td>{{ '0' + phone.phoneNumber.substring(3) }}</td>
          <td>{{ phone.authenticated ? '인증됨' : '미인증' }}</td>
          <td>{{ phone.currentDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { getFirestore, collection, query, orderBy, getDocs } from 'firebase/firestore';

export default {
  data() {
    return {
      phones: [], // Firestore에서 가져온 데이터를 저장할 배열
    };
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);
  },
  async created() {
    // Firestore 초기화
    const firestore = getFirestore();
    const phonesCollection = collection(firestore, 'phones');
    const q = query(phonesCollection);

    try {
      // Firestore에서 데이터 가져오기
      const querySnapshot = await getDocs(q);
      const phones = [];
      querySnapshot.forEach((doc) => {
        const phoneData = doc.data();
        const currentDate = phoneData.currentDate ? phoneData.currentDate.toDate().toLocaleString('ko-KR') : '';
        phones.push({
          id: doc.id,
          phoneNumber: phoneData.phoneNumber,
          authenticated: phoneData.authenticated,
          currentDate: currentDate,
        });
      });

      // 데이터 업데이트
      this.phones = phones;
      console.log('데이터 가져오기 성공');
    } catch (error) {
      console.error('데이터 가져오기 오류:', error);
    }
  },
};
</script>

<style>

</style>

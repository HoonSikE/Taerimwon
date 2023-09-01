<template>
  <div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { getFirestore, collection, addDoc, getDocs, updateDoc, doc, where, query } from "firebase/firestore";

export default {
  data() {
    return {
      phoneNumber: '',
      verificationCode: '',
      confirmationResult: null,
    };
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    // 페이지 로드 시 로그인 상태 확인
    this.authenticated = localStorage.getItem('authenticated');
    this.user = JSON.parse(localStorage.getItem('user')); // 유저 정보를 JSON으로 파싱

    if (this.authenticated === 'true' && this.user) {
      // 블랙리스트 관리
      this.checkBlackList(this.user.phoneNumber);
      // 등록 유저 관리
      this.checkNumber(this.user.phoneNumber);
      // console.log('유효성 검사');
    }else{
      // console.log('인증이 필요한 상태입니다.');
      // 홈 페이지로 이동
      this.$router.push({ name: 'main' }); // 'home'은 홈 라우터의 이름이라 가정
    }
  },
  methods: {
    async checkBlackList(phoneNumber){
      // 블랙리스트 관리
      const firestore = getFirestore();
      const blacklistCollection = collection(firestore, "blacklist");
      // console.log(phoneNumber);
      const q = query(blacklistCollection, where("phoneNumber", "==", phoneNumber));
      const blacklistSnapshot = await getDocs(q);

      if (blacklistSnapshot.docs.length > 0) {
        // console.log('블랙리스트에 등록된 번호입니다.');
        localStorage.removeItem('user'); // LocalStorage에서 상태 제거
        localStorage.removeItem('authenticated'); // LocalStorage에서 상태 제거
        this.user = null;
        this.authenticated = null;
        window.location.reload();
        return true;
      }
      return false;
    },
    async checkNumber(phoneNumber){
      // 등록 유저 관리
      const firestore = getFirestore();
      // Firestore에 사용자 정보 저장
      const phonesCollection = collection(firestore, "phones");
      const q = query(phonesCollection, where("phoneNumber", "==", phoneNumber));
      const querySnapshot = await getDocs(q);

      if (querySnapshot.empty) {
        console.log('리스트에 등록되지 않은 번호입니다.');
        localStorage.removeItem('user'); // LocalStorage에서 상태 제거
        localStorage.removeItem('authenticated'); // LocalStorage에서 상태 제거
        this.user = null;
        this.authenticated = null;
        window.location.reload();
        return true;
      }
      return false;
    },
  },
}
</script>
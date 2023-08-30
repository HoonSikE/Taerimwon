<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template>

<script>
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
    window.scrollTo(0, 0);

    // 페이지 로드 시 로그인 상태 확인
    const authenticated = localStorage.getItem('authenticated');
    const user = JSON.parse(localStorage.getItem('user')); // 유저 정보를 JSON으로 파싱

    if (authenticated === 'true' && user) {
      console.log('이미 인증된 상태입니다.');
    }else{
      console.log('인증이 필요한 상태입니다.');
      // 홈 페이지로 이동
      this.$router.push({ name: 'main' }); // 'home'은 홈 라우터의 이름이라 가정
    }
  },
}
</script>
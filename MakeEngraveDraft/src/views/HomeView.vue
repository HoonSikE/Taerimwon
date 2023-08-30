<template>
  <div class="app">
    <div v-if="!confirmationResult">
      ● 전화번호 인증
      <input v-model="phoneNumber" placeholder="전화번호" style="height: 40px; width: 100%;">
      <button @click="sendVerificationCode" class="order_button" id="sign-in-button">인증 코드 전송</button>
      <!-- <div id="recaptcha-container"></div> -->
    </div>
    <div v-else>
      ● 인증번호 입력
      <input v-model="verificationCode" placeholder="인증 코드" style="height: 40px; width: 100%;">
      <button @click="verifyCode" class="order_button">인증 확인</button>
    </div>
  </div>
</template>

<script>
import { getAuth, RecaptchaVerifier, signInWithPhoneNumber, PhoneAuthProvider } from 'firebase/auth'; // 수정된 import

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
  },
  methods: {
    // 인증 코드 전송 버튼 클릭 시
    async sendVerificationCode() {
      const auth = getAuth();
      auth.languageCode = 'ko';

      window.recaptchaVerifier = new RecaptchaVerifier(auth, 'sign-in-button', {
        'size': 'invisible',
        'callback': (response) => {
          onSignInSubmit();
        },
        'expired-callback': () => {
        }
      });

      // window.recaptchaVerifier = new RecaptchaVerifier(auth, 'recaptcha-container', {});
      const appVerifier = window.recaptchaVerifier;

      signInWithPhoneNumber(auth, '+82' + this.phoneNumber, appVerifier)
        .then((confirmationResult) => {
          console.log('인증 코드가 전송되었습니다.');
          this.confirmationResult = confirmationResult;
        }).catch((error) => {
          console.error('인증 코드 전송 실패:', error);
          this.confirmationResult = null;
        });
    },
    // 인증 코드 확인 버튼 클릭 시
    async verifyCode() {
      const auth = getAuth();
      auth.languageCode = 'ko';

      this.confirmationResult.confirm(this.verificationCode).then((result) => {
        const user = result.user;
        console.log('로그인이 되었습니다.');
      }).catch((error) => {
        console.log('로그인 실패:', error);
      });
    },
  },
};
</script>

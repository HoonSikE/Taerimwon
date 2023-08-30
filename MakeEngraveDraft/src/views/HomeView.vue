<template>
  <div class="app">
    <div v-if = "authenticated && user != null">
      메인화면입니다.
    </div>
    <div v-else>
      <div v-if="!confirmationResult">
        ● 전화번호 인증
        <input v-model="phoneNumber" placeholder="전화번호" style="height: 40px; width: 100%;">
        <div class="appbr">
          <br>
        </div>
        <button @click="sendVerificationCode" class="order_button" id="sign-in-button">인증 코드 전송</button>
        <!-- <div id="recaptcha-container"></div> -->
      </div>
      <div v-else>
        ● 인증번호 입력
        <input v-model="verificationCode" placeholder="인증 코드" style="height: 40px; width: 100%;">
        <div class="appbr">
          <br>
        </div>
        <button @click="verifyCode" class="order_button">인증 확인</button>
      </div>
    </div>
  </div>
</template>

<script>
import { getAuth, RecaptchaVerifier, signInWithPhoneNumber } from 'firebase/auth'; // 수정된 import
import { getFirestore, collection, addDoc, getDocs, updateDoc, doc, where, query } from "firebase/firestore";


export default {
  data() {
    return {
      phoneNumber: '',
      verificationCode: '',
      recaptchaVerifier: null,
      confirmationResult: null,
      authenticated: false,
      user: null,
    };
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);

    // 페이지 로드 시 로그인 상태 확인
    this.authenticated = localStorage.getItem('authenticated');
    this.user = JSON.parse(localStorage.getItem('user')); // 유저 정보를 JSON으로 파싱

    if (this.authenticated === 'true' && this.user) {
      console.log('이미 인증된 상태입니다.');
    }else{
      console.log('인증이 필요한 상태입니다.');
      // 홈 페이지로 이동
      this.$router.push({ name: 'main' }); // 'home'은 홈 라우터의 이름이라 가정
    }
  },
  methods: {
    // 인증 코드 전송 버튼 클릭 시
    async sendVerificationCode() {
      const auth = getAuth();
      auth.languageCode = 'ko';

      this.recaptchaVerifier = new RecaptchaVerifier(auth, 'sign-in-button', {
        'size': 'invisible',
        'callback': (response) => {
          onSignInSubmit();
        },
        'expired-callback': () => {
        }
      });

      // window.recaptchaVerifier = new RecaptchaVerifier(auth, 'recaptcha-container', {});
      const appVerifier = this.recaptchaVerifier;

      // 블랙리스트 관리
      const firestore = getFirestore();
      const blacklistCollection = collection(firestore, "blacklist");
      const q = query(blacklistCollection, where("phoneNumber", "==", this.phoneNumber));
      const blacklistSnapshot = await getDocs(q);

      const phonesCollection = collection(firestore, "phones");
      const q2 = query(phonesCollection, where("phoneNumber", "==", this.phoneNumber));
      const phonesSnapshot = await getDocs(q2);

      if (blacklistSnapshot.docs.length > 0) {
        console.log('블랙리스트에 등록된 번호입니다.');
        alert('블랙리스트에 등록된 번호입니다.');
        return;
      } else {
        signInWithPhoneNumber(auth, '+82' + this.phoneNumber, appVerifier)
        .then((confirmationResult) => {
          console.log('인증 코드가 전송되었습니다.');
          alert("인증번호가 전송되었습니다.");
          this.confirmationResult = confirmationResult;

          this.addPhone();
        }).catch((error) => {
          console.error('인증 코드 전송 실패:', error);
          this.confirmationResult = null;
        });
      }
    },
    // 인증 코드 확인 버튼 클릭 시
    async verifyCode() {
      const auth = getAuth();
      auth.languageCode = 'ko';

      this.confirmationResult.confirm(this.verificationCode).then((result) => {
        const user = result.user;
        alert("인증되었습니다.");
        console.log('인증이 되었습니다.');
        this.addPhone2();

        // 로컬스토리지에 인증 상태 저장
        localStorage.setItem('authenticated', 'true');
        // 유저 정보를 JSON 형태로 저장
        localStorage.setItem('user', JSON.stringify(user));
        // 페이지 새로고침
        window.location.reload();
      }).catch((error) => {
        console.log('인증 실패:', error);
      });
    },
    async addPhone(){
      const firestore = getFirestore();

      // 인증 시도 시 사용자 정보 Firestore에 저장
      try {
        // Firestore에 사용자 정보 저장
        const phonesCollection = collection(firestore, "phones");
        const newPhones = {
          phoneNumber: this.phoneNumber,
          authenticated: false,
        };
        await addDoc(phonesCollection, newPhones);
        console.log('데이터 추가 완료!');
      } catch (error) {
        const errorCode = error.code;
        const errorMessage = error.message;
        console.error(errorCode, errorMessage);
        console.log('데이터 추가 오류!');
      }
    },
    async addPhone2(){
      const firestore = getFirestore();

      // 인증 시도 시 사용자 정보 Firestore에 저장
      try {
        const phonesCollection = collection(firestore, "phones");
        
        // 이미 해당 전화번호가 있는지 검사
        const q = query(phonesCollection, where("phoneNumber", "==", this.phoneNumber));
        const querySnapshot = await getDocs(q);
        if (!querySnapshot.empty) {
          // 이미 있는 경우 해당 문서의 ID를 가져와 업데이트
          const docId = querySnapshot.docs[0].id;
          const docRef = doc(phonesCollection, docId);
          
          const newPhones = {
            phoneNumber: this.phoneNumber,
            authenticated: true,
          };
          await updateDoc(docRef, newPhones);
          console.log('데이터 업데이트 완료!');
        } else {
          // 없는 경우 새로 추가 (addPhone과 동일)
          const newPhone = {
            phoneNumber: this.phoneNumber,
            authenticated: true,
          };
          await addDoc(phonesCollection, newPhone);
          console.log('데이터 추가 완료!');
        }
      } catch (error) {
        const errorCode = error.code;
        const errorMessage = error.message;
        console.error(errorCode, errorMessage);
        console.log('데이터 수정 오류!');
      }
    },
  },
};
</script>

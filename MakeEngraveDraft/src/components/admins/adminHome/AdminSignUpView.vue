<template>
  <div class="">
    <h3>SignUp</h3>
    <input type="text" v-model="email" placeholder="email">
    <input type="password" v-model="password" placeholder="password">
    <button v-on:click="signUp">가입하기</button>
  </div>
</template>

<script>
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";
import { getFirestore, collection, addDoc } from "firebase/firestore";

export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    async signUp() {
      const auth = getAuth();
      const email = this.email + '@gmail.com';
      const firestore = getFirestore();

      try {
        const userCredential = await createUserWithEmailAndPassword(
          auth,
          email,
          this.password
        );

        const user = userCredential.user;

        // Firestore에 사용자 정보 저장
        const userCollection = collection(firestore, "users");
        const newUser = {
          uid: user.uid,
          email: user.email,
          // 추가 사용자 정보를 여기에 추가
        };
        await addDoc(userCollection, newUser);

        // 회원가입 성공 후 다른 로직 수행
        alert('관리자 추가 완료!');

      } catch (error) {
        const errorCode = error.code;
        const errorMessage = error.message;
        console.error(errorCode, errorMessage);
        alert('관리자 추가 오류!');
      }
    }
  },
};
</script>

<style>

</style>
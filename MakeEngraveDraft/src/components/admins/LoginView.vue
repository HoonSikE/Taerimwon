<template>
  <div class="app">
    <h3>Login</h3>
    <input type="text" v-model="email" placeholder="email">
    <input type="password" v-model="password" placeholder="password"><br>
    <button v-on:click="login">로그인</button><br>
    <br>
    <router-link :to="'/admin/adminHome'">adminHome 이동(로그인 생략)</router-link>
  </div>
</template>

<script>
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    login() {
      const auth = getAuth();
      const email = this.email + '@gmail.com';
      signInWithEmailAndPassword(auth, email, this.password)
        .then((userCredential) => {
          // Signed in 
          const user = userCredential.user;
          this.$router.replace('adminHome');
          alert('로그인 성공!');
        })
        .catch((error) => {
          const errorCode = error.code;
          const errorMessage = error.message;
          alert('로그인 실패!');
        });
    }
  },
};
</script>

<style>
</style>
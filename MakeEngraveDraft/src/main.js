import './assets/main.css';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { store } from './store';
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";


// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyAaeQCTafCYMdGxGgPm8r20ycbX6QQQCTQ",
    authDomain: "makeengravedraft.firebaseapp.com",
    projectId: "makeengravedraft",
    storageBucket: "makeengravedraft.appspot.com",
    messagingSenderId: "229948173857",
    appId: "1:229948173857:web:c806b0c64c43b402073d60",
    measurementId: "G-01VD34GRQ9"
  };

// Firebase 초기화
const firebaseApp = initializeApp(firebaseConfig);
const auth = getAuth(firebaseApp);

// Vue 애플리케이션 생성
const app = createApp(App);

// Vuex 상태 복원
const savedState = JSON.parse(localStorage.getItem('vuexState'));
if (savedState) {
  store.replaceState(savedState);
}

// Vue 플러그인 등록 및 마운트
app.use(router);
app.use(store);
app.mount('#app');
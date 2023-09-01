import './assets/main.css';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { store } from './store';
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from 'firebase/storage';


// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyA9WYiWpvjINaK8-fa4RUspXs2fG1zpzLg",
  authDomain: "taerimwon-b4e5c.firebaseapp.com",
  projectId: "taerimwon-b4e5c",
  storageBucket: "taerimwon-b4e5c.appspot.com",
  messagingSenderId: "369114470374",
  appId: "1:369114470374:web:0bff89cbfc5f9309ee2827",
  measurementId: "G-Z927S1NZVD"
};

// Vue 애플리케이션 생성
const app = createApp(App);

// Vue 플러그인 등록 및 마운트
app.use(router);
app.use(store);
app.mount('#app');

// Vuex 상태 복원
const savedState = JSON.parse(localStorage.getItem('vuexState'));
if (savedState) {
  store.replaceState(savedState);
}

// Firebase 초기화
const firebaseApp = initializeApp(firebaseConfig);
const auth = getAuth(firebaseApp);
// const firestore = getFirestore(firebaseApp);
// Storage 인스턴스 생성
// const storage = getStorage(app);
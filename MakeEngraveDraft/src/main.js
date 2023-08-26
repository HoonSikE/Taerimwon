import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// import store from './store'
import { initializeApp } from "firebase/app"
// import { getAnalytics } from "firebase/analytics";
import { getAuth } from "firebase/auth"

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

// Initialize Firebase
const firebaseApp = initializeApp(firebaseConfig)
// const analytics = getAnalytics(firebaseApp);
const auth = getAuth(firebaseApp)

const app = createApp(App)

app.use(router);
// app.use(store);

app.mount('#app');
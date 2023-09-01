<template>
  <div>
    <header class="app">
      <div class="text-align-center" >
        <router-link :to="{name: 'main'}" @click.native="updateRouteData()" class="logo">
          <img alt="logo1" src="@/assets/images/logo.png"/>
          <img alt="logo2" src="@/assets/images/homeLogo.png"/>
        </router-link>
      </div>
      <div class="router-view-wrapper">
        <nav>
          <RouterLink to="/" @click.native="updateRouteData()">Home</RouterLink> |
          <RouterLink to="/engrave" @click.native="updateRouteData()">Engrave</RouterLink> |
          <RouterLink to="/about" @click.native="updateRouteData()">About</RouterLink>
        </nav>
      </div>
    </header>
    <body>
      <router-view></router-view>
    </body>
    <hr>
    <footer class="app" @click="handleFooterClick">
      copyright @태림원. All Rights Reserved.
    </footer>
  </div>
</template>

<script>
import { getFirestore, collection, addDoc, getDocs, updateDoc, doc, where, query } from "firebase/firestore";

import { mapMutations } from 'vuex';
export default {
  name: 'App',
  data() {
    return {
      leaderName: '',
      leaderPhone: '',
      leaderDepartment: '',
      clickCount: 0
    };
  },
  mounted() {
    // 페이지 로드 시 로그인 상태 확인
    this.authenticated = localStorage.getItem('authenticated');
    this.user = JSON.parse(localStorage.getItem('user')); // 유저 정보를 JSON으로 파싱

    if (this.authenticated === 'true' && this.user) {
      // console.log('이미 인증된 상태입니다.');
    }else{
      // console.log('인증이 필요한 상태입니다.');
      // 홈 페이지로 이동
      this.$router.push({ name: 'main' }); // 'home'은 홈 라우터의 이름이라 가정
    }

    // 마우스 우클릭 막기
    document.addEventListener("contextmenu", function(e) {
      e.preventDefault();
    });

    // 텍스트 선택 막기 (위의 CSS 스타일과 중복됩니다)
    document.addEventListener("selectstart", function(e) {
      e.preventDefault();
    });

    // 캡처 막기 (일부 브라우저에서만 동작합니다)
    document.addEventListener("keydown", function(e) {
      if (e.key === "PrintScreen" || e.key === "F12") {
        e.preventDefault();
      }
    });

    // 로컬 스토리지에서 정보 가져오기
    const savedInfo = localStorage.getItem('savedInfo');
    if (savedInfo) {
      const info = JSON.parse(savedInfo);
      this.leaderName = info.leaderName;
      this.leaderPhone = info.leaderPhone;
      this.leaderDepartment = info.leaderDepartment;
      this.saveInfo = true;
    };
  },
  methods: {
    ...mapMutations(['resetState']),
    updateRouteData() {
      this.resetState();
    },
    handleFooterClick() {
      this.clickCount++;

      if (this.clickCount === 3) {
        if(this.leaderName === '양청우')
          this.goToAdmin();
        // 리셋 클릭 횟수
        this.clickCount = 0;
      }
    },
    goToAdmin() {
      this.$router.push('/admin'); // admin 페이지로 이동
    },
    async checkPhone(){
      // phones 컬렉션에서 이미 승인된 번호인지 확인
      const phonesCollection = collection(firestore, "phones");
      const phonesQuery = query(phonesCollection, where("phoneNumber", "==", this.phoneNumber), where("authenticated", "==", true));
      const phonesSnapshot = await getDocs(phonesQuery);

      if (phonesSnapshot.docs.length > 0) {
        console.log('이미 승인된 번호입니다.');
        // 로컬스토리지에 인증 상태 저장
        localStorage.setItem('authenticated', 'true');
        // 유저 정보를 JSON 형태로 저장
        // 유저 정보를 어떻게 가져올 것인지에 따라 아래 코드 수정 필요
        const user = {
          // 유저 정보를 객체 형태로 작성
        };
        localStorage.setItem('user', JSON.stringify(user));
        // 페이지 새로고침
          window.location.reload();
        return;
      }
    }
  }
};
</script>


<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
  /* background-color: rgb(242, 240, 240); */
}

.logo {
  display: inline-block;
}

.logo img {
  height: 8vh;
  margin-right: -15px;
}

.router-view-wrapper {
  margin-top: 1rem; /* 원하는 간격 크기 설정 */
}

nav {
  width: 100%;
  font-size: 16px;
  text-align: center;
  /* margin-top: 2rem; */
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  
  text-decoration: none; /* 밑줄 제거 */
  color: rgb(47, 47, 47);
}

nav a.router-link-exact-active {
  color: rgb(47, 47, 47);
  border-bottom: 4px solid rgb(47, 47, 47);
}

footer {
  padding: 20px 0; /* 위아래 여백 설정 */
  font-size: 14px;
  text-align: center;
  font-family: "BMEULJIROTTF";
  /* background-color: aqua; */
}
</style>

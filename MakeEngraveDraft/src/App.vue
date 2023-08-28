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
    <div>
      <router-view></router-view>
    </div>
    <hr>
    <footer class="app" @click="handleFooterClick">
      copyright @태림원. All Rights Reserved.
    </footer>
  </div>
</template>

<script>
import { mapMutations } from 'vuex';
export default {
  name: 'App',
  data() {
    return {
      clickCount: 0
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
        if(this.$store.getters.getName1 === '양청우')
          this.goToAdmin();
        // 리셋 클릭 횟수
        this.clickCount = 0;
      }
    },
    goToAdmin() {
      this.$router.push('/admin'); // admin 페이지로 이동
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

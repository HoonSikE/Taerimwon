<template>
  <div id="app">
    <header>
      <div class="text-align-center" >
        <router-link :to="{name: 'main'}" @click.native="updateRouteData()">
          <img alt="logo" class="logo" src="@/assets/images/logo.png" width="160" height="160" />
        </router-link>
      </div>
      <div class="router-view-wrapper">
        <nav>
          <!-- <RouterLink to="/">Home</RouterLink> -->
          <!-- <RouterLink to="/engrave">Engrave</RouterLink> -->
          <!-- <RouterLink to="/about">About</RouterLink> -->
        </nav>
      </div>
    </header>
    <body>
      <router-view></router-view>
    </body>
    <hr>
    <footer>
      <div @click="handleFooterClick">
        ● 문의사항<br>
        ☎️ 연락처: 010-4509-7485<br>
        📨 Email: hoongrammer@gmail.com
      </div>
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
/* header {
  line-height: 1.5;
  max-height: 100vh;
} */

/* .logo { */
  /* display: block; */
  /* margin: 0 auto 2rem; */
/* } */

.text-align-center {
  text-align: center;
}

.router-view-wrapper {
  margin-top: 1rem; /* 원하는 간격 크기 설정 */
}

footer {
  font-size: 14px;
  font-family: "BMEULJIROTTF";
}

/* nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
} */

/* @media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
} */
</style>

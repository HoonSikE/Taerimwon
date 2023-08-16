<template>
  <div id="app">
    <header>
      <div class="text-align-center" >
        <RouterLink to="/">
        <img alt="logo" class="logo" src="@/assets/images/logo.png" width="160" height="160" />
        </RouterLink>
      </div>
      <div class="wrapper">
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
      <div>
        ● 문의사항<br>
        ☎️ 연락처: 010-4509-7485<br>
        📨 Email: hoongrammer@gmail.com
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "App",
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

@font-face {
    font-family: '궁서';
    src: url('../assets/fonts/궁서.TTF');
}

@font-face {
    font-family: '한양해서';
    src: url('../assets/fonts/한양해서.TTF') format('truetype');
}

@font-face {
    font-family: 'NotoSerifTC';
    src: url('../assets/fonts/NotoSerifTC-Bold.otf');
}

@font-face {
    font-family: 'CENTURY';
    src: url('../assets/fonts/CENTURY.TTF') format('truetype');
}

@font-face {
    font-family: 'BMEULJIROTTF';
    src: url('../assets/fonts/BMEULJIROTTF.ttf');
}
</style>

<template>
  <div class="">
    <h1>각인 주문하기</h1>
    각인 안내<br/>
    <!-- 가로 스크롤 가능한 컨테이너 -->
    <div class="scroll-container">
      <div class="link-container">
        <RouterLink to="/engrave/create/1" class="link-item" @click="selectOption(1)">
          <img src="../../assets/images/example/일반.png" width="80" height="100" alt="일반">
          <span class="selectText">각인[일반]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/1" class="link-item" @click="selectOption(2)">
          <img src="../../assets/images/example/일반(형제).png" width="80" height="100" alt="일반/형제">
          <span class="selectText">각인[일반(형제)]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/2" class="link-item" @click="selectOption(3)">
          <img src="../../assets/images/example/기독교.png" width="80" height="100" alt="기독교">
          <span class="selectText">각인[기독교]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/3" class="link-item" @click="selectOption(4)">
          <img src="../../assets/images/example/불교.png" width="80" height="100" alt="불교">
          <span class="selectText">각인[불교]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/4" class="link-item" @click="selectOption(5)">
          <img src="../../assets/images/example/천주교.png" width="80" height="100" alt="천주교">
          <span class="selectText">각인[천주교]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/3" class="link-item" @click="selectOption(6)">
          <img src="../../assets/images/example/불교(법명).png" width="80" height="100" alt="불교(법명)">
          <span class="selectText">각인[불교(법명)]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/2" class="link-item" @click="selectOption(7)">
          <img src="../../assets/images/example/기독교(직분x).png" width="80" height="100" alt="기독교(직분x)">
          <span class="selectText">각인[기독교(직분x)]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/4" class="link-item" @click="selectOption(8)">
          <img src="../../assets/images/example/천주교(세례명x).png" width="80" height="100" alt="천주교(세례명x)">
          <span class="selectText">각인[천주교(세례명x)]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/5" class="link-item" @click="selectOption(9)">
          <img src="../../assets/images/example/sgi.png" width="80" height="100" alt="SGI">
          <span class="selectText">각인[SGI]</span>
        </RouterLink>
        <RouterLink to="/engrave/create/6" class="link-item" @click="selectOption(10)">
          <img src="../../assets/images/example/묘법.png" width="80" height="100" alt="묘법">
          <span class="selectText">각인[묘법]</span>
        </RouterLink>
      </div>
    </div>
    <hr>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
};
</script>

<style>
/* @media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
} */
.scroll-container {
  max-width: 100%; /* 화면 너비 이내로 제한 */
  overflow-x: auto; /* 가로 스크롤 가능하도록 설정 */
  white-space: nowrap; /* 자식 요소들이 한 줄로 나란히 표시될 수 있도록 설정 */
}

.link-container {
  /* display: inline-block; */
  display: flex;
}

.link-item {
  display: inline-block;
  text-align: center;
  margin-right: 20px; /* 원하는 간격으로 조정 */
  outline: none; /* 기본 테두리 제거 */
}

.link-item img {
  display: block;
  margin: 0 auto;
  width: 80px;
  height: 100px;
}

.link-item .selectText {
  display: block;
  margin-top: 5px;
  white-space: nowrap;
  /* 글자가 한 줄로 유지되도록 설정 */
}

.link-item:focus {
  /* 클릭되었을 때의 스타일 지정 */
  outline: 2px solid #00ff8c; /* 테두리 스타일 및 색상 설정 */
  background-color: rgb(203, 237, 223);
  box-shadow: 0 0 5px rgba(167, 167, 230, 0.5); /* 박스 쉐도우 효과 추가 */
}
</style>
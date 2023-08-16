<template>
  <div class="">
    <div class="title">
      각인 생성하기
    </div>
    <div class="title2">
      ● 각인 종류
    </div>
    <!-- 가로 스크롤 가능한 컨테이너 -->
    <div class="text-align-center">
      <div class="scroll-container">
        <div class="link-container">
          <RouterLink to="/engrave/engraveCreate/engraveDetail?type=일반&selectedType=일반&showRouterView=true" class="link-item"
              :class="{ selected: selectedType === '일반' }"
              @click="selectedType = '일반'">
            <img src="../../assets/images/engrave/example/일반.png" width="80" height="130" alt="일반">
            <span class="selectText">[일반]</span>
          </RouterLink>
          <RouterLink to="/engrave/engraveCreate/engraveDetail?type=기독교&selectedType=기독교&showRouterView=true" class="link-item"
              :class="{ selected: selectedType === '기독교' }"
              @click="selectedType = '기독교'">
            <img src="../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="기독교">
            <span class="selectText">[기독교]</span>
          </RouterLink>
          <RouterLink to="/engrave/engraveCreate/engraveDetail?type=불교&selectedType=불교&showRouterView=true" class="link-item"
              :class="{ selected: selectedType === '불교' }"
              @click="selectedType = '불교'">
            <img src="../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교">
            <span class="selectText">[불교]</span>
          </RouterLink>
          <RouterLink to="/engrave/engraveCreate/engraveDetail?type=천주교&selectedType=천주교&showRouterView=true" class="link-item"
              :class="{ selected: selectedType === '천주교' }"
              @click="selectedType = '천주교'">
            <img src="../../assets/images/engrave/example/천주교.png" width="80" height="130" alt="천주교">
            <span class="selectText">[천주교]</span>
          </RouterLink>
          <RouterLink to="/engrave/engraveCreate/engraveDetail?type=SGI&selectedType=SGI&showRouterView=true" class="link-item"
              :class="{ selected: selectedType === 'SGI' }"
              @click="selectedType = 'SGI'">
            <img src="../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI">
            <span class="selectText">[SGI]</span>
          </RouterLink>
          <RouterLink to="/engrave/engraveCreate/engraveDetail?type=묘법&selectedType=묘법&showRouterView=true" class="link-item"
              :class="{ selected: selectedType === '묘법' }"
              @click="selectedType = '묘법'">
            <img src="../../assets/images/engrave/example/묘법.png" width="80" height="130" alt="묘법">
            <span class="selectText">[묘법]</span>
          </RouterLink>
        </div>
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
      selectedType: '일반', // 초기 선택 타입 설정
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

.text-align-center {
  text-align: center;
  font-family: "BMEULJIROTTF";
}

.image-text-container {
  position: relative;
  width: 200px;
  /* width: 30%; */
  /* height: 80%; */
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: blue; */
}

.title {
  text-align: center;
  color: #e92d62;
  font-size: 25px;
  font-family: "BMEULJIROTTF";
}

.title2 {
  text-align: left;
  color: #baca11;
  font-size: 20px;
  font-family: "BMEULJIROTTF";
}

.scroll-container {
  max-width: 100%; /* 화면 너비 이내로 제한 */
  overflow-x: auto; /* 가로 스크롤 가능하도록 설정 */
  white-space: nowrap; /* 자식 요소들이 한 줄로 나란히 표시될 수 있도록 설정 */
  background-color: #cfcfcf; /* 선택 시 배경색을 변경 */
  border-radius: 5px;

  padding: 3px;
  margin-top: 2px;
  margin-bottom: 6px;
}

/* .link-container { */
  /* display: inline-block; */
  /* display: flex; */
/* } */

.link-item {
  display: inline-block;
  text-align: center;
  margin-right: 12px; /* 원하는 간격으로 조정 */
  outline: none; /* 기본 테두리 제거 */
  cursor: pointer;

  margin: 3px;
  padding: 3px;
  padding-left: 5px;
  padding-right: 5px;
  background-color: #212121; /* 선택 시 배경색을 변경 */
  border-radius: 5px;
}

.link-item img {
  display: block;
  margin: 0 auto;
  width: 80px;
  /* height: 100px; */
}

.link-item .selectText {
  /* display: block; */
  margin-top: 5px;
  color: #b50303;
  font-family: "BMEULJIROTTF";
  font-size: 16px;
  white-space: nowrap;
  /* 글자가 한 줄로 유지되도록 설정 */
}

.link-item.selected {
  border: 3px solid #000000; /* 선택 시 보더 스타일을 추가하여 선택 표시 */
  background-color: #545454a6; /* 선택 시 배경색을 변경 */
}
</style>
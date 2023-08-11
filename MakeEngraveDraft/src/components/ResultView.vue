<template>
  <div class="">
    <router-link :to="'/engrave/engraveCreate/engraveDetail?type='
           + type + '&selectedType=' + selectedType + '&showRouterView=true'">
          [이전 페이지]
    </router-link>
    <br>
    각인 예시
    <br>
    <!-- 일반 -->
    <div class="image-text-container" v-if="type === '일반'">
      <div class="image-container">
        <div v-if="selectedType === '일반'">
          <img src="../assets/images/engrave/background/일반(공).png" width="100%" alt="일반">
        </div>
        <div v-if="selectedType === '형제'">
          <img src="../assets/images/engrave/background/일반형제(공).png" width="100%" alt="형제">
        </div>
      </div>
      <div class="text-container">
        <div v-if="selectedType === '일반'">
          <div class="resultTextHorizontal">
            <span class="resultText1">
              <span class="resultText1_2">{{date1_1}}</span>
              <span class="resultText1_3">●</span>
              <span class="resultText1_2">{{date1_2}}</span>
              <span class="resultText1_3">●</span>
              <span class="resultText1_2">{{date1_3}}</span>
              <span class="span1_1">陽</span>
            </span>
            <span class="resultText2">
              <span>{{$route.query.name1}}</span>
            </span>
            <span class="resultText1">
              <span class="resultText1_2">{{date2_1}}</span>
              <span class="resultText1_3">●</span>
              <span class="resultText1_2">{{date2_2}}</span>
              <span class="resultText1_3">●</span>
              <span class="resultText1_2">{{date2_3}}</span>
              <span class="span1_1">陽</span>
            </span>
          </div>
        </div>
        <div v-if="selectedType === '직분'">
          일반(형제)
        </div>
      </div>
    </div>
    <!-- 기독교 -->
    <div class="image-text-container" v-if="type === '기독교'">
      <div class="image-container">
        <img src="../assets/images/engrave/background/기독교(공).png" width="100%" alt="기독교">
      </div>
      <div class="text-container">
        <div v-if="selectedType === '기독교'">
          기독교
        </div>
        <div v-if="selectedType === '직분'">
          기독교(직분)
        </div>
      </div>
    </div>
    <!-- 불교 -->
    <div class="image-text-container" v-if="type === '불교'">
      <div class="image-container">
        <img src="../assets/images/engrave/background/불교(공).png" width="100%" alt="불교">
      </div>
      <div class="text-container">
        <div v-if="selectedType === '불교'">
          불교
        </div>
        <div v-if="selectedType === '법명'">
          불교(법명)
        </div>
      </div>
    </div>
    <!-- 천주교 -->
    <div class="image-text-container" v-if="type === '천주교'">
      <div class="image-container">
        <img src="../assets/images/engrave/background/천주교(공).png" width="100%" alt="천주교">
      </div>
      <div class="text-container">
        <div v-if="selectedType === '천주교'">
          천주교
        </div>
        <div v-if="selectedType === '세례명'">
          천주교(세례명)
        </div>
      </div>
    </div>
    <!-- SGI -->
    <div class="image-text-container" v-if="type === 'SGI'">
      <div class="image-container">
        <img src="../assets/images/engrave/background/sgi(공).png" width="100%" alt="SGI">
      </div>
      <div class="text-container">
        <div v-if="selectedType === 'SGI'">
          SGI
        </div>
      </div>
    </div>
    <!-- 묘법 -->
    <div class="image-text-container" v-if="type === '묘법'">
      <div class="image-container">
        <img src="../assets/images/engrave/background/묘법(공).png" width="100%" alt="묘법">
      </div>
      <div class="text-container">
        <div v-if="selectedType === '묘법'">
          묘법
        </div>
      </div>
    </div>
    <hr>
    <!-- 위패 -->
    <div class="image-text-container">
      <div class="image-container">
        <div v-if="selectedType2 !== '없음'">
          위패 예시
          <div v-if="name0 !== '없음'">
            <div v-if="type === '일반'">
              <img src="../assets/images/memorialTablet/background/본관출력시안/일반(본관출력시안).png" width="100%" alt="일반">
            </div>
            <div v-if="type === '기독교'">
              <img src="../assets/images/memorialTablet/background/본관출력시안/기독교(본관출력시안).png" width="100%" alt="기독교">
            </div>
            <div v-if="type === '불교'">
              <img src="../assets/images/memorialTablet/background/본관출력시안/불교(본관출력시안).png" width="100%" alt="불교">
            </div>
            <div v-if="type === '천주교'">
              <img src="../assets/images/memorialTablet/background/본관출력시안/천주교(본관출력시안).png" width="100%" alt="천주교">
            </div>
          </div>
          <div v-else>
            <div v-if="type === '일반'">
              <img src="../assets/images/memorialTablet/background/이름출력시안/일반(이름출력시안).png" width="100%" alt="일반">
            </div>
            <div v-if="type === '기독교'">
              <img src="../assets/images/memorialTablet/background/이름출력시안/기독교(이름출력시안).png" width="100%" alt="기독교">
            </div>
            <div v-if="type === '불교'">
              <img src="../assets/images/memorialTablet/background/이름출력시안/불교(이름출력시안).png" width="100%" alt="불교">
            </div>
            <div v-if="type === '천주교'">
              <img src="../assets/images/memorialTablet/background/이름출력시안/천주교(이름출력시안).png" width="100%" alt="천주교">
            </div>
          </div>
          <hr>
        </div>
      </div>
      <div class="text-container">
        <div v-if="name0 !== '없음'">
          <div v-if="type === '일반'">
            {{name0}}
          </div>
          <div v-if="type === '기독교'">
            {{name0}}
          </div>
          <div v-if="type === '불교'">
            {{name0}}
          </div>
          <div v-if="type === '천주교'">
            {{name0}}
          </div>
        </div>
        <div v-else>
          <div v-if="type === '일반'">
            {{name1}}
          </div>
          <div v-if="type === '기독교'">
            {{name1}}
          </div>
          <div v-if="type === '불교'">
            {{name1}}
          </div>
          <div v-if="type === '천주교'">
            {{name1}}
          </div>
        </div>
      </div>
    </div>
    <br>
    <div>
        종류: {{type}}
        <br>
        본관: {{name0}}
        <br>
        이름: {{name1}}
        <br>
        이름2: {{name2}}
        <br>
        출생일: {{date1}} , {{date1Type}}
        <br>
        사망일: {{date2}} , {{date2Type}}
        <br>
        종류: {{selectedType}}
        <br>
        종류: {{selectedType2}}
      <br>
    </div>
    <div>
      <!-- <router-link :to="'/engrave/result?' + 'name=' + $route.query.name + '&date1=' + $route.query.date1 + '&date2='+ $route.query.date2">전체 화면</router-link> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: this.$route.query.type,
      name0: this.$route.query.name0,
      name1: this.$route.query.name1,
      name2: this.$route.query.name2,
      date1: this.$route.query.date1,
      date1Type: this.$route.query.date1Type,
      date2: this.$route.query.date2,
      date2Type: this.$route.query.date2Type,
      selectedType: this.$route.query.selectedType, // 초기 선택 타입 설정
      selectedType2: this.$route.query.selectedType2, // 초기 선택 타입 설정

      date1_1: this.$route.query.date1.substr(0,4),
      date1_2: this.$route.query.date1.substr(5,2),
      date1_3: this.$route.query.date1.substr(8,2),
      date2_1: this.$route.query.date2.substr(0,4),
      date2_2: this.$route.query.date2.substr(5,2),
      date2_3: this.$route.query.date2.substr(8,2),
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

.image-text-container {
  position: relative;
  width: 50%;
  /* height: 80%; */
  background-repeat: no-repeat;
  background-position: center;
}

.image-container img {
  width: 100%;
  height: 100%;
}

.text-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  color: black;
  font-family: "궁서체";
  font-weight: bold;
  font-size: 50px;
  writing-mode: vertical-rl; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
}
</style>

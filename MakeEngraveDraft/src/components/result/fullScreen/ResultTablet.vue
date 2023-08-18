<template>
  <div class="">
    <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
      <router-link :to="'/engrave/result?' + 'type=' + type 
                  + '&name0=' + name0 + '&name1=' + name1 + '&name2='+ name2 
                  + '&date1=' + date1 + '&date1Type=' + date1Type 
                  + '&date2=' + date2 + '&date2Type=' + date2Type
                  + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title4">👉 [이전 페이지]</router-link>
    </div>
    <div v-else>
      <router-link :to="'/engrave/result?' + 'type=' + type 
                  + '&name0=' + '없음' + '&name1=' + name1 + '&name2='+ name2 
                  + '&date1=' + date1 + '&date1Type=' + date1Type 
                  + '&date2=' + date2 + '&date2Type=' + date2Type
                  + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title4">👉 [이전 페이지]</router-link>
    </div>

    <div class="container-tablet">
      <!-- 위패 -->
      <div class="image-text-container2-tablet" v-if="selectedType2 !== '없음'">
        <div class="title2">● 위패 예시
          <div class="title6">&nbsp;&nbsp;- {{type}} 
            <span v-if="name0 !== '없음'"> [본관] </span>
          </div>
        </div>
        <div class="image-text-container1_1-tablet">
          <div class="image-container2-tablet">
            <div v-if="name0 !== '없음'">
              <div v-if="type === '일반'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/일반(본관출력시안).png" height="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/기독교(본관출력시안).png" height="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/불교(본관출력시안).png" height="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/천주교(본관출력시안).png" height="100%" alt="천주교">
              </div>
            </div>
            <div v-else>
              <div v-if="type === '일반'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/일반(이름출력시안).png" height="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/기독교(이름출력시안).png" height="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/불교(이름출력시안).png" height="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/천주교(이름출력시안).png" height="100%" alt="천주교">
              </div>
            </div>
          </div>
          <div class="text-container2-tablet">
            <!-- <span class="resultText3"> -->
            <!-- 위패 추가 -->
            <div v-if="selectedType2 !== '없음'">
              <!-- 본관 선택 o -->
              <div v-if="name0 !== '없음'">
                <!-- 일반 -->
                <span class="resultText4_1_1-tablet" v-if="type === '일반'">
                  {{name0}}
                </span>
                <!-- 불교 -->
                <span class="resultText4_1_2-tablet" v-if="type === '불교'">
                  {{name0}}
                </span>
                <!-- 위 글자 -->
                <span class="resultText4_2-tablet" v-if="type === '기독교'">
                  <span class="resultText4_2_2-tablet">{{name2}}</span>
                  <span class="resultText4_2_1-tablet">{{name0}}</span>
                </span>
                <!-- 아래 글자 -->
                <span class="resultText4_3-tablet" v-if="type === '천주교'">
                  <span class="resultText4_3_1-tablet">{{name0}}</span>
                  <span class="resultText4_3_2-tablet">{{name2}}</span>
                </span>
              </div>
              <!-- 본관 선택 x-->
              <div v-else>
                <!-- 일반 -->
                <span class="" v-if="type === '일반' || type === '불교'">
                  <span class="resultText3_1-tablet" v-if="name1.length === 2 || name1.length === 3">
                    {{encodedName1}}
                  </span>
                  <span class="resultText3_1-tablet-4" v-if="name1.length === 4">
                    {{encodedName1}}
                  </span>
                </span>
                <!-- 위 글자 -->
                <span class="resultText3_2-tablet" v-if="type === '기독교'">
                  <span class="resultText3_2_2-tablet">{{name2}}</span>
                  <span class="resultText3_2_1-tablet" v-if="name1.length === 2 || name1.length === 3">
                    {{encodedName1}}
                  </span>
                  <span class="resultText3_2_1-tablet-4" v-if="name1.length === 4">
                    {{encodedName1}}
                  </span>
                </span>
                <!-- 아래 글자 -->
                <span class="resultText3_3-tablet" v-if="type === '천주교'">
                  <span class="resultText3_3_1-tablet" v-if="name1.length === 2 || name1.length === 3">
                    {{encodedName1}}
                  </span>
                  <span class="resultText3_3_1-tablet-4" v-if="name1.length === 4">
                    {{encodedName1}}
                  </span>
                  <span class="resultText3_3_2-tablet">{{name2}}</span>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
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
  computed: {
    encodedName1() {
      const trimmedName1 = this.name1.trim();

      if(trimmedName1.length === 2){
        return trimmedName1.substr(0, 1) + " " + trimmedName1.substr(1, 1);
      }
      return trimmedName1;
    },
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
.container-tablet {
  display: flex;
  /* justify-content: space-between; 가로로 나란히 정렬 */
  justify-content: center; /* 가운데 정렬 추가 */
  align-items: center;

  margin: 0 auto;
  max-width: 1200px; /* 원하는 최대 너비 설정 */
  /* background-color: darkblue; */

  /* text-align: center; */
}

.image-text-container1_1-tablet {
  display: flex;
}

.image-text-container2-tablet {
  position: relative;
  width: 60vw;
  height: 60vh;
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(0, 255, 17); */
}

.image-container2-tablet img {
  width: 60vw;
  height: auto;
}

.text-container2-tablet {
  width: 60vw;
  height: auto;

  transform: translate(-100%, 0%);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}
/* 위패 */
/* 일반 */
.resultText3_1-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 24vw;
  margin-top: 27%;
  letter-spacing:0.9em;

  height: auto;
  width: 60vw;

  /* background-color: rgba(251, 0, 0, 0.384); */
}
.resultText3_1-tablet-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 24vw;
  margin-top: 24%;
  letter-spacing:0.5em;

  height: auto;
  width: 60vw;

  /* background-color: rgba(251, 0, 0, 0.384); */
}
/* 위 글자 (직분)*/
.resultText3_2-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: 19%;

  height: auto;
  width: 60vw;
  /* background-color: rgba(221, 255, 0, 0.443); */
}
/* 이름1 */
.resultText3_2_1-tablet {
  font-size: 24.2vw;
  margin-top: 5%;
  letter-spacing:0.6em;

  font-weight: 900;
  /* background-color: rgba(8, 218, 127, 0.473); */
}
.resultText3_2_1-tablet-4 {
  font-size: 24.2vw;
  margin-top: 2.5%;
  letter-spacing:0.25em;

  font-weight: 900;
  /* background-color: rgba(8, 218, 127, 0.473); */
}
/* 이름2 */
.resultText3_2_2-tablet {
  font-size: 11.8vw;
  margin-top: 4%;
  letter-spacing:-0.05em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgba(246, 71, 2, 0.414); */
}
/* 아래 글자(세례명) */
.resultText3_3-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: 19%;

  height: auto;
  width: 60vw;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText3_3_1-tablet {
  font-size: 24.6vw;
  margin-top: 8%;
  letter-spacing:0.5em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
.resultText3_3_1-tablet-4 {
  font-size: 24.6vw;
  margin-top: 4%;
  margin-bottom: 1%;
  letter-spacing:0.2em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText3_3_2-tablet {
  font-size: 11.2vw;
  margin-top: -5%;
  letter-spacing:-0.05em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 위패 */
/* 본관 */
/* 일반 */
.resultText4_1_1-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.6vw;
  margin-top: 0%;
  letter-spacing:0.07em;
  
  height: auto;
  width: 60vw;
  /* background-color: rgb(251, 0, 0); */
}
/* 불교 */
.resultText4_1_2-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.6vw;
  margin-top: 9%;
  letter-spacing:-0.03em;
  
  height: auto;
  width: 60vw;
  /* background-color: rgb(251, 0, 0); */
}
/* 위 글자 (직분)*/
.resultText4_2-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -18%;

  height: auto;
  width: 60vw;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_2_1-tablet {
  font-size: 16.8vw;
  margin-top: 0%;
  letter-spacing:-0.02em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText4_2_2-tablet {
  font-size: 12.2vw;
  margin-top: 19%;
  letter-spacing:-0.05em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 아래 글자(세례명) */
.resultText4_3-tablet {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: 0%;

  height: auto;
  width: 60vw;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_3_1-tablet {
  font-size: 16.8vw;
  margin-top: 20%;
  letter-spacing:0.13em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText4_3_2-tablet {
  font-size: 12.2vw;
  margin-top: 0%;
  letter-spacing:-0.05em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
</style>
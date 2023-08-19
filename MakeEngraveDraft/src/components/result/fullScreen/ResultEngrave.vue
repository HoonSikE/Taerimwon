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

    <div class="container-engrave">
      <!-- 각인 -->
      <div class="image-text-container-engrave">
        <div class="title2">● 각인 예시 
          <div class="title6">&nbsp;&nbsp;- {{type}} 
            <span v-if="type !== selectedType"> [{{selectedType}}] </span>
          </div>
        </div>
        <div class="image-text-container1_1-engrave">
          <div class="image-container1-engrave">
            <div v-if="type === '일반' && selectedType === '일반'">
              <img src="../../../assets/images/engrave/background/일반(공).png" width="100%" alt="일반">
            </div>
            <div v-if="type === '일반' && selectedType === '형제'">
              <img src="../../../assets/images/engrave/background/일반형제(공).png" width="100%" alt="형제">
            </div>
            <div v-if="type === '기독교'">
              <img src="../../../assets/images/engrave/background/기독교(공).png" width="100%" alt="기독교">
            </div>
            <div v-if="type === '불교'">
              <img src="../../../assets/images/engrave/background/불교(공).png" width="100%" alt="불교">
            </div>
            <div v-if="type === '천주교'">
              <img src="../../../assets/images/engrave/background/천주교(공).png" width="100%" alt="천주교">
            </div>
            <div v-if="type === 'SGI'">
              <img src="../../../assets/images/engrave/background/sgi(공).png" width="100%" alt="SGI">
            </div>
            <div v-if="type === '묘법'">
              <img src="../../../assets/images/engrave/background/묘법(공).png" width="100%" alt="묘법">
            </div>
          </div>
          <div class="text-container-engrave">
            <span class="resultText1-engrave">
              <span class="resultText1_1-engrave">{{date1_1}}</span>
              <span class="resultText1_2-engrave">•</span>
              <span class="resultText1_1-engrave">{{date1_2}}</span>
              <span class="resultText1_2-engrave">•</span>
              <span class="resultText1_1-engrave">{{date1_3}}</span>
              <span v-if="date1Type==='음력'" class="resultText1_3-engrave">陰</span>
              <span v-if="date1Type==='양력'" class="resultText1_3-engrave">陽</span>
            </span>
            <!-- <div v-if="selectedType === '일반'"> -->
            <!-- 그대로 -->
            <span class="" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교'
                                      || selectedType === '천주교'">
              <span class="resultText2-engrave" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2-engrave-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 아래 글자 1 -->
            <span class="" v-if="selectedType === '형제'">
              <span class="resultText2_1-engrave" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_1-engrave-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 위 글자 1 -->
            <span class="" v-if="selectedType === 'SGI'">
              <span class="resultText2_2-engrave" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_2-engrave-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 위 아래 글자 -->
            <span class="" v-if="selectedType === '묘법'">
              <span class="resultText2_3-engrave" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_3-engrave-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 아래 글자 2 -->
            <span class="resultText2_4-engrave" v-if="selectedType === '세례명'">
              <span class="resultText2_4_1-engrave" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_4_1-engrave-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
              <span class="resultText2_4_2-engrave" v-if="name2.length === 2 || name2.length === 3">
                {{name2}}
              </span>
              <span class="resultText2_4_2-engrave-4" v-if="name2.length === 4">
                {{name2}}
              </span>
              <span class="resultText2_4_2-engrave-5" v-if="name2.length === 5">
                {{name2}}
              </span>
              <span class="resultText2_4_2-engrave-6" v-if="name2.length === 6">
                {{name2}}
              </span>
            </span>
            <!-- 위 글자 2-->
            <span class="resultText2_5-engrave" v-if="selectedType === '직분' || selectedType === '법명'">
              <span class="resultText2_5_2-engrave" v-if="name2.length === 2 || name2.length === 3">
                {{name2}}
              </span>
              <span class="resultText2_5_2-engrave-4" v-if="name2.length === 4">
                {{name2}}
              </span>
              <span class="resultText2_5_1-engrave" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_5_1-engrave-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- </div> -->
            <span class="resultText1-engrave">
              <span class="resultText1_1-engrave">{{date2_1}}</span>
              <span class="resultText1_2-engrave">•</span>
              <span class="resultText1_1-engrave">{{date2_2}}</span>
              <span class="resultText1_2-engrave">•</span>
              <span class="resultText1_1-engrave">{{date2_3}}</span>
              <span v-if="date2Type==='음력'" class="resultText1_3-engrave">陰</span>
              <span v-if="date2Type==='양력'" class="resultText1_3-engrave">陽</span>
            </span>
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
.container-engrave {
  display: flex;
  /* justify-content: space-between; 가로로 나란히 정렬 */
  justify-content: center; /* 가운데 정렬 추가 */
  align-items: center;

  margin: 0 auto;
  max-width: 1200px; /* 원하는 최대 너비 설정 */
  /* background-color: darkblue; */

  /* text-align: center; */
}

.image-text-container-engrave {
  position: relative;
  width: 72vw;
  height: 60vh;
  background-repeat: no-repeat;
  background-position: center;
  margin-right: 10%;
  /* background-color: rgb(226, 245, 100); */
}

.image-text-container1_1-engrave {
  display: flex;
}

.image-container1-engrave img {
  width: 72vw;
  height: auto;
}

.text-container-engrave {
  width: 72vw;
  height: auto;

  transform: translate(-100%, 0%);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}

.resultText1-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: 30%;
  margin-bottom: 7%;

  height: auto;
  width: 24vw;
  /* background-color: rgba(9, 255, 0, 0.551); */
}

.resultText1_1-engrave {
  color: black;
  font-family: "CENTURY";
  font-weight: 900;

  font-size: 8.5vw;
  margin-top: -0.15em;
  letter-spacing:-0.29em;

  width: 100%;
  /* height: 100%; */
  display: flex;
  justify-content: center;
  align-items: center;

  /* margin-top: 1%; */

  /* background-color: rgba(255, 157, 0, 0.551); */
}

.resultText1_2-engrave {
  color: black;
  font-weight: bold;

  font-size: 8.5vw;
  margin-top: -0.5em;
  letter-spacing:-0.2em;

  width: 100%;
  /* height: 100%; */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(0, 30, 255, 0.551); */
}

.resultText1_3-engrave {
  color: black;
  font-family: "한양해서";
  font-weight: 900;
 
  font-size: 12vw;
  /* margin-top: -0em; */
  /* letter-spacing:-0em; */

  width: 100%;
  /* height: 100%; */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(17, 255, 0, 0.551); */
}

/* 이름 */
.resultText2-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  font-size: 18.4vw;
  margin-top: 26%;
  letter-spacing:0.5em;

  height: auto;
  width: 24vw;
  /* background-color: rgba(145, 108, 108, 0.662); */
}
.resultText2-engrave-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  font-size: 18.4vw;
  margin-top: 23%;
  letter-spacing:0.2em;

  height: auto;
  width: 24vw;
  /* background-color: rgba(145, 108, 108, 0.662); */
}

/* 형제 */
.resultText2_1-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.4vw;
  margin-top: 6%;
  letter-spacing:0.26em;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.475); */
}
.resultText2_1-engrave-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 16.4vw;
  margin-top: 4%;
  letter-spacing:-0.02em;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.475); */
}
/* SGI */
.resultText2_2-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.4vw;
  margin-top: 14%;
  letter-spacing:0.28em;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.394); */
}
.resultText2_2-engrave-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.4vw;
  margin-top: 13%;
  letter-spacing:0.06em;

  height: 100%;
  width: 33.3333%;
}

/* 묘법 */
.resultText2_3-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.4vw;
  margin-top: 9%;
  letter-spacing:0.22em;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.438); */
}
.resultText2_3-engrave-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  font-size: 18.4vw;
  margin-top: 7%;
  letter-spacing:-0.08em;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.438); */
}
/* 아래 글자(세례명) */
.resultText2_4-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -3%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(221, 255, 0, 0.695); */
}
/* 이름1 */
.resultText2_4_1-engrave {
  font-size: 18.4vw;
  margin-top: 28%;
  letter-spacing:0.28em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
.resultText2_4_1-engrave-4 {
  font-size: 18.4vw;
  margin-top: 28%;
  margin-bottom: 2%;
  letter-spacing:-0.04em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_4_2-engrave {
  font-size: 10.5vw;
  margin-top: -4%;
  letter-spacing:-0.02em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-engrave-4 {
  font-size: 10.5vw;
  margin-top: -4%;
  letter-spacing:-0.22em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-engrave-5 {
  font-size: 9.5vw;
  margin-top: -4%;
  letter-spacing:-0.25em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-engrave-6 {
  font-size: 8.8vw;
  margin-top: -4%;
  letter-spacing:-0.37em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 위 글자 */
.resultText2_5-engrave {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -3%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(221, 255, 0, 0.31); */
}
/* 이름1 */
.resultText2_5_1-engrave {
  font-size: 18.4vw;
  margin-top: -1%;
  letter-spacing:0.32em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
.resultText2_5_1-engrave-4 {
  font-size: 18.4vw;
  margin-top: -2%;
  letter-spacing:-0.04em;

  font-weight: 900;
  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_5_2-engrave {
  font-size: 10.5vw;
  margin-top: 25%;
  letter-spacing:-0.04em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_5_2-engrave-4 {
  font-size: 10.5vw;
  margin-top: 28%;
  letter-spacing:-0.20em;

  font-weight: 900;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
</style>
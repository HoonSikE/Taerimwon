<template>
  <div class="">
    <router-link :to="'/engrave/engraveCreate/engraveDetail?type='
           + type + '&selectedType=' + selectedType + '&showRouterView=true'" class="title4">
          👉 [이전 페이지]
    </router-link>
    <div class="container">
      <!-- 각인 -->
      <div class="image-text-container">
        <div class="title2">● 각인 예시 
          <!-- 본관에 따른 데이터 전송값 변경 -->
          <span v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
            <router-link :to="'/engrave/resultEngrave?' + 'type=' + type 
                        + '&name0=' + encodedName0 + '&name1=' + name1 + '&name2='+ name2 
                        + '&date1=' + date1 + '&date1Type=' + date1Type 
                        + '&date2=' + date2 + '&date2Type=' + date2Type
                        + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;(🔎 크게보기)</router-link>
          </span>
          <span v-else>
            <router-link :to="'/engrave/resultEngrave?' + 'type=' + type 
                        + '&name0=' + '없음' + '&name1=' + name1 + '&name2='+ name2 
                        + '&date1=' + date1 + '&date1Type=' + date1Type 
                        + '&date2=' + date2 + '&date2Type=' + date2Type
                        + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;(🔎 크게보기)</router-link>
          </span>
        </div>
        <div class="title6">&nbsp;&nbsp;- {{type}} 
          <span v-if="type !== selectedType"> [{{selectedType}}] </span>
        </div>
        <div class="image-container">
          <div v-if="type === '일반' && selectedType === '일반'">
            <img src="../../assets/images/engrave/background/일반(공).png" width="100%" alt="일반">
          </div>
          <div v-if="type === '일반' && selectedType === '형제'">
            <img src="../../assets/images/engrave/background/일반형제(공).png" width="100%" alt="형제">
          </div>
          <div v-if="type === '기독교'">
            <img src="../../assets/images/engrave/background/기독교(공).png" width="100%" alt="기독교">
          </div>
          <div v-if="type === '불교'">
            <img src="../../assets/images/engrave/background/불교(공).png" width="100%" alt="불교">
          </div>
          <div v-if="type === '천주교'">
            <img src="../../assets/images/engrave/background/천주교(공).png" width="100%" alt="천주교">
          </div>
          <div v-if="type === 'SGI'">
            <img src="../../assets/images/engrave/background/sgi(공).png" width="100%" alt="SGI">
          </div>
          <div v-if="type === '묘법'">
            <img src="../../assets/images/engrave/background/묘법(공).png" width="100%" alt="묘법">
          </div>
        </div>
        <div class="text-container">
          <span class="resultText1">
            <span class="resultText1_1">{{date1_1}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{date1_2}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{date1_3}}</span>
            <span v-if="date1Type==='음력'" class="resultText1_3">陰</span>
            <span v-if="date1Type==='양력'" class="resultText1_3">陽</span>
          </span>
          <!-- <div v-if="selectedType === '일반'"> -->
          <!-- 그대로 -->
          <span class="resultText2" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교'
                                    || selectedType === '천주교'">
            <span>{{name1}}</span>
          </span>
          <!-- 아래 글자 1 -->
          <span class="resultText2_1" v-if="selectedType === '형제'">
            <span>{{name1}}</span>
          </span>
          <!-- 위 글자 1 -->
          <span class="resultText2_2" v-if="selectedType === 'SGI'">
            <span>{{name1}}</span>
          </span>
          <!-- 위 아래 글자 -->
          <span class="resultText2_3" v-if="selectedType === '묘법'">
            <span>{{name1}}</span>
          </span>
          <!-- 아래 글자 2 -->
          <span class="resultText2_4" v-if="selectedType === '세례명'">
            <span class="resultText2_4_1">{{name1}}</span>
            <span class="resultText2_4_2">{{name2}}</span>
          </span>
          <!-- 위 글자 2-->
          <span class="resultText2_5" v-if="selectedType === '직분' || selectedType === '법명'">
            <span class="resultText2_5_2">{{name2}}</span>
            <span class="resultText2_5_1">{{name1}}</span>
          </span>
          <!-- </div> -->
          <span class="resultText1">
            <span class="resultText1_1">{{date2_1}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{date2_2}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{date2_3}}</span>
            <span v-if="date2Type==='음력'" class="resultText1_3">陰</span>
            <span v-if="date2Type==='양력'" class="resultText1_3">陽</span>
          </span>
        </div>
      </div>
      <!-- 위패 -->
      <div class="image-text-container2">
        <div class="image-container">
          <div v-if="selectedType2 !== '없음'">
            <div class="title2">● 위패 예시
              <!-- 본관에 따른 데이터 전송값 변경 -->
              <span v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
                <router-link :to="'/engrave/resultTablet?' + 'type=' + type 
                            + '&name0=' + encodedName0 + '&name1=' + name1 + '&name2='+ name2 
                            + '&date1=' + date1 + '&date1Type=' + date1Type 
                            + '&date2=' + date2 + '&date2Type=' + date2Type
                            + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;(🔎 크게보기)</router-link>
              </span>
              <span v-else>
                <router-link :to="'/engrave/resultTablet?' + 'type=' + type 
                            + '&name0=' + '없음' + '&name1=' + name1 + '&name2='+ name2 
                            + '&date1=' + date1 + '&date1Type=' + date1Type 
                            + '&date2=' + date2 + '&date2Type=' + date2Type
                            + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;(🔎 크게보기)</router-link>
              </span>
            </div>
            <div class="title6">&nbsp;&nbsp;- {{type}} 
              <span v-if="name0 !== '없음'"> [본관] </span>
            </div>
            <div v-if="name0 !== '없음'">
              <div v-if="type === '일반'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/일반(본관출력시안).png" width="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/기독교(본관출력시안).png" width="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/불교(본관출력시안).png" width="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/천주교(본관출력시안).png" width="100%" alt="천주교">
              </div>
            </div>
            <div v-else>
              <div v-if="type === '일반'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/일반(이름출력시안).png" width="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/기독교(이름출력시안).png" width="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/불교(이름출력시안).png" width="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/천주교(이름출력시안).png" width="100%" alt="천주교">
              </div>
            </div>
            <hr>
          </div>
        </div>
        <div class="text-container">
          <!-- <span class="resultText3"> -->
          <!-- 위패 추가 -->
          <div v-if="selectedType2 !== '없음'">
            <!-- 본관 선택 o -->
            <div v-if="name0 !== '없음'">
              <!-- 일반 -->
              <span class="resultText4_1_1" v-if="type === '일반'">
                {{name0}}
              </span>
              <!-- 불교 -->
              <span class="resultText4_1_2" v-if="type === '불교'">
                {{name0}}
              </span>
              <!-- 위 글자 -->
              <span class="resultText4_2" v-if="type === '기독교'">
                <span class="resultText4_2_2">{{name2}}</span>
                <span class="resultText4_2_1">{{name0}}</span>
              </span>
              <!-- 아래 글자 -->
              <span class="resultText4_3" v-if="type === '천주교'">
                <span class="resultText4_3_1">{{name0}}</span>
                <span class="resultText4_3_2">{{name2}}</span>
              </span>
            </div>
            <!-- 본관 선택 x-->
            <div v-else>
              <!-- 일반 -->
              <span class="resultText3_1" v-if="type === '일반' || type === '불교'">
                {{name1}}
              </span>
              <!-- 위 글자 -->
              <span class="resultText3_2" v-if="type === '기독교'">
                <span class="resultText3_2_2">{{name2}}</span>
                <span class="resultText3_2_1">{{name1}}</span>
              </span>
              <!-- 아래 글자 -->
              <span class="resultText3_3" v-if="type === '천주교'">
                <span class="resultText3_3_1">{{name1}}</span>
                <span class="resultText3_3_2">{{name2}}</span>
              </span>
            </div>
          </div>
        </div>
      </div>
      <br>
      <!-- <div>
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
          selectedType: {{selectedType}}
          <br>
          selectedType2: {{selectedType2}}
        <br>
      </div> -->
      <div>
        <!-- <router-link :to="'/engrave/result?' + 'name=' + $route.query.name + '&date1=' + $route.query.date1 + '&date2='+ $route.query.date2">전체 화면</router-link> -->
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
.container {
  display: flex;
  /* justify-content: space-between; 가로로 나란히 정렬 */
  justify-content: center; /* 가운데 정렬 추가 */
  align-items: center;
  margin: 0 auto;
  max-width: 1200px; /* 원하는 최대 너비 설정 */
  /* background-color: darkblue; */
}

.image-text-container {
  position: relative;
  width: 200px;
  /* width: 30%; */
  /* height: 80%; */
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: blue; */
  margin-right: 10%;
}

.image-text-container2 {
  position: relative;
  width: 200px;
  /* width: 30%; */
  /* height: 80%; */
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: blue; */
}

.image-container img {
  width: 100%;
  height: 100%;
  /* width: 363px; */
  /* height: 590px; */
  /* width: 200px; */
  /* height: 330px; */
}

.text-container {
  position: absolute;
  top: 65%;
  left: 50%;
  transform: translate(-50%, -50%);

  /* width: 100%; */
  /* width: 200px; */
  /* height: 75%; */
  
  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: blue; */
}

.resultText1 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 23px;
  letter-spacing:-7px;

  margin-top: 1%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}

.resultText1_1 {
  color: black;
  font-family: "CENTURY";
  font-weight: 900;
}

.resultText1_2 {
  color: black;
  font-weight: bold;
  letter-spacing:-9px;
  margin-top: -7px;
}

.resultText1_3 {
  color: black;
  /* font-family: "한양해서"; */
  /* font-weight: regular; */
  font-family: "NotoSerifTC";
  /* font-weight: bold; */
  margin-top: 2%;
  font-size: 30px;
}

/* 이름 */
.resultText2 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 58px;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  margin-top: 4%;
  letter-spacing:21px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}

/* 형제 */
.resultText2_1 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 58px;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  margin-top: -10%;
  letter-spacing:3px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}
/* SGI */
.resultText2_2 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 58px;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  margin-top: 6%;
  letter-spacing:10px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}

/* 묘법 */
.resultText2_3 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 58px;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  margin-top: -3%;
  letter-spacing:5px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}
/* 아래 글자(세례명) */
.resultText2_4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -3%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText2_4_1 {
  font-size: 58px;
  margin-top: 13%;
  letter-spacing:6px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_4_2 {
  font-size: 35px;
  margin-top: -8px;
  letter-spacing:-4px;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 위 글자 */
.resultText2_5 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -6%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText2_5_1 {
  font-size: 58px;
  /* margin-top: 0%; */
  letter-spacing:7px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_5_2 {
  font-size: 25px;
  margin-top: 8%;
  letter-spacing:-4px;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}

/* 위패 */
/* 일반 */
.resultText3_1 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  font-size: 80px;
  letter-spacing:65px;

  /* font-weight: 900; */
  /* margin-top: 4%; */
  
  height: 100%;
  width: 50%;
  /* background-color: rgb(251, 0, 0); */
}
/* 위 글자 */
.resultText3_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -3%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText3_2_1 {
  font-size: 80px;
  margin-top: 6%;
  letter-spacing:35px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText3_2_2 {
  font-size: 50px;
  margin-top: 1%;
  letter-spacing:-7px;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 아래 글자(세례명) */
.resultText3_3 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -3%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText3_3_1 {
  font-size: 80px;
  margin-top: 12%;
  letter-spacing:30px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText3_3_2 {
  font-size: 50px;
  margin-top: -15px;
  letter-spacing:-7px;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 위패 */
/* 본관 */
/* 일반 */
.resultText4_1_1 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  font-size: 65px;

  margin-top: -14%;
  letter-spacing:2px;

  
  height: 100%;
  width: 50%;
  /* background-color: rgb(251, 0, 0); */
}
/* 불교 */
.resultText4_1_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;
  font-size: 60px;

  margin-top: -9%;
  letter-spacing:0px;
  
  height: 100%;
  width: 50%;
  /* background-color: rgb(251, 0, 0); */
}
/* 위 글자 */
.resultText4_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -18%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_2_1 {
  font-size: 55px;
  margin-top: 1%;
  letter-spacing:-2px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText4_2_2 {
  font-size: 40px;
  margin-top: 1%;
  letter-spacing:-7px;
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 아래 글자(세례명) */
.resultText4_3 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "궁서체";
  font-weight: 900;

  margin-top: -4%;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_3_1 {
  font-size: 63px;
  /* margin-top: 1%; */
  letter-spacing:0px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText4_3_2 {
  font-size: 45px;
  /* margin-top: 1%; */
  /* letter-spacing:-7px; */
  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}

.title6 {
  font-size: 15px;
  font-family: "BMEULJIROTTF";
  cursor: pointer;
}

.title7 {
  font-size: 13px;
  font-weight: bold;
  color: rgb(111, 103, 255);
  font-family: "BMEULJIROTTF";
  cursor: pointer;
}
</style>
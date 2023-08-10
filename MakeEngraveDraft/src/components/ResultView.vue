<template>
  <div class="">
    <h1>각인 주문접수</h1>
    <div>
      <div>
        각인
        <span>
          각인종류<br/>
          각인 [일반]<br/>
        </span>
      </div>
    </div>
    <hr>
    <div class="resultBackground">
      <div class="resultText">
        <span class="resultText1">
          <!-- <span class="span1_1">生</span> -->
          <span class="resultText1_2">{{date1_1}}</span>
          <span class="resultText1_3">●</span>
          <span class="resultText1_2">{{date1_2}}</span>
          <span class="resultText1_3">●</span>
          <span class="resultText1_2">{{date1_3}}</span>
          <!-- <span class="span1_1">陽</span> -->
        </span>
        <span class="resultText2">
          <!-- <span class="span2_1">故</span> -->
          <span>{{$route.query.name}}</span>
        </span>
        <span class="resultText1">
          <!-- <span class="span1_1">卒</span> -->
          <span class="resultText1_2">{{date2_1}}</span>
          <span class="resultText1_3">●</span>
          <span class="resultText1_2">{{date2_2}}</span>
          <span class="resultText1_3">●</span>
          <span class="resultText1_2">{{date2_3}}</span>
          <!-- <span class="span1_1">陽</span> -->
        </span>
      </div>
    </div>

    <div>
      <router-link :to="'/engrave/result?' + 'name=' + $route.query.name + '&date1=' + $route.query.date1 + '&date2='+ $route.query.date2">전체 화면</router-link>
      <!-- <div>
        {{$route.query.name}}
        {{$route.query.date1}}
        {{$route.query.date2}}
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
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

.resultBackground {
  /* background-image: url("../../assets/images/background/일반(공).png"); */
  background-image: url("../../assets/images/engrave/background/불교(공).png");
  background-size: cover; /* 배경 이미지를 컨테이너에 꽉 차게 늘리기 */
  background-repeat: no-repeat; /* 배경 이미지 반복 방지 */
  background-position: center; /* 배경 이미지 중앙 정렬 */
  width: 391px;
  height: 646px;
}

.resultText {
  color: black;
  width: 100%;
  height: 100%;
}

.resultText1{
  /* background-color: blue; */
  margin-top: 45%;
  writing-mode: vertical-rl; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  /* border: 2px solid #09c; */
}
.resultText1_1{
  /* background-color: pink; */
  font-size: 35px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
.resultText1_2{
  /* background-color: red; */
  font-size: 50px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
.resultText1_3{
  /* background-color: green; */
  font-size: 20px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
.resultText2{
  /* background-color: orange; */
  margin-top: 40%;
  /* width: 50%; */
  margin-bottom: 10px;
  font-size: 140px;
  font-family: "궁서체";
  font-weight: bold;
  writing-mode: vertical-rl; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  /* transform: translate(0, -3%); */
  /* border: 2px solid #09c; */
}
.resultText2_1{
  /* background-color: yellow; */
  margin-top: 10px;
  margin-bottom: 10px;
  padding: 0px;
  font-size: 60px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
</style>

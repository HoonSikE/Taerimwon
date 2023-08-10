<template>
  <div class="">
    <h1>명지사 각인 미리보기</h1>
    <div>
      각인
      <hr>
      <div class="div0">
        <div class="div1">
          <span class="span1">
            <span class="span1_1">生</span>
            <span class="span1_2">{{date1_1}}</span>
            <span class="span1_3">●</span>
            <span class="span1_2">{{date1_2}}</span>
            <span class="span1_3">●</span>
            <span class="span1_2">{{date1_3}}</span>
            <span class="span1_1">陽</span>
          </span>
          <span class="span2">
            <span class="span2_1">故</span>
            <span>{{$route.query.name}}</span>
          </span>
          <span class="span1">
            <span class="span1_1">卒</span>
            <span class="span1_2">{{date2_1}}</span>
            <span class="span1_3">●</span>
            <span class="span1_2">{{date2_2}}</span>
            <span class="span1_3">●</span>
            <span class="span1_2">{{date2_3}}</span>
            <span class="span1_1">陽</span>
          </span>
        </div>
      </div>
    </div>
    <br/>
    <!-- <div>
        {{$route.query.name}}
        {{$route.query.date1}}
        {{$route.query.date2}}
    </div>
    <br/>
    <div>
      {{date1_1}}
      {{date1_2}}
      {{date1_3}}
      {{date2_1}}
      {{date2_2}}
      {{date2_3}}
    </div> -->
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

.div0{
  margin-top: 10px;
}
.div1{
  background-color: white; color: black;
  width: max-content;
  margin: 0 auto; /* div 가운데 정렬 */
  text-align: center; /* 텍스트 가운데 정렬 */
  position: relative;
  /* border: 2px solid black; */
}
.span1{
  /* background-color: blue; */
  margin-top: 5px;
  margin-bottom: 5px;
  writing-mode: vertical-rl; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  /* border: 2px solid #09c; */
}
.span1_1{
  /* background-color: pink; */
  font-size: 35px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
.span1_2{
  /* background-color: red; */
  font-size: 30px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
.span1_3{
  /* background-color: green; */
  font-size: 10px;
  font-family: "궁서체";
  font-weight: bold;
  /* border: 2px solid #09c; */
}
.span2{
  /* background-color: orange; */
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: 80px;
  font-family: "궁서체";
  font-weight: bold;
  writing-mode: vertical-rl; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  /* transform: translate(0, -3%); */
  /* border: 2px solid #09c; */
}
.span2_1{
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

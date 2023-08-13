<template>
  <div class="">
    <!-- 본관에 따른 데이터 전송값 변경 -->
    <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
      <router-link :to="'/engrave/result?' + 'type=' + type 
                  + '&name0=' + encodedName0 + '&name1=' + name1 + '&name2='+ name2 
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
    <div class="container">
      <!-- 각인 -->
      <div class="image-text-container">
        <div class="title2">● 각인 예시</div>
        <div class="title6">&nbsp;&nbsp;- {{type}} 
          <span v-if="type !== selectedType"> [{{selectedType}}] </span>
        </div>
        <div class="image-container">
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

</style>
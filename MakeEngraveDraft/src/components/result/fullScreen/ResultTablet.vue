<template>
  <div class="">
    <!-- 본관에 따른 데이터 전송값 변경 -->
    <router-link :to="'/engrave/result?' + 'type=' + type 
                + '&name0=' + name0 + '&name1=' + name1 + '&name2='+ name2 
                + '&date1=' + date1 + '&date1Type=' + date1Type 
                + '&date2=' + date2 + '&date2Type=' + date2Type
                + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title4">👉 [이전 페이지]</router-link>
    <div class="container">
      <!-- 위패 -->
      <div class="image-text-container2">
        <div class="image-container">
          <div v-if="selectedType2 !== '없음'">
            <div class="title2">● 위패 예시</div>
            <div v-if="name0 !== '없음'">
              <div v-if="type === '일반'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/일반(본관출력시안).png" width="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/기독교(본관출력시안).png" width="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/불교(본관출력시안).png" width="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../../assets/images/memorialTablet/background/본관출력시안/천주교(본관출력시안).png" width="100%" alt="천주교">
              </div>
            </div>
            <div v-else>
              <div v-if="type === '일반'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/일반(이름출력시안).png" width="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/기독교(이름출력시안).png" width="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/불교(이름출력시안).png" width="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../../assets/images/memorialTablet/background/이름출력시안/천주교(이름출력시안).png" width="100%" alt="천주교">
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
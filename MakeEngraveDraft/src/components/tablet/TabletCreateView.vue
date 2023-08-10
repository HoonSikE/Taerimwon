<template>
  <div class="">
    <h1>위패 주문하기</h1>
    ● 위패 안내<br/>
    <!-- 가로 스크롤 가능한 컨테이너 -->
    <div class="scroll-container">
      <div class="link-container">
        <!-- 일반 -->
        <div v-if="type === '일반'">
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '일반' }"
              @click="selectedType2 = '일반'">
            <img src="../../assets/images/memorialTablet/example/이름시안/일반.png" width="80" height="100" alt="일반">
            <span class="selectText">[일반]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '일반(본관)' }"
              @click="selectedType2 = '일반(본관)'">
            <img src="../../assets/images/memorialTablet/example/본관시안/일반(본관).png" width="80" height="100" alt="일반(본관)">
            <span class="selectText">[일반(본관)]</span>
          </button>
        </div>
        <div v-if="type === '기독교'">
          <!-- 기독교 -->
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '기독교' }"
              @click="selectedType2 = '기독교'">
            <img src="../../assets/images/memorialTablet/example/이름시안/기독교.png" width="80" height="100" alt="기독교">
            <span class="selectText">[기독교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '기독교(본관)' }"
              @click="selectedType2 = '기독교(본관)'">
            <img src="../../assets/images/memorialTablet/example/본관시안/기독교(본관).png" width="80" height="100" alt="기독교(본관)">
            <span class="selectText">[기독교(본관)]</span>
          </button>
        </div>
        <div v-if="type === '불교'">
          <!-- 불교 -->
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '불교' }"
              @click="selectedType2 = '불교'">
            <img src="../../assets/images/memorialTablet/example/이름시안/불교.png" width="80" height="100" alt="불교">
            <span class="selectText">[불교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '불교(본관)' }"
              @click="selectedType2 = '불교(본관)'">
            <img src="../../assets/images/memorialTablet/example/본관시안/불교(본관).png" width="80" height="100" alt="불교(본관)">
            <span class="selectText">[불교(본관)]</span>
          </button>
        </div>
        <!-- 천주교 -->
        <div v-if="type === '천주교'">
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '천주교' }"
              @click="selectedType2 = '천주교'">
            <img src="../../assets/images/memorialTablet/example/이름시안/천주교.png" width="80" height="100" alt="천주교">
            <span class="selectText">[천주교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '천주교(본관)' }"
              @click="selectedType2 = '천주교(본관)'">
            <img src="../../assets/images/memorialTablet/example/본관시안/천주교(본관).png" width="80" height="100" alt="천주교(본관)">
            <span class="selectText">[천주교(본관)]</span>
          </button>
        </div>
      </div>
    </div>
    <!-- 정보입력 -->
    <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
      * 본관 입력<br/>
      <input v-model="name0" type="text" :placeholder="defaultName0Placeholder"/>
    </div>
    <div>
      * 고인성함 입력<br/>
      <input v-model="name1" type="text" :placeholder="name1"/>
    </div>
    <span v-if="selectedType === '직분'">
      직분 입력<br/>
      <input v-model="name2" type="text" :placeholder="name2"/>
    </span>
    <span v-if="selectedType === '법명'">
      법명 입력<br/>
      <input v-model="name2" type="text" :placeholder="name2"/>
    </span>
    <span v-if="selectedType === '세례명'">
      세례명 입력<br/>
      <input v-model="name2" type="text" :placeholder="name2"/>
    </span>
    <hr>
    <!-- <router-link to="/engrave/result">dd</router-link> -->
    <router-link :to="'/engrave/result?' + 'type=' + type 
                  + '&name1=' + name1 + '&name2='+ name2 
                  + '&date1=' + date1 + '&date1Type=' + date1Type 
                  + '&date2=' + date2 + '&date2Type=' + date2Type
                  + '&selectedType=' + selectedType">예시 보기</router-link>
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
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: this.$route.query.type,
      name1: this.$route.query.name1,
      name2: this.$route.query.name2,
      date1: this.$route.query.date1,
      date1Type: this.$route.query.date1Type,
      date2: this.$route.query.date2,
      date2Type: this.$route.query.date2Type,
      selectedType: this.$route.query.selectedType, // 초기 선택 타입 설정
      selectedType2: this.$route.query.type, // 초기 선택 타입 설정
    };
  },
  computed: {
    encodedName1() {
      const trimmedName0 = this.name0.trim();
      return trimmedName0 === '' ? '본관' : encodeURIComponent(trimmedName0);
    },
    defaultName0Placeholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      return '본관';
    },
  },
  // 매개변수의 변경 사항을 감지
  watch: {
    '$route.query.type': function(newType) {
      this.type = newType;
    },
    '$route.query.name1': function(newName1) {
      this.name1 = newName1;
    },
    '$route.query.name2': function(newName2) {
      this.name2 = newName2;
    },
    '$route.query.date1': function(newDate1) {
      this.date1 = newDate1;
    },
    '$route.query.date1Type': function(newDate1Type) {
      this.date1Type = newDate1Type;
    },
    '$route.query.date2': function(newDate2) {
      this.date2 = newDate2;
    },
     '$route.query.date2Type': function(newDate2Type) {
      this.date2Type = newDate2Type;
    },
    '$route.query.selectedType': function(newSelectedType) {
      this.selectedType = newSelectedType;
    },
  },
};
</script>
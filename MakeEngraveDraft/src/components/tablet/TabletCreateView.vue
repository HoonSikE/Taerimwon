<template>
  <div class="">
    <div class="title">
      위패 생성하기
    </div>
    <div class="title2">
      ● 위패 종류
    </div>
    <!-- 가로 스크롤 가능한 컨테이너 -->
    <div class="text-align-center">
      <span class="link-item-color">
        <!-- 일반 -->
        <div v-if="type === '일반'">
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '일반' }"
              @click="selectedType2 = '일반', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/이름시안/일반.png" width="80" height="240" alt="일반">
            <span class="selectText">[일반]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '일반(본관)' }"
              @click="selectedType2 = '일반(본관)', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/본관시안/일반(본관).png" width="80" height="240" alt="일반(본관)">
            <span class="selectText">[일반(본관)]</span>
          </button>
        </div>
        <div v-if="type === '기독교'">
          <!-- 기독교 -->
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '기독교' }"
              @click="selectedType2 = '기독교', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/이름시안/기독교.png" width="80" height="240" alt="기독교">
            <span class="selectText">[기독교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '기독교(본관)' }"
              @click="selectedType2 = '기독교(본관)', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/본관시안/기독교(본관).png" width="80" height="240" alt="기독교(본관)">
            <span class="selectText">[기독교(본관)]</span>
          </button>
        </div>
        <div v-if="type === '불교'">
          <!-- 불교 -->
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '불교' }"
              @click="selectedType2 = '불교', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/이름시안/불교.png" width="80" height="240" alt="불교">
            <span class="selectText">[불교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '불교(본관)' }"
              @click="selectedType2 = '불교(본관)', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/본관시안/불교(본관).png" width="80" height="240" alt="불교(본관)">
            <span class="selectText">[불교(본관)]</span>
          </button>
        </div>
        <!-- 천주교 -->
        <div v-if="type === '천주교'">
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '천주교' }"
              @click="selectedType2 = '천주교', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/이름시안/천주교.png" width="80" height="240" alt="천주교">
            <span class="selectText">[천주교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '천주교(본관)' }"
              @click="selectedType2 = '천주교(본관)', showRouterView = false">
            <img src="../../assets/images/memorialTablet/example/본관시안/천주교(본관).png" width="80" height="240" alt="천주교(본관)">
            <span class="selectText">[천주교(본관)]</span>
          </button>
        </div>
      </span>
    </div>
    <!-- 정보입력 -->
    <div class="text-align-center">
      <span class="info-text-align-center">
        <div class="title3">
          정보 입력
        </div>
        <span class="input-info1">
         <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
            본관 내용&nbsp;
          </div>
          <span v-if="type === '기독교'">
            직분&nbsp;
          </span>
          <!-- <span v-if="selectedType === '법명'">
            법명 입력<br/>
          </span> -->
          <span v-if="type === '천주교'">
            세례명&nbsp;
          </span>
        </span>
        <span class="input-info2">
          <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
            <input v-model="name0" type="text" :placeholder="defaultName0Placeholder"/>
          </div>
          <span v-if="type === '기독교'">
            <input v-model="name2" type="text" :placeholder="name2"/>
          </span>
          <!-- <span v-if="selectedType === '법명'">
            <input v-model="name2" type="text" :placeholder="name2"/>
          </span> -->
          <span v-if="type === '천주교'">
            <input v-model="name2" type="text" :placeholder="name2"/>
          </span>
        </span>
      </span>
    </div>

    <!-- 본관에 따른 데이터 전송값 변경 -->
    <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
      <router-link :to="'/engrave/result?' + 'type=' + type 
                  + '&name0=' + encodedName0 + '&name1=' + name1 + '&name2='+ name2 
                  + '&date1=' + date1 + '&date1Type=' + date1Type 
                  + '&date2=' + date2 + '&date2Type=' + date2Type
                  + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title4">👉 예시 보기 (각인/위패)</router-link>
    </div>
    <div v-else>
      <router-link :to="'/engrave/result?' + 'type=' + type 
                  + '&name0=' + '없음' + '&name1=' + name1 + '&name2='+ name2 
                  + '&date1=' + date1 + '&date1Type=' + date1Type 
                  + '&date2=' + date2 + '&date2Type=' + date2Type
                  + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title4">👉 예시 보기 (각인/위패)</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: this.$route.query.type,
      name0: '',
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
    encodedName0() {
      const trimmedName0 = this.name0.trim();

      if(this.selectedType2 === "일반(본관)")
        return trimmedName0 === '' ? '희빈홍씨길동신위' : encodeURIComponent(trimmedName0);
      else if(this.selectedType2 === "기독교(본관)")
        return trimmedName0 === '' ? '희빈홍씨길동' : encodeURIComponent(trimmedName0);
      else if(this.selectedType2 === "불교(본관)")
        return trimmedName0 === '' ? '희빈홍씨길동영가' : encodeURIComponent(trimmedName0);
      else if(this.selectedType2 === "천주교(본관)")
        return trimmedName0 === '' ? '희빈홍씨길동' : encodeURIComponent(trimmedName0);

      return trimmedName0 === '' ? '본관' : encodeURIComponent(trimmedName0);
    },
    defaultName0Placeholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      if(this.selectedType2 === "일반(본관)")
        return '희빈홍씨길동신위';
      else if(this.selectedType2 === "기독교(본관)")
        return '희빈홍씨길동';
      else if(this.selectedType2 === "불교(본관)")
        return '희빈홍씨길동영가';
      else if(this.selectedType2 === "천주교(본관)")
        return '희빈홍씨길동';
      return '본관';
    },
  },
  // 매개변수의 변경 사항을 감지
  watch: {
    '$route.query.type': function(newType) {
      this.type = newType;
    },
    '$route.query.name0': function(newName0) {
      this.name0 = newName0;
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
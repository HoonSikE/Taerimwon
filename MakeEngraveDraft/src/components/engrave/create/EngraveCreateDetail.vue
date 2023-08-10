<template>
  <div class="">
    <div>
      ● 종류<br>
      정보 입력 ({{type}})
      <div class="link-container">
        <!-- 일반 -->
        <div v-if="type === '일반'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '일반' }"
            @click="selectedType = '일반'"
          >
            <img src="../../../assets/images/engrave/example/일반.png" width="80" height="100" alt="일반">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '형제' }"
            @click="selectedType = '형제'"
          >
            <img src="../../../assets/images/engrave/example/일반(형제).png" width="80" height="100" alt="형제">
            <span class="selectText">[형제]</span>
          </button>
        </div>
        <!-- 기독교 -->
        <div v-if="type === '기독교'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '기독교' }"
            @click="selectedType = '기독교'"
          >
            <img src="../../../assets/images/engrave/example/기독교(직분x).png" width="80" height="100" alt="기독교(직분x)">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '직분' }"
            @click="selectedType = '직분'"
          >
            <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="100" alt="기독교(직분)">
            <span class="selectText">[직분]</span>
          </button>
        </div>
        <!-- 불교 -->
        <div v-if="type === '불교'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '불교' }"
            @click="selectedType = '불교'"
          >
            <img src="../../../assets/images/engrave/example/불교.png" width="80" height="100" alt="불교">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '법명' }"
            @click="selectedType = '법명'"
          >
            <img src="../../../assets/images/engrave/example/불교(법명).png" width="80" height="100" alt="불교(법명)">
            <span class="selectText">[법명]</span>
          </button> 
        </div>
        <!-- 천주교 -->
        <div v-if="type === '천주교'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '천주교' }"
            @click="selectedType = '천주교'"
          >
            <img src="../../../assets/images/engrave/example/천주교(세례명x).png" width="80" height="100" alt="천주교)">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '세례명' }"
            @click="selectedType = '세례명'"
          >
            <img src="../../../assets/images/engrave/example/천주교.png" width="80" height="100" alt="천주교(세례명)">
            <span class="selectText">[세례명]</span>
          </button>
        </div>
        <!-- SGI -->
        <div v-if="type === 'SGI'">
          <button
            class="link-item"
            :class="{ selected: selectedType === 'SGI' }"
            @click="selectedType = 'SGI'"
          >
            <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="100" alt="SGI">
            <span class="selectText">[기본]</span>
          </button>
        </div>
        <!-- 묘법 -->
        <div v-if="type === '묘법'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '묘법' }"
            @click="selectedType = '묘법'"
          >
            <img src="../../../assets/images/engrave/example/묘법.png" width="80" height="100" alt="묘법">
            <span class="selectText">[기본]</span>
          </button>
        </div>
      </div>
      <!-- 정보입력 -->
      <div>
        * 고인성함 입력<br/>
        <input v-model="name1" type="text" :placeholder="defaultName1Placeholder"/>
      </div>

      <div v-if="selectedType === '형제'">
        형제이름 입력<br/>
        <input v-model="name2" type="text" :placeholder="defaultName2Placeholder"/>
      </div>
      <span v-if="selectedType === '직분'">
        직분 입력<br/>
        <input v-model="name2" type="text" :placeholder="defaultName2Placeholder"/>
      </span>
      <span v-if="selectedType === '법명'">
        법명 입력<br/>
        <input v-model="name2" type="text" :placeholder="defaultName2Placeholder"/>
      </span>
      <span v-if="selectedType === '세례명'">
        세례명 입력<br/>
        <input v-model="name2" type="text" :placeholder="defaultName2Placeholder"/>
      </span>
      <!-- 날짜 입력 -->
      <div>
        * 출생일 선택<br/>
        <input v-model="date1" type="date" placeholder="1900-01-01"/>
        <select name="date1Type" v-model="date1Type">
          <option value="양력" selected="selected">양력</option>
          <option value="음력">음력</option>
        </select>
      </div>
      <div>
        *사망일 선택<br/>
        <input v-model="date2" type="date"/>
        <select name="date2Type" v-model="date2Type">
          <option value="양력" selected="selected">양력</option>
          <option value="음력">음력</option>
        </select>
      </div>
    </div>
    <hr>
    <div v-if="type !== 'SGI' && type !== '묘법'">
      <div v-if="showRouterView">
        <RouterLink :to="'/engrave/engraveCreate/engraveDetail/tabletCreate?' + 'type=' + type 
                        + '&name1=' + encodedName1 + '&name2=' + encodedName2 
                        + '&date1=' + date1 + '&date1Type=' + date1Type 
                        + '&date2=' + date2 + '&date2Type=' + date2Type
                        + '&selectedType=' + selectedType"
                    @click="toggleRouterView">
          위패 주문하기
        </RouterLink>
      </div>
      <div v-if="!showRouterView">
        <button @click="toggleRouterView">위패 주문하지 않기</button>
        <router-view></router-view>
      </div>
    </div>
    <div v-else>
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: this.$route.query.type,
      selectedType: this.$route.query.selectedType, // 초기 선택 타입 설정
      name1: '',
      name2: '',
      date1: '1920-08-01',
      date1Type: '양력',
      date2Type: '양력',
      date2: new Date().toISOString().substr(0, 10),
      showRouterView: this.$route.query.showRouterView,
    };
  },
  computed: {
    encodedName1() {
      const trimmedName1 = this.name1.trim();
      return trimmedName1 === '' ? '홍길동' : encodeURIComponent(trimmedName1);
    },
    defaultName1Placeholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      return '홍길동';
    },
    encodedName2() {
      const trimmedName2 = this.name2.trim();
      return trimmedName2 === '' ? this.selectedType : encodeURIComponent(trimmedName2);
    },
    defaultName2Placeholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      return this.selectedType;
    },
  },
  methods: {
    toggleRouterView() {
      this.showRouterView = !this.showRouterView;
    },
  },
  // 매개변수의 변경 사항을 감지
  watch: {
    '$route.query.type': function(newType) {
      this.type = newType;
    },
    '$route.query.selectedType': function(newSelectedType) {
      this.selectedType = newSelectedType;
    },
    '$route.query.showRouterView': function(newShowRouterView) {
      this.showRouterView = newShowRouterView;
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
</style>
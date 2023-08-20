<template>
  <div class="">
    <div class="title2">
      ● {{type}} 각인 종류
    </div>
    <div class="text-align-center">
      <span class="link-item-color">
        <!-- 일반 -->
        <div v-if="type === '일반'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '일반' }"
            @click="selectedType = '일반', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/일반.png" width="80" height="130" alt="일반">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '형제' }"
            @click="selectedType = '형제', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/일반(형제).png" width="80" height="130" alt="형제">
            <span class="selectText">[형제]</span>
          </button>
        </div>
        <!-- 기독교 -->
        <div v-if="type === '기독교'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '직분' }"
            @click="selectedType = '직분', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="기독교(직분)">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '기독교' }"
            @click="selectedType = '기독교', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/기독교(직분x).png" width="80" height="130" alt="기독교(직분x)">
            <span class="selectText">[직분X]</span>
          </button>
        </div>
        <!-- 불교 -->
        <div v-if="type === '불교'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '불교' }"
            @click="selectedType = '불교', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '법명' }"
            @click="selectedType = '법명', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/불교(법명).png" width="80" height="130" alt="불교(법명)">
            <span class="selectText">[법명]</span>
          </button> 
        </div>
        <!-- 천주교 -->
        <div v-if="type === '천주교'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '세례명' }"
            @click="selectedType = '세례명', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/천주교.png" width="80" height="130" alt="천주교(세례명)">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '천주교' }"
            @click="selectedType = '천주교', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/천주교(세례명x).png" width="80" height="130" alt="천주교)">
            <span class="selectText">[세례명X]</span>
          </button>
        </div>
        <!-- SGI -->
        <div v-if="type === 'SGI'">
          <button
            class="link-item"
            :class="{ selected: selectedType === 'SGI' }"
            @click="selectedType = 'SGI', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI">
            <span class="selectText">[기본]</span>
          </button>
        </div>
        <!-- 묘법 -->
        <div v-if="type === '묘법'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '묘법' }"
            @click="selectedType = '묘법', showRouterView = true"
          >
            <img src="../../../assets/images/engrave/example/묘법.png" width="80" height="130" alt="묘법">
            <span class="selectText">[기본]</span>
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
          <div>
            고인성함&nbsp;
          </div>
          <div v-if="selectedType === '직분'">
            직분&nbsp;
          </div>
          <div v-if="selectedType === '법명'">
            법명&nbsp;
          </div>
          <div v-if="selectedType === '세례명'">
            세례명&nbsp;
          </div>
          <!-- 날짜 입력 -->
          <div>
            출생일&nbsp;
          </div>
          <div>
            사망일&nbsp;
          </div>
        </span>
        <span class="input-info2">
          <div>
            <input v-model="name1" type="text" :placeholder="defaultName1Placeholder"/>
          </div>
          <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
            <input v-model="name2" type="text" :placeholder="defaultName2Placeholder"/>
          </div>
          <div>
            <!-- <input v-model="date1" type="date" placeholder="1900-01-01"/> -->
            <input v-model="date1_1" type="text" placeholder="년도" style="width: 4em;">.
            <input v-model="date1_2" type="text" placeholder="월" style="width: 2em;">.
            <input v-model="date1_3" type="text" placeholder="일" style="width: 2em;">.&nbsp;
            <!-- 날짜 입력 -->
            <select name="date1Type" v-model="date1Type">
              <option value="양력" selected="selected">양력</option>
              <option value="음력">음력</option>
            </select>
          </div>
          <div>
            <input v-model="date2_1" type="text" placeholder="년도" style="width: 4em;">.
            <input v-model="date2_2" type="text" placeholder="월" style="width: 2em;">.
            <input v-model="date2_3" type="text" placeholder="일" style="width: 2em;">.&nbsp;
            <select name="date2Type" v-model="date2Type">
              <option value="양력" selected="selected">양력</option>
              <option value="음력">음력</option>
            </select>
          </div>
        </span>
      </span>
    </div>
    <hr>
    <div v-if="showRouterView">
      <div v-if="showName1KoreanWarning" class="warning_text">
        - 성함을 한국어로 올바르게 입력해주세요.
      </div>
      <div v-else-if="showName1Warning" class="warning_text">
        - 성함을 2~4글자로 입력해주세요.
      </div>
      <div v-else-if="showName2KoreanWarning && (selectedType === '직분' || selectedType === '법명' || selectedType === '세례명')" class="warning_text">
        - {{selectedType}}을 한국어로 올바르게 입력해주세요.
      </div>
      <div v-else-if="showName2Warning && (selectedType === '직분' || selectedType === '법명')" class="warning_text">
        - {{selectedType}}을 2~4글자로 입력해주세요.
      </div>
      <div v-else-if="showName3Warning && (selectedType === '세례명')" class="warning_text">
        - {{selectedType}}을 2~6글자로 입력해주세요.
      </div>
      <div v-else-if="showDate1_2Warning" class="warning_text">
        - 출생일을 월을 올바르게 입력해주세요.
      </div>
      <div v-else-if="showDate1_3Warning" class="warning_text">
        - 출생일을 일을 올바르게 입력해주세요.
      </div>
      <div v-else-if="showDate2_2Warning" class="warning_text">
        - 사망일을 월을 올바르게 입력해주세요.
      </div>
      <div v-else-if="showDate2_3Warning" class="warning_text">
        - 사망일을 일을 올바르게 입력해주세요.
      </div>
      <div v-else>
        <div v-if="type !== 'SGI' && type !== '묘법'">
          <RouterLink :to="'/engrave/engraveCreate/engraveDetail/tabletCreate?' + 'type=' + type 
                          + '&name1=' + encodedName1 + '&name2=' + encodedName2 
                          + '&date1=' + encodedDate1 + '&date1Type=' + date1Type 
                          + '&date2=' + encodedDate2 + '&date2Type=' + date2Type
                          + '&selectedType=' + selectedType"
                      @click="toggleRouterView">
            <div class="title4">
              👉 위패 주문하기
              <span class="title4_1">
                (Click!!)
              </span>
            </div>
          </RouterLink>
        </div>
        <router-link :to="'/engrave/result?' + 'type=' + type 
                + '&name0=' + '없음' + '&name1=' + encodedName1 + '&name2='+ encodedName2 
                + '&date1=' + encodedDate1 + '&date1Type=' + date1Type 
                + '&date2=' + encodedDate2 + '&date2Type=' + date2Type
                + '&selectedType=' + selectedType + '&selectedType2=없음'" class="title4">👉 예시 보기 (각인)</router-link>
      </div>
    </div>
    <div v-if="!showRouterView">
      <div class="title5" @click="toggleRouterView">👉 위패 주문하지 않기!!</div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    const currentDate = new Date().toLocaleString('ko-KR');
    console.log('currentDate:', currentDate); // 현재 currentDate 값 출력
    const [year, month, day] = currentDate.split('.').map(value => parseInt(value.trim()));
    console.log('year:', year, 'month:', month, 'day:', day); // year, month, day 값 출력

    const formattedMonth = month < 10 ? `0${month}` : month.toString();
    console.log('formattedMonth:', formattedMonth); // formattedMonth 값 출력
    const formattedDay = day < 10 ? `0${day}` : day.toString();
    console.log('formattedDay:', formattedDay); // formattedDay 값 출력

    return {
      type: this.$route.query.type,
      selectedType: this.$route.query.selectedType, // 초기 선택 타입 설정
      name1: '',
      name2: '',
      date1_1: '',
      date1_2: '',
      date1_3: '',
      date2_1: year,
      date2_2: formattedMonth,
      date2_3: formattedDay,
      // date1: '1920-08-01',
      // date2: new Date().toISOString().substr(0, 10),
      date1Type: '양력',
      date2Type: '양력',
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

      if(this.type === '기독교')
        return trimmedName2 === '' ? '직분' : encodeURIComponent(trimmedName2);
      else if(this.type === '불교')
        return trimmedName2 === '' ? '법명' : encodeURIComponent(trimmedName2);
      else if(this.type === '천주교')
        return trimmedName2 === '' ? '세례명' : encodeURIComponent(trimmedName2);

      return trimmedName2 === '' ? this.selectedType : encodeURIComponent(trimmedName2);
    },
    defaultName2Placeholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      return this.selectedType;
    },
    showName1Warning() {
      const name1Length = this.name1.trim().length;
      return (name1Length < 2 || name1Length > 4) && name1Length !== 0;
    },
    showName1KoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.name1.length === 0)
        return false;

      // return !koreanRegex.test(this.name1);
      return !(koreanRegex.test(this.name1) && !koreanConsonantVowelRegex.test(this.name1));
    },
    showName2Warning() {
      const name2Length = this.name2.trim().length;
      return (name2Length < 2 || name2Length > 4) && name2Length !== 0;
    },
    showName3Warning() {
      const name2Length = this.name2.trim().length;
      return (name2Length < 2 || name2Length > 6) && name2Length !== 0;
    },
    showName2KoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.name2.length === 0)
        return false;

      // return !koreanRegex.test(this.name2);
      return !(koreanRegex.test(this.name2) && !koreanConsonantVowelRegex.test(this.name2));
    },
    encodedDate1() {
      if(this.date1_1 == '' || this.date1_2 == '' || this.date1_3 == '')
        return '1912-03-14';
      return this.date1_1 + '-' + this.date1_2 + '-' + this.date1_3;
    },
    encodedDate2() {
      return this.date2_1 + '-' + this.date2_2 + '-' + this.date2_3;
    },
    showDate1_2Warning(){
      const month = parseInt(this.date1_2);
      return month < 1 || month > 12;
    },
    showDate1_3Warning(){
      const month = parseInt(this.date1_2);
      const day = parseInt(this.date1_3);
      const maxDay = new Date(2023, month, 0).getDate();
      return day < 1 || day > maxDay;
    },
    showDate2_2Warning(){
      const month = parseInt(this.date2_2);
      return month < 1 || month > 12;
    },
    showDate2_3Warning(){
      const month = parseInt(this.date2_2);
      const day = parseInt(this.date2_3);
      const maxDay = new Date(2023, month, 0).getDate();
      return day < 1 || day > maxDay;
    },
  },
  methods: {
    toggleRouterView() {
      this.showRouterView = !this.showRouterView;
    },
  },
  // 매개변수의 변경 사항을 감지
  watch: {
    'name1': function(newName1) {
      this.name1 = newName1;
    },
    'name2': function(newName2) {
      this.name2 = newName2;
    },
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

.info-text-align-center {
  display: inline-block;
  text-align: left;
}

.link-item-color {
  display: inline-block;
  justify-content: center;
  align-items: center;
  background-color: #cfcfcf; /* 배경색을 설정합니다 */
  padding: 3px;
  margin-top: 2px;
  margin-bottom: 6px;
  border-radius: 5px;
}

.title3 {
  text-align: center;
  color: #baca11;
}

.title4 {
  font-size: 20px;
  font-family: "BMEULJIROTTF";
  color: red;
  cursor: pointer;
}

.title4_1 {
  color: pink;
}

.title5 {
  font-size: 20px;
  font-family: "BMEULJIROTTF";
  color: green;
  cursor: pointer;
}

.input-info1 {
  display: inline-block;
  justify-content: center;
  text-align: center;
  font-weight: bold;
  padding: 3px;
  /* background-color: rgb(81, 59, 59); */
}

.input-info2 {
  display: inline-block;
  justify-content: center;
  /* background-color: rgb(63, 103, 103); */
}

input::placeholder {
  text-align: center;
}

.warning_text{
  font-size: 18px;
  font-family: "BMEULJIROTTF";
  color: rgb(246, 63, 63);
}
</style>
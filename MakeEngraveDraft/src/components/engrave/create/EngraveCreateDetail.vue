<template>
  <div class="">
    <div class="title2">
      ● {{type}} 각인 종류 <br>
    </div>
    <div class="text-align-center">
      <span class="link-item-color">
        <!-- 일반 -->
        <div v-if="type === '일반'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '일반' }"
            @click="updateSelectType('일반')"
          >
            <img src="../../../assets/images/engrave/example/일반.png" width="80" height="130" alt="일반">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '형제' }"
            @click="updateSelectType('형제')"
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
            @click="updateSelectType('직분')"
          >
            <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="기독교(직분)">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '기독교' }"
            @click="updateSelectType('기독교')"
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
            @click="updateSelectType('불교')"
          >
            <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '법명' }"
            @click="updateSelectType('법명')"
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
            @click="updateSelectType('세례명')"
          >
            <img src="../../../assets/images/engrave/example/천주교.png" width="80" height="130" alt="천주교(세례명)">
            <span class="selectText">[기본]</span>
          </button>
          <button
            class="link-item"
            :class="{ selected: selectedType === '천주교' }"
            @click="updateSelectType('천주교')"
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
            @click="updateSelectType('SGI')"
          >
            <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI">
            <span class="selectText">[기본]</span>
          </button>
        </div>
        <!-- 묘법 -->
        <div v-if="getType === '묘법'">
          <button
            class="link-item"
            :class="{ selected: selectedType === '묘법' }"
            @click="updateSelectType('묘법')"
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
            <input v-model="name1" type="text" placeholder="홍길동" style="width: 7em;"/>
          </div>
          <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
            <input v-model="name2" type="text" :placeholder="defaultName2Placeholder" style="width: 7em;"/>
          </div>
          <div>
            <input v-model="date1" type="text" :placeholder="Date1Placeholder" @input="handleDateInput($event, 'date1')" style="width: 7em;">&nbsp;
            <!-- 날짜 입력 -->
            <select name="date1Type" v-model="date1Type">
              <option value="양력" selected="selected">양력</option>
              <option value="음력">음력</option>
            </select>
          </div>
          <div>
            <input v-model="date2" type="text" :placeholder="todayDate2Placeholder" @input="handleDateInput($event, 'date2')" style="width: 7em;">&nbsp;
            <select name="date2Type" v-model="date2Type">
              <option value="양력" selected="selected">양력</option>
              <option value="음력">음력</option>
            </select>
          </div>
        </span>
      </span>
    </div>
    <hr>
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
    <div v-else-if="showDate1Warning" class="warning_text">
      - 출생일을 {{ getDateWarningMessage(date1) }}
    </div>
    <div v-else-if="showDate2Warning" class="warning_text">
      - 사망일을 {{ getDateWarningMessage(date2) }}
    </div>
    <div v-else-if="showRouterView">
      <div v-if="type !== 'SGI' && type !== '묘법'">
        <router-link :to="{name: 'tabletCreateView'}" @click.native="updateRouteData()">
          <div class="title4">
            👉 위패 주문하기
            <span class="title4_1">
              (Click!!)
            </span>
          </div>
        </router-link>
      </div>
      <router-link :to="{name: 'result'}" @click.native="updateRouteData2()" class="title4">
        👉 예시 보기 (각인)
      </router-link>
    </div>
    <div v-else-if="!showRouterView">
      <div class="title5" @click="toggleRouterView">👉 위패 주문하지 않기!!</div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';

export default {
  data() {
    return {
      Date1Placeholder: '1900-01-01',
      todayDate2Placeholder: this.getTodayDate2Placeholder(),
    };
  },
  computed: {
    ...mapGetters([
      'getType',
      'getSelectedType',
      'getShowRouterView',

      'getName1',
      'getName2',
      'getDate1',
      'getDate1Type',
      'getDate2',
      'getDate2Type',
    ]),
    type: {
      get() {
        return this.$store.getters.getType;
      },
    },
    selectedType: {
      get() {
        return this.$store.getters.getSelectedType;
      },
    },
    showRouterView: {
      get() {
        return this.$store.getters.getShowRouterView;
      },
      set(value) {
        this.$store.commit('updateShowRouterView', value);
      }
    },
    name1: {
      get() {
        return this.$store.getters.getName1;
      },
      set(value) {
        this.$store.commit('updateName1', value);
      }
    },
    name2: {
      get() {
        return this.$store.getters.getName2;
      },
      set(value) {
        this.$store.commit('updateName2', value);
      }
    },
    date1: {
      get() {
        return this.$store.getters.getDate1;
      },
      set(value) {
        this.$store.commit('updateDate1', value);
      },
    },
    date1Type: {
      get() {
        return this.$store.getters.getDate1Type;
      },
      set(value) {
        this.$store.commit('updateDate1Type', value);
      }
    },
    date2: {
      get() {
        return this.$store.getters.getDate2;
      },
      set(value) {
        return this.$store.commit('updateDate2', value);
      },
    },
    date2Type: {
      get() {
        return this.$store.getters.getDate2Type;
      },
      set(value) {
        this.$store.commit('updateDate2Type', value);
      }
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

      return !(koreanRegex.test(this.name2) && !koreanConsonantVowelRegex.test(this.name2));
    },
    showDate1Warning() {
      return this.showDateWarning(this.date1);
    },
    showDate2Warning() {
      return this.showDateWarning(this.date2);
    },
  },
  methods: {
    ...mapMutations(['toggleRouterView']),
    toggleRouterView() {
      this.$store.commit('updateShowRouterView',!this.showRouterView);
    },
    updateSelectType(selectedType){
      this.$store.commit('updateSelectedType', selectedType);
      this.$store.commit('updateShowRouterView', true);
    },
    encodedName1() {
      const trimmedName1 = this.name1.trim();
      return trimmedName1 === '' ? '홍길동' : encodeURIComponent(trimmedName1);
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
    encodedDate1() {
      const trimmedDate1 = this.date1.trim();
      return trimmedDate1 === '' ? this.Date1Placeholder : encodeURIComponent(trimmedDate1);
    },
    encodedDate2() {
      const trimmedDate2 = this.date2.trim();
      return trimmedDate2 === '' ? this.todayDate2Placeholder : encodeURIComponent(trimmedDate2);
    },
    getTodayDate2Placeholder() {
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, '0');
      const day = String(today.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    },
    handleDateInput(event, targetDate) {
      const inputValue = event.target.value;
      const numericValue = inputValue.replace(/\D/g, ''); // 숫자만 추출

      // 숫자 입력 값을 날짜 형식으로 변환
      let formattedDate = '';

      if (numericValue.length >= 6) {
        const year = numericValue.substring(0, 4);
        formattedDate += year;
        const month = numericValue.substring(4, 6).padStart(2, '0');

        if (numericValue.length >= 8) {
          const day = numericValue.substring(6, 8).padStart(2, '0');
          formattedDate += `-${month}-${day}`;
        } else {
          formattedDate += `-${month}-${numericValue.substring(6)}`;
        }
      } else {
        formattedDate = inputValue;
      }

      // 마지막 글자가 하이폰인 경우 제거
      if (formattedDate.endsWith('-')) {
        formattedDate = formattedDate.slice(0, -1);
      }

      // 날짜 형식으로 변환한 값을 갱신
      if (targetDate === 'date1') {
        this.date1 = formattedDate;
      } else if (targetDate === 'date2') {
        this.date2 = formattedDate;
      }
    },
    showDateWarning(date) {
      if(date.length == 0)
        return false;

      const datePattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;

      if (!datePattern.test(date)) {
        return true;
      }

      const parts = date.split('-');
      const year = parseInt(parts[0], 10);
      const month = parseInt(parts[1], 10);
      const day = parseInt(parts[2], 10);

      if (month < 1 || month > 12 || day < 1 || day > new Date(year, month, 0).getDate()) {
        return true;
      }

      return false;
    },
    getDateWarningMessage(date) {
      const datePattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;

      if (!datePattern.test(date)) {
        return "올바른 날짜 형식(yyyy-mm-dd)으로 입력해주세요.";
      }

      const parts = date.split('-');
      const year = parseInt(parts[0], 10);
      const month = parseInt(parts[1], 10);
      const day = parseInt(parts[2], 10);

      if (month < 1 || month > 12) {
        return "올바른 월(1~12)로 입력해주세요.";
      }

      const lastDayOfMonth = new Date(year, month, 0).getDate();
      if (day < 1 || day > lastDayOfMonth) {
        return `올바른 일(1~${lastDayOfMonth})로 입력해주세요.`;
      }

      return "";
    },
    updateRouteData(){
      this.$store.commit('updateName0', '')
      this.name1 = decodeURIComponent(this.encodedName1());
      this.name2 = decodeURIComponent(this.encodedName2());
      this.date1 = decodeURIComponent(this.encodedDate1());
      this.date1Type ='양력';
      this.date2 = decodeURIComponent(this.encodedDate2());
      this.date2Type ='양력';
      this.$store.commit('updateSelectedType2', this.type);
      this.showRouterView = false;
    },
    updateRouteData2(){
      this.name1 = decodeURIComponent(this.encodedName1());
      this.name2 = decodeURIComponent(this.encodedName2());
      this.date1 = decodeURIComponent(this.encodedDate1());
      this.date1Type ='양력';
      this.date2 = decodeURIComponent(this.encodedDate2());
      this.date2Type ='양력';
      this.$store.commit('updateSelectedType2', '없음');
      // 로컬스토리지 저장
      this.$store.commit('updateName0', '없음')
    }
  },
};
</script>

<style>
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
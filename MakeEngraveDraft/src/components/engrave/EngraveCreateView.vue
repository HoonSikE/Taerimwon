<template>
  <div class="">
    <hr>
    <div class="app">
      <div class="title">
        1<span class="title-gray">-2-3-4</span><br>
        기본 정보 입력
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div class="text-align-center">
        <span class="info-text-align-center">
          <div class="title3">
            팀장 정보 입력 (필수)
          </div>
          <!-- 팀장명 입력 -->
          <div>
            팀장명<br>
            <input v-model="leaderName" type="text" placeholder="홍길동" style="height: 30px; width: 100%;"/>
            <div v-if="showLeaderNameKoreanWarning" class="warning_text">
              - 팀장명을 한국어로 올바르게 입력해주세요.
            </div>
            <div v-else-if="showLeaderNameWarning" class="warning_text">
              - 팀장명을 2~4글자로 입력해주세요.
            </div>
          </div>
          <!-- 전화번호 입력 -->
          <div>
            전화번호 (숫자만 입력)<br>
            <input v-model="leaderPhone" type="text" placeholder="010-1234-5678" @input="handlePhoneInput($event, 'leaderPhone')" style="height: 30px; width: 100%;"/>
            <div v-if="showLeaderPhoneWarning" class="warning_text">
              - 전화번호를 올바르게 입력해주세요.
            </div>
          </div>
          <div>
            소속<br>
            <input v-model="leaderDepartment" type="text" placeholder="소속" style="height: 30px; width: 100%;"/>
            <!-- <div v-if="showLeaderDepartmentWarning" class="warning_text"> -->
              <!-- - 소속을 올바르게 입력해주세요. -->
            <!-- </div> -->
          </div>
        </span>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div class="text-align-center">
        <span class="info-text-align-center">
          <div class="title3">
            상주 정보 입력(필수)
          </div>
          <!-- 상주 정보 입력 -->
          <div>
            상주명<br>
            <input v-model="clientName" type="text" placeholder="홍길동" style="height: 30px; width: 100%;"/>
            <div v-if="showClientNameKoreanWarning" class="warning_text">
              - 상주명을 한국어로 올바르게 입력해주세요.
            </div>
            <div v-else-if="showClientNameWarning" class="warning_text">
              - 상주명을 2~4글자로 입력해주세요.
            </div>
          </div>
          <!-- 전화번호 입력 -->
          <div>
            전화번호 (숫자만 입력)<br>
            <input v-model="clientPhone" type="text" placeholder="010-1234-5678" @input="handlePhoneInput($event, 'clientPhone')" style="height: 30px; width: 100%;"/>
            <div v-if="showClientPhoneWarning" class="warning_text">
              - 전화번호를 올바르게 입력해주세요.
            </div>
          </div>
        </span>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div class="text-align-center">
        <span class="info-text-align-center">
          <div>
            <div class="title3">
              발주 장소 입력
            </div>
            <label>
              <input type="radio" v-model="selectedLocation" name = "selectedLocation" value="화장장"> 화장장
            </label>
            <label>
              <input type="radio" v-model="selectedLocation" name = "selectedLocation" value="장례식장"> 장례식장
            </label>
            <label>
              <input type="radio" v-model="selectedLocation" name = "selectedLocation" value="장지"> 장지
            </label>

            <!-- 화장장 관련 내용을 여기에 표시 -->
            <div v-if="selectedLocation === '화장장'">
              <div>
                화장장<br>
                <select v-model="cremationArea" style="height: 30px; width: 100%;">
                  <option value="" selected disabled>화장장 선택</option>
                  <optgroup v-for="(cremationList, region) in getCremationOptions" :label="region" :key="region">
                    <option v-for="option in cremationList" :value="option" :key="option">{{ option }}</option>
                  </optgroup>
                </select>
              </div>
              <!-- 전화번호 입력 -->
              <div>
                화장시간 (숫자만 입력)<br>
                <input v-model="cremationTime" type="text" placeholder="2023-01-01 10:00" @input="handleDateTimeInput($event, 'cremationTime')" style="height: 30px; width: 100%;">
                <div v-if="showDateTimeWarning1" class="warning_text">
                  - 화장시간을 {{ getDateTimeWarningMessage(cremationTime) }}
                </div>
              </div>
            </div>
            <!-- 장례식장 관련 내용을 여기에 표시 -->
            <div v-else-if="selectedLocation === '장례식장'">
              <div>
                장례식장 명<br>
                <input v-model="funeralName" type="text" placeholder="장례식장 명" style="height: 30px; width: 100%;"/>
              </div>
              <!-- 전화번호 입력 -->
              <div>
                호실<br>
                <input v-model="funeralNumber" type="text" placeholder="ㅇㅇㅇ호" style="height: 30px; width: 100%;"/>
                <!-- <div v-if="showName1Warning" class="warning_text">
                  - 화장날짜를 올바르게 입력해주세요.
                </div> -->
              </div>
              <div>
                함 도착시간<br>
                <input v-model="funeralTime" type="text" placeholder="2023-01-01 10:00" @input="handleDateTimeInput($event, 'funeralTime')" style="height: 30px; width: 100%;">
                <div v-if="showDateTimeWarning2" class="warning_text">
                  - 함 도착시간을 {{ getDateTimeWarningMessage(funeralTime) }}
                </div>
              </div>
            </div>
            <!-- 장지 관련 내용을 여기에 표시 -->
            <div v-else-if="selectedLocation === '장지'">
              <div>
                장지명<br>
                <input v-model="burialName" type="text" placeholder="장지명" style="height: 30px; width: 100%;"/>
              </div>
              <!-- 전화번호 입력 -->
              <div>
                함 도착시간<br>
                <input v-model="burialTime" type="text" placeholder="2023-01-01 10:00" @input="handleDateTimeInput($event, 'burialTime')" style="height: 30px; width: 100%;">
                <div v-if="showDateTimeWarning3" class="warning_text">
                  - 함 도착시간을 {{ getDateTimeWarningMessage(burialTime) }}
                </div>
              </div>
            </div>
          </div>
        </span>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div class="title">
        <span class="title-gray">1-</span>2<span class="title-gray">-3-4</span><br>
        유골함 주문
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div class="title2">
        ● 유골함 각인 종류
      </div>
      <!-- 가로 스크롤 가능한 컨테이너 -->
      <div class="text-align-center">
        <div class="scroll-container">
          <div class="link-container">
            <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('일반', '일반')" class="link-item"
                :class="{ selected: selectedType === '일반' }"
                >
              <img src="../../assets/images/engrave/example/일반.png" alt="일반">
              <span class="selectText">[일반]</span>
            </router-link>
            <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('기독교', '직분')" class="link-item"
                :class="{ selected: selectedType === '직분' }"
                @click="selectedType = '직분'">
              <img src="../../assets/images/engrave/example/기독교.png" alt="기독교">
              <span class="selectText">[기독교]</span>
            </router-link>
            <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('불교', '불교')" class="link-item"
                :class="{ selected: selectedType === '불교' }"
                @click="selectedType = '불교'">
              <img src="../../assets/images/engrave/example/불교.png" alt="불교">
              <span class="selectText">[불교]</span>
            </router-link>
            <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('천주교', '세례명')" class="link-item"
                :class="{ selected: selectedType === '세례명' }"
                @click="selectedType = '세례명'">
              <img src="../../assets/images/engrave/example/천주교.png" alt="천주교">
              <span class="selectText">[천주교]</span>
            </router-link>
            <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('SGI', 'SGI')" class="link-item"
                :class="{ selected: selectedType === 'SGI' }"
                @click="selectedType = 'SGI'">
              <img src="../../assets/images/engrave/example/sgi.png" alt="SGI">
              <span class="selectText">[SGI]</span>
            </router-link>
            <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('묘법', '묘법')" class="link-item"
                :class="{ selected: selectedType === '묘법' }"
                @click="selectedType = '묘법'">
              <img src="../../assets/images/engrave/example/묘법.png" alt="묘법">
              <span class="selectText">[묘법]</span>
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
    };
  },
  computed: {
    ...mapGetters([
      'getLeaderName',
      'getLeaderPhone',
      'getLeaderDepartment',
      'getClientName',
      'getClientPhone',

      'getSelectedLocation',
      'getCremationArea',
      'getCremationTime',
      'getFuneralName',
      'getFuneralNumber',
      'getFuneralTime',
      'getBurialName',
      'getBurialTime',

      'getEngraveType',
      'getReligion',
      'getSelectedType',
      'getName2',
      'getShowRouterView'
    ]),
    leaderName: {
      get() {
        return this.$store.getters.getLeaderName;
      },
      set(value) {
        this.$store.commit('updateLeaderName', value);
      }
    },
    leaderPhone: {
      get() {
        return this.$store.getters.getLeaderPhone;
      },
      set(value) {
        this.$store.commit('updateLeaderPhone', value);
      }
    },
    leaderDepartment: {
      get() {
        return this.$store.getters.getLeaderDepartment;
      },
      set(value) {
        this.$store.commit('updateLeaderDepartment', value);
      }
    },
    clientName: {
      get() {
        return this.$store.getters.getClientName;
      },
      set(value) {
        this.$store.commit('updateClientName', value);
      }
    },
    clientPhone: {
      get() {
        return this.$store.getters.getClientPhone;
      },
      set(value) {
        this.$store.commit('updateClientPhone', value);
      }
    },
    selectedLocation: {
      get() {
        return this.$store.getters.getSelectedLocation;
      },
      set(value) {
        this.$store.commit('updateSelectedLocation', value);
      }
    },
    cremationArea: {
      get() {
        return this.$store.getters.getCremationArea;
      },
      set(value) {
        this.$store.commit('updateCremationArea', value);
      }
    },
    getCremationOptions() {
      return {
        서울: this.cremationSeoul(),
        인천: this.cremationIncheon(),
        경기: this.cremationGyeonggi(),
        대구: this.cremationDaegu(),
      };
    },
    cremationTime: {
      get() {
        return this.$store.getters.getCremationTime;
      },
      set(value) {
        this.$store.commit('updateCremationTime', value);
      }
    },
    selectedUrnType: {
      get() {
        return this.$store.getters.getSelectedUrnType;
      },
      set(value) {
        this.$store.commit('updateSelectedUrnType', value);
      }
    },
    funeralName: {
      get() {
        return this.$store.getters.getFuneralName;
      },
      set(value) {
        this.$store.commit('updateFuneralName', value);
      }
    },
    funeralNumber: {
      get() {
        return this.$store.getters.getFuneralNumber;
      },
      set(value) {
        this.$store.commit('updateFuneralNumber', value);
      }
    },
    funeralTime: {
      get() {
        return this.$store.getters.getFuneralTime;
      },
      set(value) {
        this.$store.commit('updateFuneralTime', value);
      }
    },
    burialName: {
      get() {
        return this.$store.getters.getBurialName;
      },
      set(value) {
        this.$store.commit('updateBurialName', value);
      }
    },
    burialTime: {
      get() {
        return this.$store.getters.getBurialTime;
      },
      set(value) {
        this.$store.commit('updateBurialTime', value);
      }
    },
    engraveType: {
      get() {
        return this.$store.getters.getEngraveType;
      },
      set(value) {
        this.$store.commit('updateEngraveType', value);
      }
    },
    selectedType: {
      get() {
        return this.$store.getters.getSelectedType;
      },
      set(value) {
        this.$store.commit('updateSelectedType', value);
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
    showRouterView: {
      get() {
        return this.$store.getters.getShowRouterView;
      },
      set(value) {
        this.$store.commit('updateShowRouterView', value);
      }
    },
    showLeaderNameWarning() {
      const leaderNameLength = this.leaderName.trim().length;
      return (leaderNameLength < 2 || leaderNameLength > 6) && leaderNameLength !== 0;
    },
    showLeaderNameKoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.leaderName.length === 0)
        return false;

      return !(koreanRegex.test(this.leaderName) && !koreanConsonantVowelRegex.test(this.leaderName));
    },
    showClientNameWarning() {
      const clientNameLength = this.clientName.trim().length;
      return (clientNameLength < 2 || clientNameLength > 6) && clientNameLength !== 0;
    },
    showClientNameKoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.clientName.length === 0)
        return false;

      return !(koreanRegex.test(this.clientName) && !koreanConsonantVowelRegex.test(this.clientName));
    },
    showLeaderPhoneWarning(){
      return this.showPhoneWarning(this.leaderPhone);
    },
    showClientPhoneWarning(){
      return this.showPhoneWarning(this.clientPhone);
    },
    showDateTimeWarning1() {
      return this.showDateTimeWarning(this.cremationTime);
    },
    showDateTimeWarning2() {
      return this.showDateTimeWarning(this.funeralTime);
    },
    showDateTimeWarning3() {
      return this.showDateTimeWarning(this.burialTime);
    },
  },
  methods: {
    updateRouteData(engraveType, selectedType){
      this.engraveType = engraveType;
      this.selectedType = selectedType;
      this.name2 = '';
      this.showRouterView = true;
    },
    handlePhoneInput(event, targetPhone) {
      const inputValue = event.target.value;
      const numericValue = inputValue.replace(/\D/g, ''); // 숫자만 추출

      // 숫자 입력 값을 휴대폰 번호 형식으로 변환
      let formattedPhone = '';

      if (numericValue.length >= 4) {
        const firstPart = numericValue.substring(0, 3);
        formattedPhone += `${firstPart}-`;
        const secondPart = numericValue.substring(3, 7);

        if (numericValue.length >= 8) {
          const thirdPart = numericValue.substring(7, 11);
          formattedPhone += `${secondPart}-${thirdPart}`;
        } else {
          formattedPhone += secondPart;
        }
      } else {
        formattedPhone = inputValue;
      }

      // 마지막 글자가 하이폰인 경우 제거
      if (formattedPhone.endsWith('-')) {
        formattedPhone = formattedPhone.slice(0, -1);
      }

      // 휴대폰 번호 형식으로 변환한 값을 갱신
      if (targetPhone === 'leaderPhone') {
        this.leaderPhone = formattedPhone;
      } else if (targetPhone === 'clientPhone') {
        this.clientPhone = formattedPhone;
      }
    },
    showPhoneWarning(phone) {
      if (phone.length === 0) {
        return false;
      }
      const phonePattern = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/;

      return !phonePattern.test(phone);
    },
    cremationSeoul(){
      return ['서울1', '서울2', '서울3'];
    },
    cremationIncheon(){
      return ['인천1', '인천2'];
    },
    cremationGyeonggi(){
      return ['경기1', '경기2', '경기3', '경기4'];
    },
    cremationDaegu(){
      return ['대구1'];
    },
    handleDateTimeInput(event, targetDate) {
      const inputValue = event.target.value;
      const numericValue = inputValue.replace(/\D/g, ''); // 숫자만 추출

      // 숫자 입력 값을 날짜 형식으로 변환
      let formattedDateTime = '';

      if (numericValue.length >= 6) {
        const year = numericValue.substring(0, 4);
        formattedDateTime += year;
        const month = numericValue.substring(4, 6).padStart(2, '0');

        if (numericValue.length >= 8) {
          const day = numericValue.substring(6, 8).padStart(2, '0');
          formattedDateTime += `-${month}-${day}`;

          if(numericValue.length >= 10){
            const hour = numericValue.substring(8, 10).padStart(2, '0');
            formattedDateTime += ` ${hour}`;

            if(numericValue.length >= 12){
              const minute = numericValue.substring(10, 12).padStart(2, '0');
              formattedDateTime += `:${minute}`;
            } else {
              formattedDateTime += `:${numericValue.substring(10)}`;
            }
          } else {
            formattedDateTime += ` ${numericValue.substring(8)}`;
          }
        } else {
          formattedDateTime += `-${month}-${numericValue.substring(6)}`;
        }
      } else {
        formattedDateTime = inputValue;
      }

      // 마지막 글자가 하이폰인 경우 제거
      if (formattedDateTime.endsWith(' ') || formattedDateTime.endsWith('-') || formattedDateTime.endsWith(':')) {
        formattedDateTime = formattedDateTime.slice(0, -1);
      }

      // 날짜 형식으로 변환한 값을 갱신
      if (targetDate === 'cremationTime') {
        this.cremationTime = formattedDateTime;
      } else if (targetDate === 'funeralTime') {
        this.funeralTime = formattedDateTime;
      } else if (targetDate === 'burialTime') {
        this.burialTime = formattedDateTime;
      }
    },
    showDateTimeWarning(dateTime) {
      if (dateTime.length === 0) {
        return false;
      }

      const dateTimePattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}\s[0-9]{2}:[0-9]{2}$/;

      if (!dateTimePattern.test(dateTime)) {
        return true;
      }

      const parts = dateTime.split(' ');
      const datePart = parts[0];
      const timePart = parts[1];
      
      const dateParts = datePart.split('-');
      const year = parseInt(dateParts[0], 10);
      const month = parseInt(dateParts[1], 10);
      const day = parseInt(dateParts[2], 10);
      
      const timeParts = timePart.split(':');
      const hour = parseInt(timeParts[0], 10);
      const minute = parseInt(timeParts[1], 10);

      if (month < 1 || month > 12 || day < 1 || day > new Date(year, month, 0).getDate() || hour < 0 || hour > 23 || minute < 0 || minute > 59) {
        return true;
      }

      return false;
    },
    getDateTimeWarningMessage(dateTime) {
      const dateTimePattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}\s[0-9]{2}:[0-9]{2}$/;

      if (!dateTimePattern.test(dateTime)) {
        return "올바른 날짜 및 시간 형식(yyyy-mm-dd hh:mm)으로 입력해주세요.";
      }

      const parts = dateTime.split(' ');
      const datePart = parts[0];
      const timePart = parts[1];
      
      const dateParts = datePart.split('-');
      const year = parseInt(dateParts[0], 10);
      const month = parseInt(dateParts[1], 10);
      const day = parseInt(dateParts[2], 10);
      
      const timeParts = timePart.split(':');
      const hour = parseInt(timeParts[0], 10);
      const minute = parseInt(timeParts[1], 10);

      if (month < 1 || month > 12) {
        return "올바른 월(1~12)로 입력해주세요.";
      }

      const lastDayOfMonth = new Date(year, month, 0).getDate();
      if (day < 1 || day > lastDayOfMonth) {
        return `올바른 일(1~${lastDayOfMonth})로 입력해주세요.`;
      }

      if (hour < 0 || hour > 23) {
        return "올바른 시간(0~23)으로 입력해주세요.";
      }

      if (minute < 0 || minute > 59) {
        return "올바른 분(0~59)으로 입력해주세요.";
      }

      return "";
    },
  }
};
</script>
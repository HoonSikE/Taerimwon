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
              @click="selectedType2 = '일반'">
            <img src="../../assets/images/tablet/example/이름시안/일반.png" width="80" height="240" alt="일반">
            <span class="selectText">[일반]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '일반(본관)' }"
              @click="selectedType2 = '일반(본관)'">
            <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" width="80" height="240" alt="일반(본관)">
            <span class="selectText">[일반(본관)]</span>
          </button>
        </div>
        <div v-if="type === '기독교'">
          <!-- 기독교 -->
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '기독교' }"
              @click="selectedType2 = '기독교'">
            <img src="../../assets/images/tablet/example/이름시안/기독교.png" width="80" height="240" alt="기독교">
            <span class="selectText">[기독교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '기독교(본관)' }"
              @click="selectedType2 = '기독교(본관)'">
            <img src="../../assets/images/tablet/example/본관시안/기독교(본관).png" width="80" height="240" alt="기독교(본관)">
            <span class="selectText">[기독교(본관)]</span>
          </button>
        </div>
        <div v-if="type === '불교'">
          <!-- 불교 -->
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '불교' }"
              @click="selectedType2 = '불교'">
            <img src="../../assets/images/tablet/example/이름시안/불교.png" width="80" height="240" alt="불교">
            <span class="selectText">[불교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '불교(본관)' }"
              @click="selectedType2 = '불교(본관)'">
            <img src="../../assets/images/tablet/example/본관시안/불교(본관).png" width="80" height="240" alt="불교(본관)">
            <span class="selectText">[불교(본관)]</span>
          </button>
        </div>
        <!-- 천주교 -->
        <div v-if="type === '천주교'">
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '천주교' }"
              @click="selectedType2 = '천주교'">
            <img src="../../assets/images/tablet/example/이름시안/천주교.png" width="80" height="240" alt="천주교">
            <span class="selectText">[천주교]</span>
          </button>
          <button
              class="link-item"
              :class="{ selected: selectedType2 === '천주교(본관)' }"
              @click="selectedType2 = '천주교(본관)'">
            <img src="../../assets/images/tablet/example/본관시안/천주교(본관).png" width="80" height="240" alt="천주교(본관)">
            <span class="selectText">[천주교(본관)]</span>
          </button>
        </div>
      </span>
    </div>
    <!-- 정보입력 -->
    <div class="text-align-center">
      <span class="info-text-align-center">
        <span class="input-info1">
         <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
            본관 내용&nbsp;
          </div>
          <span v-if="selectedType === '기독교'">
            직분&nbsp;
          </span>
          <!-- <span v-if="selectedType === '법명'">
            법명 입력<br/>
          </span> -->
          <span v-if="selectedType === '천주교'">
            세례명&nbsp;
          </span>
        </span>
        <span class="input-info2">
          <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
            <input v-model="name0" type="text" :placeholder="defaultName0Placeholder"/>
          </div>
          <span v-if="selectedType === '기독교'">
            <input v-model="name2" type="text" :placeholder="name2"/>
          </span>
          <!-- <span v-if="selectedType === '법명'">
            <input v-model="name2" type="text" :placeholder="name2"/>
          </span> -->
          <span v-if="selectedType === '천주교'">
            <input v-model="name2" type="text" :placeholder="name2"/>
          </span>
        </span>
      </span>
    </div>

    
    <div v-if="showName0KoreanWarning" class="warning_text">
        - 본관을 한국어로 올바르게 입력해주세요.
    </div>
    <div v-else-if="showName0Warning" class="warning_text">
        - 본관을 5~8글자로 입력해주세요.
    </div>
    <div v-else>
      <!-- 본관에 따른 데이터 전송값 변경 -->
      <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
        <router-link :to="{name: 'result'}" @click.native="updateRouteData()" class="title4">👉 예시 보기 (각인/위패)</router-link>
      </div>
      <div v-else>
        <router-link :to="{name: 'result'}" @click.native="updateRouteData2()" class="title4">👉 예시 보기 (각인/위패)</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';

export default {
  data() {
    return {
    };
  },
  computed: {
    ...mapGetters([
      'getType',
      'getSelectedType',
      'getShowRouterView',

      'getName0',
      'getName1',
      'getName2',
      'getDate1',
      'getDate1Type',
      'getDate2',
      'getDate2Type',
      'getSelectedType2',
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
    },
    name0: {
      get() {
        return this.$store.getters.getName0;
      },
      set(value) {
        this.$store.commit('updateName0', value);
      }
    },
    name1: {
      get() {
        return this.$store.getters.getName1;
      },
    },
    name2: {
      get() {
        return this.$store.getters.getName2;
      },
    },
    date1: {
      get() {
        return this.$store.getters.getDate1;
      },
    },
    date1Type: {
      get() {
        return this.$store.getters.getDate1Type;
      },
    },
    date2: {
      get() {
        return this.$store.getters.getDate2;
      },
    },
    date2Type: {
      get() {
        return this.$store.getters.getDate2Type;
      },
    },
    selectedType2: {
      get() {
        return this.$store.getters.getSelectedType2;
      },
      set(value) {
        this.$store.commit('updateSelectedType2', value);
      }
    },
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
    showName0Warning() {
      const name0Length = this.name0.trim().length;
      return (name0Length < 5 || name0Length > 8) && name0Length !== 0;
    },
    showName0KoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.name0.length === 0)
        return false;

      // return !koreanRegex.test(this.name0);
      return !(koreanRegex.test(this.name0) && !koreanConsonantVowelRegex.test(this.name0));
    },
    updateRouteData(){
      this.name0 = decodeURIComponent(this.encodedName0());
    },
    updateRouteData2(){
      this.name0 = '없음';
    }
  },
};
</script>
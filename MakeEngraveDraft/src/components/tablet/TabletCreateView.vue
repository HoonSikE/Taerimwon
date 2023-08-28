<template>
  <div class="">
    <div class="app">
      <div class="title">
        <span class="title-gray">1-</span>2<span class="title-gray">-3</span><br>
        위패 주문하기
      </div>
    </div>
    <hr>
    <div class="app">
      <div class="title2">
        ● 위패 종류
      </div>
      <!-- 가로 스크롤 가능한 컨테이너 -->
      <div class="text-align-center">
        <div class="scroll-container">
          <span class="link-item-color">
            <!-- 일반 -->
            <div v-if="type === '일반'">
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '일반' }"
                  @click="selectedType2 = '일반'">
                <img src="../../assets/images/tablet/example/이름시안/일반.png" alt="일반">
                <span class="selectText">[일반]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '일반(본관)' }"
                  @click="selectedType2 = '일반(본관)', name0 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="일반(본관)">
                <span class="selectText">[일반(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <div v-if="type === '기독교'">
              <!-- 기독교 -->
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '기독교' }"
                  @click="selectedType2 = '기독교'">
                <img src="../../assets/images/tablet/example/이름시안/기독교.png" alt="기독교">
                <span class="selectText">[기독교]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '기독교(본관)' }"
                  @click="selectedType2 = '기독교(본관)', name0 = ''">
                <img src="../../assets/images/tablet/example/본관시안/기독교(본관).png" alt="기독교(본관)">
                <span class="selectText">[기독교(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <div v-if="type === '불교'">
              <!-- 불교 -->
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '불교' }"
                  @click="selectedType2 = '불교'">
                <img src="../../assets/images/tablet/example/이름시안/불교.png" alt="불교">
                <span class="selectText">[불교]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '불교(본관)' }"
                  @click="selectedType2 = '불교(본관)', name0 = ''">
                <img src="../../assets/images/tablet/example/본관시안/불교(본관).png" alt="불교(본관)">
                <span class="selectText">[불교(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <!-- 천주교 -->
            <div v-if="type === '천주교'">
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '천주교' }"
                  @click="selectedType2 = '천주교'">
                <img src="../../assets/images/tablet/example/이름시안/천주교.png" alt="천주교">
                <span class="selectText">[천주교]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '천주교(본관)' }"
                  @click="selectedType2 = '천주교(본관)', name0 = ''">
                <img src="../../assets/images/tablet/example/본관시안/천주교(본관).png" alt="천주교(본관)">
                <span class="selectText">[천주교(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
          </span>
        </div>
      </div>
    </div>
    <hr>
    <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)' || selectedType === '기독교' || selectedType === '천주교' || selectedType2 === '문구'" class="app">
      <!-- 정보입력 -->
      <div class="text-align-center">
        <span class="info-text-align-center">
          <span class="input-info1">
          <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
              본관 내용&nbsp;
            </div>
            <span v-if="selectedType === '기독교' && selectedType2 !== '문구'">
              직분&nbsp;
            </span>
            <!-- <span v-if="selectedType === '법명'">
              법명 입력<br/>
            </span> -->
            <span v-if="selectedType === '천주교' && selectedType2 !== '문구'">
              세례명&nbsp;
            </span>
            <span v-if="selectedType2 === '문구'">
              &nbsp;<br>문구<br>&nbsp;
            </span>
          </span>
          <span class="input-info2">
            <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
              <input v-model="name0" type="text" :placeholder="defaultName0Placeholder"/>
            </div>
            <span v-if="selectedType === '기독교' && selectedType2 !== '문구'">
              <input v-model="name2" type="text" placeholder="직분"/>
            </span>
            <!-- <span v-if="selectedType === '법명'">
              <input v-model="name2" type="text" :placeholder="name2"/>
            </span> -->
            <span v-if="selectedType === '천주교' && selectedType2 !== '문구'">
              <input v-model="name2" type="text" placeholder="세례명"/>
            </span>
            <span v-if="selectedType2 === '문구'">
              <input v-model="name3_1" type="text" placeholder="문구1"/>
              <br>
            </span>
            <span v-if="selectedType2 === '문구'">
              <input v-model="name3_2" type="text" placeholder="문구2"/>
              <br>
              <span>
                1줄: 문구 1 입력, 2줄: 문구1, 2 입력
              </span>
            </span>
          </span>
        </span>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div v-if="showName0KoreanWarning" class="warning_text">
        - 본관을 한국어로 올바르게 입력해주세요.
    </div>
    <div v-else-if="showName0Warning" class="warning_text">
        - 본관을 5~9글자로 입력해주세요.
    </div>
    <div v-else class="app">
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
      'getName3_1',
      'getName3_2',
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
      set(value) {
        this.$store.commit('updateName2', value);
      }
    },
    name3_1: {
      get() {
        return this.$store.getters.getName3_1;
      },
      set(value) {
        this.$store.commit('updateName3_1', value);
      }
    },
    name3_2: {
      get() {
        return this.$store.getters.getName3_2;
      },
      set(value) {
        this.$store.commit('updateName3_2', value);
      }
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
      if(this.selectedType2 === '일반' || this.selectedType2 === '기독교' || this.selectedType2 === '불교' || this.selectedType2 === '천주교')
        return false;
      const name0Length = this.name0.trim().length;
      return (name0Length < 5 || name0Length > 9) && name0Length !== 0;
    },
    showName0KoreanWarning() {
      if(this.selectedType2 === '일반' || this.selectedType2 === '기독교' || this.selectedType2 === '불교' || this.selectedType2 === '천주교')
        return false;
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.name0.length === 0)
        return false;

      // return !koreanRegex.test(this.name0);
      return !(koreanRegex.test(this.name0) && !koreanConsonantVowelRegex.test(this.name0));
    },
  },
  methods: {
    updateRouteData(){
      this.name0 = decodeURIComponent(this.encodedName0);
    },
    updateRouteData2(){
      this.name0 = '없음';
    }
  }
};
</script>
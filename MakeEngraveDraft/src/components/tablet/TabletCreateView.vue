<template>
  <div class="">
    <a id="scroll-anchor"></a>
    <div class="app">
      <div class="title">
        <span class="title-gray">1-2-</span>3<br>
        위패 주문
      </div>
    </div>
    <hr>
    <div class="app">
      <div class="title2">
        ● 위패 각인 종류
      </div>
      <!-- 가로 스크롤 가능한 컨테이너 -->
      <div class="text-align-center">
        <div class="scroll-container">
          <span class="link-item-color">
            <!-- 일반 -->
            <div v-if="engraveType === '일반'">
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
                  @click="selectedType2 = '일반(본관)', name3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="일반(본관)">
                <span class="selectText">[일반(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = '', name3_3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <div v-else-if="engraveType === '기독교'">
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
                  @click="selectedType2 = '기독교(본관)', name3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/기독교(본관).png" alt="기독교(본관)">
                <span class="selectText">[기독교(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = '', name3_3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <div v-else-if="engraveType === '불교'">
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
                  @click="selectedType2 = '불교(본관)', name3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/불교(본관).png" alt="불교(본관)">
                <span class="selectText">[불교(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = '', name3_3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <!-- 천주교 -->
            <div v-else-if="engraveType === '천주교'">
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
                  @click="selectedType2 = '천주교(본관)', name3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/천주교(본관).png" alt="천주교(본관)">
                <span class="selectText">[천주교(본관)]</span>
              </div>
              <div
                  class="link-item"
                  :class="{ selected: selectedType2 === '문구' }"
                  @click="selectedType2 = '문구', name3_1 = '', name3_2 = '', name3_3 = ''">
                <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
                <span class="selectText">[문구 넣기]</span>
              </div>
            </div>
            <!-- 나머지 -->
            <div v-else
                class="link-item"
                :class="{ selected: selectedType2 === '문구' }"
                @click="selectedType2 = '문구', name3_1 = '', name3_2 = '', name3_3 = ''">
              <img src="../../assets/images/tablet/example/본관시안/일반(본관).png" alt="문구">
              <span class="selectText">[문구 넣기]</span>
            </div>
          </span>
        </div>
      </div>
      <hr>
      <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)' || selectedType === '기독교' || selectedType === '천주교' || selectedType2 === '문구'">
        <!-- 정보입력 -->
        <div class="text-align-center">
          <span class="info-text-align-center">
            <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
              본관 내용
              <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)'">
                <input v-model="name3" type="text" :placeholder="defaultName3Placeholder" style="height: 30px; width: 100%;"/>
              </div>
              <div v-if="showName3KoreanWarning" class="warning_text">
                  - 본관을 한국어로 올바르게 입력해주세요.
              </div>
              <div v-else-if="showName3Warning" class="warning_text">
                  - 본관을 5~9글자로 입력해주세요.
              </div>
            </div>
            <div v-if="selectedType === '기독교' && selectedType2 !== '문구'">
              직분
            </div>
            <!-- <span v-if="selectedType === '법명'">
              법명 입력<br/>
            </span> -->
            <div v-if="selectedType === '천주교' && selectedType2 !== '문구'">
              세례명
            </div>
            <div v-if="selectedType === '기독교' && selectedType2 !== '문구'">
              <input v-model="name2" type="text" placeholder="직분" style="height: 30px; width: 100%;"/>
              <div v-if="showName2KoreanWarning" class="warning_text">
                - 직분을 한국어로 올바르게 입력해주세요.
              </div>
              <div v-else-if="showName2Warning" class="warning_text">
                - 직분을 2~4글자로 입력해주세요.
              </div>
            </div>
            <!-- <span v-if="selectedType === '법명'">
              <input v-model="name2" type="text" :placeholder="name2"/>
            </span> -->
            <div v-if="selectedType === '천주교' && selectedType2 !== '문구'">
              <input v-model="name2" type="text" placeholder="세례명" style="height: 30px; width: 100%;"/>
              <div v-if="showName2KoreanWarning" class="warning_text">
                - 세례명을 한국어로 올바르게 입력해주세요.
              </div>
              <div v-else-if="showName2Warning2" class="warning_text">
                - 세례명을 2~6글자로 입력해주세요.
              </div>
            </div>
            <div v-if="selectedType2 === '문구'">
              문구<br>
              <select v-model="name3Type" style="height: 30px; width: 100%;">
                <option value="" selected disabled>줄 선택</option>
                <option value="one">1줄</option>
                <option value="two">2줄</option>
                <option value="three">3줄</option>
              </select>
              <div>
                <div v-if="name3Type === 'one' || name3Type === 'two' || name3Type === 'three'">
                  문구1<br>
                  <input v-model="name3_1" type="text" placeholder="예) 아버지 사랑합니다." style="height: 30px; width: 100%;"/>
                  <span @click="addHeart1" style="font-size: 15px; width: 15px; margin-left: -30px; cursor: pointer;">
                    ❤️
                  </span>
                  <div v-if="showName3_1Warning" class="warning_text">
                    - 문구1을 10글자 이하로 입력해주세요.
                  </div>
                </div>
                <div v-if="name3Type === 'two' || name3Type === 'three'">
                  문구2<br>
                  <input v-model="name3_2" type="text" placeholder="예) 아버지 사랑합니다." style="height: 30px; width: 100%;"/>
                  <span @click="addHeart2" style="font-size: 15px; width: 15px; margin-left: -30px; cursor: pointer;">
                    ❤️
                  </span>
                  <div v-if="showName3_2Warning" class="warning_text">
                    - 문구2를 10글자 이하로 입력해주세요.
                  </div>
                </div>
                <div v-if="name3Type === 'three'">
                  문구3<br>
                  <input v-model="name3_3" type="text" placeholder="예) 아버지 사랑합니다." style="height: 30px; width: 100%;"/>
                    <span @click="addHeart3" style="font-size: 15px; width: 15px; margin-left: -30px; cursor: pointer;">
                    ❤️
                  </span>
                  <div v-if="showName3_3Warning" class="warning_text">
                    - 문구3을 10글자 이하로 입력해주세요.
                  </div>
                </div>
              </div>
              <div class="title7">
                &nbsp;* 1줄 최대 10자(공백포함)까지 입력가능합니다.
              </div>
            </div>
          </span>
        </div>
      </div>
      <div>
        위패 종류<br>
        <select :value="selectedTabletType" @change="updateSelectedTabletType" style="height: 30px; width: 100%;">
          <option value="" disabled>위패 선택</option>
          <option v-for="tablet in tabletTypes" :value="tablet" :key="tablet">{{ tablet }}</option>
        </select>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div>
        특이사항 (40자 이내)<br>
        <input v-model="note" type="text" placeholder="특이사항을 적어주세요." style="height: 30px; width: 100%;"/>
        <div v-if="showNoteWarning" class="warning_text">
          - 특이사항을 40글자 이하로 입력해주세요.
        </div>
      </div>
      <br>
      <!-- 본관에 따른 데이터 전송값 변경 -->
      <div v-if="selectedType2 === '일반(본관)' || selectedType2 === '기독교(본관)' || selectedType2 === '불교(본관)' || selectedType2 === '천주교(본관)' || selectedType2 === '문구'">
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
      'getEngraveType',
      'getSelectedType',
      'getShowRouterView',

      'getName1',
      'getName2',
      'getName3',

      'getName3Type',
      'getName3_1',
      'getName3_2',
      'getName3_3',

      'getDate1',
      'getDate1Type',
      'getDate2',
      'getDate2Type',
      'getSelectedType2',
      'getSelectedTabletType',
      'getNote',
    ]),
    engraveType: {
      get() {
        return this.$store.getters.getEngraveType;
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
    name3: {
      get() {
        return this.$store.getters.getName3;
      },
      set(value) {
        this.$store.commit('updateName3', value);
      }
    },
    name3Type: {
      get() {
        return this.$store.getters.getName3Type;
      },
      set(value) {
        this.$store.commit('updateName3Type', value);
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
    name3_3: {
      get() {
        return this.$store.getters.getName3_3;
      },
      set(value) {
        this.$store.commit('updateName3_3', value);
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
    tabletTypes() {
      return ['흰색', '검정', '투명'];
    },
    selectedTabletType: {
      get() {
        return this.$store.getters.getSelectedTabletType;
      },
      set(value) {
        this.$store.commit('updateSelectedTabletType', value);
      }
    },
    note: {
      get() {
        return this.$store.getters.getNote;
      },
      set(value) {
        this.$store.commit('updateNote', value);
      }
    },
    encodedName3() {
      const trimmedName3 = this.name3.trim();

      if(this.selectedType2 === "일반(본관)")
        return trimmedName3 === '' ? '희빈홍씨길동신위' : encodeURIComponent(trimmedName3);
      else if(this.selectedType2 === "기독교(본관)")
        return trimmedName3 === '' ? '희빈홍씨길동' : encodeURIComponent(trimmedName3);
      else if(this.selectedType2 === "불교(본관)")
        return trimmedName3 === '' ? '희빈홍씨길동영가' : encodeURIComponent(trimmedName3);
      else if(this.selectedType2 === "천주교(본관)")
        return trimmedName3 === '' ? '희빈홍씨길동' : encodeURIComponent(trimmedName3);

      return trimmedName3 === '' ? '본관' : encodeURIComponent(trimmedName3);
    },
    defaultName3Placeholder() {
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
    showName2Warning() {
      const name2Length = this.name2.trim().length;
      return (name2Length < 2 || name2Length > 4) && name2Length !== 0;
    },
    showName2Warning2() {
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
    showName3Warning() {
      if(this.selectedType2 === '일반' || this.selectedType2 === '기독교' || this.selectedType2 === '불교' || this.selectedType2 === '천주교')
        return false;
      const name3Length = this.name3.trim().length;
      return (name3Length < 5 || name3Length > 9) && name3Length !== 0;
    },
    showName3_1Warning() {
      if(this.selectedType2 !== '문구')
        return false;
      const name3_1Length = this.name3_1.trim().length;
      return (name3_1Length < 1 || name3_1Length > 10) && name3_1Length !== 0;
    },
    showName3_2Warning() {
      if(this.selectedType2 !== '문구')
        return false;
      const name3_2Length = this.name3_2.trim().length;
      return (name3_2Length < 1 || name3_2Length > 10) && name3_2Length !== 0;
    },
    showName3_3Warning() {
      if(this.selectedType2 !== '문구')
        return false;
      const name3_3Length = this.name3_3.trim().length;
      return (name3_3Length < 1 || name3_3Length > 10) && name3_3Length !== 0;
    },
    showName3KoreanWarning() {
      if(this.selectedType2 === '일반' || this.selectedType2 === '기독교' || this.selectedType2 === '불교' || this.selectedType2 === '천주교')
        return false;
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.name3.length === 0)
        return false;

      // return !koreanRegex.test(this.name3);
      return !(koreanRegex.test(this.name3) && !koreanConsonantVowelRegex.test(this.name3));
    },
    showNoteWarning() {
      if(this.note.length === 0)
        return false;
      const noteLength = this.note.trim().length;
      return (noteLength < 1 || noteLength > 40) && noteLength !== 0;
    },
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 anchor 부분까지 스크롤
    const anchorElement = document.getElementById('scroll-anchor');
    if (anchorElement) {
      anchorElement.scrollIntoView();
    }
  },
  methods: {
    updateRouteData(){
      this.name3 = decodeURIComponent(this.encodedName3);
    },
    updateRouteData2(){
      this.name3 = '없음';
    },
    updateSelectedTabletType(event) {
      this.selectedTabletType = event.target.value;
    },
    addHeart1() {
      if (this.name3_1.length < 10) {
        this.name3_1 += '❤️'; // 하트 아이콘 추가
      }
    },
    addHeart2() {
      if (this.name3_2.length < 10) {
        this.name3_2 += '❤️'; // 하트 아이콘 추가
      }
    },
    addHeart3() {
      if (this.name3_3.length < 10) {
        this.name3_3 += '❤️'; // 하트 아이콘 추가
      }
    },
  }
};
</script>
<template>
  <div class="app">
    <router-link v-if="showRouterView" :to="{name: 'engraveDetail'}" class="title4">
          👉 [이전 페이지]
    </router-link>
    <router-link v-if="!showRouterView" :to="{name: 'tabletCreateView'}" @click.native="updateRouteData()" class="title4">
          👉 [이전 페이지]
    </router-link>

    <div class="title2">
      <div class="title">
        <span class="title-gray">1-2-</span>3
      </div>
      <span>● 각인</span>
      <span class="title6">({{type}} 
        <span v-if="type !== selectedType"> [{{selectedType}}] </span>
        )
      </span>
      <span v-if="selectedType2 !== '없음'">
        / 위패
        <span class="title6">({{type}} 
          <span v-if="name0 !== '없음'"> [본관] </span>
          )
        </span>
      </span>
        예시
    </div>
    <div class="container" :class="{ 'fullscreen': isFullscreen }" @click="toggleFullscreen">
      <!-- 각인 -->
      <div class="engrave_container">
        <div class="engrave_image_container">
          <img class="engrave_image" v-if="engraveCapturedImage" :src="engraveCapturedImage" alt="각인 예시 사진" />
        </div>
      </div>
      <!-- 위패 -->
      <div class="tablet_container" v-if="selectedType2 !== '없음'">
        <div class="tablet_image_container">
          <img class="tablet_image" v-if="tabletCapturedImage" :src="tabletCapturedImage" alt="위패 예시 사진" />
        </div>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="text-align-center">
      <!-- 사진 다운로드 버튼 -->
      <button class="download-button" @click="downloadContainer">사진 다운로드</button>
    </div>
    <!-- 각인 -->
    <div v-if="engraveImageContainerVisible" class="image-text-container" ref="engraveImageContainer">
      <div class="text-container">
        <span class="resultText1">
          <span v-if="type == '일반' || type == '불교' || type == '묘법' || type == 'SGI'" class="resultText1_0_1">生</span>
          <span v-if="type == '기독교'" class="resultText1_0_2">出生</span>
          <span v-if="type == '천주교'" class="resultText1_0_2">出生</span>

          <span class="resultText1_1">{{date1.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date1.substr(1, 1)}}</span>
          <span class="resultText1_1">{{date1.substr(2, 1)}}</span>
          <span class="resultText1_1">{{date1.substr(3, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date1.substr(5, 1)}}</span>
          <span class="resultText1_1">{{date1.substr(6, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date1.substr(8, 1)}}</span>
          <span class="resultText1_1">{{date1.substr(9, 1)}}</span>
          <span v-if="date1Type==='음력'" class="resultText1_3">陰</span>
          <span v-if="date1Type==='양력'" class="resultText1_3">陽</span>
        </span>
        <!-- 일반, 기독교, 불교, 천주교-->
        <span class="resultText2" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교'
                                  || selectedType === '천주교'">
          <div :class="getResult2Mark"></div>
          <span class="resultText2_0" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_0-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <!-- 형제(아래)) -->
        <span class="resultText2" v-if="selectedType === '형제'">
          <div :class="getResult2Mark"></div>
          <span class="resultText2_1" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_1-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
          <span class="resultText2_1_down">
            형제
          </span>
        </span>
        <!-- SGI(위)) -->
        <span class="resultText2" v-if="selectedType === 'SGI'">
          <div :class="getResult2Mark"></div>
          <span class="resultText2_2_up">
            SGI
          </span>
          <span class="resultText2_2" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_2-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <!-- 묘볍(위아래) -->
        <span class="resultText2" v-if="selectedType === '묘법'">
          <div :class="getResult2Mark"></div>
          <span class="resultText2_3_up">
            妙法
          </span>
          <span class="resultText2_3" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_3-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
           <span class="resultText2_3_down">
            位
          </span>
        </span>
        <!-- 세례명(아래)) -->
        <span class="resultText2" v-if="selectedType === '세례명'">
          <div :class="getResult2Mark"></div>
          <span class="resultText2_4_1" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_4_1-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
          <span class="resultText2_4_2" v-if="name2.length === 2 || name2.length === 3">
            {{name2}}
          </span>
          <span class="resultText2_4_2-4" v-if="name2.length === 4">
            {{name2}}
          </span>
          <span class="resultText2_4_2-5" v-if="name2.length === 5">
            {{name2}}
          </span>
          <span class="resultText2_4_2-6" v-if="name2.length === 6">
            {{name2}}
          </span>
        </span>
        <!-- 직분(위)-->
        <span class="resultText2" v-if="selectedType === '직분' || selectedType === '법명'">
          <div :class="getResult2Mark"></div>
          <span class="resultText2_5_2" v-if="name2.length === 2 || name2.length === 3">
            {{name2}}
          </span>
          <span class="resultText2_5_2-4" v-if="name2.length === 4">
            {{name2}}
          </span>
          <span class="resultText2_5_1" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_5_1-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <span class="resultText1">
          <span v-if="type == '일반' || type == '불교' || type == '묘법' || type == 'SGI'" class="resultText1_0_1">卒</span>
          <span v-if="type == '기독교'" class="resultText1_0_2">召天</span>
          <span v-if="type == '천주교'" class="resultText1_0_3">善終</span>

          <span class="resultText1_1">{{date2.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date2.substr(1, 1)}}</span>
          <span class="resultText1_1">{{date2.substr(2, 1)}}</span>
          <span class="resultText1_1">{{date2.substr(3, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date2.substr(5, 1)}}</span>
          <span class="resultText1_1">{{date2.substr(6, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date2.substr(8, 1)}}</span>
          <span class="resultText1_1">{{date2.substr(9, 1)}}</span>
          <span v-if="date2Type==='음력'" class="resultText1_3">陰</span>
          <span v-if="date2Type==='양력'" class="resultText1_3">陽</span>
        </span>
      </div>
    </div>
    <!-- 위패 -->
    <div v-if="tabletImageContainerVisible && selectedType2 !== '없음'" class="image-text-container2" ref="tabletImageContainer">
      <div class="text-container2">
        <!-- 본관 선택 x-->
        <div v-if="name0 === '없음'" class="resultText3">
          <div :class="getResult3Mark"></div>
          <!-- 일반 -->
          <span class="" v-if="type === '일반' || type === '불교'">
            <span class="resultText3_1" v-if="name1.length === 2 || name1.length === 3">
              {{encodedName1}}
            </span>
            <span class="resultText3_1-4" v-if="name1.length === 4">
              {{encodedName1}}
            </span>
          </span>
          <!-- 위 글자 -->
          <span class="" v-if="type === '기독교'">
            <span class="resultText3_2_2" v-if="name2.length === 2 || name2.length === 3">
              {{name2}}
            </span>
            <span class="resultText3_2_2-4" v-if="name2.length === 4">
              {{name2}}
            </span>
            <span class="resultText3_2_1" v-if="name1.length === 2 || name1.length === 3">
              {{encodedName1}}
            </span>
            <span class="resultText3_2_1-4" v-if="name1.length === 4">
              {{encodedName1}}
            </span>
          </span>
          <!-- 아래 글자 -->
          <span class="" v-if="type === '천주교'">
            <span class="resultText3_3_1" v-if="name1.length === 2 || name1.length === 3">
              {{encodedName1}}
            </span>
            <span class="resultText3_3_1-4" v-if="name1.length === 4">
              {{encodedName1}}
            </span>
            <span class="resultText3_3_2" v-if="name2.length === 2 || name2.length === 3">
              {{name2}}
            </span>
            <span class="resultText3_3_2-4" v-if="name2.length === 4">
              {{name2}}
            </span>
            <span class="resultText3_3_2-5" v-if="name2.length === 5">
              {{name2}}
            </span>
            <span class="resultText3_3_2-6" v-if="name2.length === 6">
              {{name2}}
            </span>
          </span>
        </div>
        <!-- 본관 선택 o -->
        <div v-else class="resultText3">
          <div :class="getResult4Mark"></div>
          <!-- 일반 -->
          <span class="resultText4_1_1" data-letter-count="5" v-if="type === '일반' && name0.length=== 5">
            {{name0}}
          </span>
          <span class="resultText4_1_1" data-letter-count="6" v-if="type === '일반' && name0.length=== 6">
            {{name0}}
          </span>
          <span class="resultText4_1_1" data-letter-count="7" v-if="type === '일반' && name0.length=== 7">
            {{name0}}
          </span>
          <span class="resultText4_1_1" data-letter-count="8" v-if="type === '일반' && name0.length=== 8">
            {{name0}}
          </span>
          <span class="resultText4_1_1" data-letter-count="9" v-if="type === '일반' && name0.length=== 9">
            {{name0}}
          </span>
          <!-- 불교 -->
          <span class="resultText4_1_2" data-letter-count="5" v-if="type === '불교' && name0.length=== 5">
            {{name0}}
          </span>
          <span class="resultText4_1_2" data-letter-count="6" v-if="type === '불교' && name0.length=== 6">
            {{name0}}
          </span>
          <span class="resultText4_1_2" data-letter-count="7" v-if="type === '불교' && name0.length=== 7">
            {{name0}}
          </span>
          <span class="resultText4_1_2" data-letter-count="8" v-if="type === '불교' && name0.length=== 8">
            {{name0}}
          </span>
          <span class="resultText4_1_2" data-letter-count="9" v-if="type === '불교' && name0.length=== 9">
            {{name0}}
          </span>
          <!-- 기독교 직분 위 글자 -->
          <span class="resultText4_2" v-if="type === '기독교'">
            <!-- 직분 -->
            <span class="resultText4_2_2">{{name2}}</span>
            <!-- 이름 -->
            <span class="resultText4_2_1" data-letter-count="5" v-if="name0.length=== 5">
              {{name0}}
            </span>
            <span class="resultText4_2_1" data-letter-count="6" v-if="name0.length=== 6">
              {{name0}}
            </span>
            <span class="resultText4_2_1" data-letter-count="7" v-if="name0.length=== 7">
              {{name0}}
            </span>
            <span class="resultText4_2_1" data-letter-count="8" v-if="name0.length=== 8">
              {{name0}}
            </span>
            <span class="resultText4_2_1" data-letter-count="9" v-if="name0.length=== 9">
              {{name0}}
            </span>
            <!-- 아래 -->
            <span class="resultText4_2_3">
              召天
            </span>
          </span>
          <!-- 천주교 세례명 아래 글자 -->
          <span class="resultText4_3" v-if="type === '천주교'">
            <!-- 이름 -->
            <span class="resultText4_3_1" data-letter-count="5" v-if="name0.length=== 5">
              {{name0}}
            </span>
            <span class="resultText4_3_1" data-letter-count="6" v-if="name0.length=== 6">
              {{name0}}
            </span>
            <span class="resultText4_3_1" data-letter-count="7" v-if="name0.length=== 7">
              {{name0}}
            </span>
            <span class="resultText4_3_1" data-letter-count="8" v-if="name0.length=== 8">
              {{name0}}
            </span>
            <span class="resultText4_3_1" data-letter-count="9" v-if="name0.length=== 9">
              {{name0}}
            </span>
            <!-- 세례명 -->
            <span class="resultText4_3_2" v-if="name2.length === 2 || name2.length === 3">
              {{name2}}
            </span>
            <span class="resultText4_3_2" v-if="name2.length === 4">
              {{name2}}
            </span>
            <span class="resultText4_3_2" v-if="name2.length === 5">
              {{name2}}
            </span>
            <span class="resultText4_3_2-6" v-if="name2.length === 6">
              {{name2}}
            </span>
          </span>
        </div>
      </div>
    </div>
      
    <div>
      각인 종류: {{this.type}}<br>
      상세 종류: {{this.selectedType}}<br>
      <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
        {{this.selectedType}}명: {{this.name2}}<br>
      </div>
      <hr>
      고인 성함: {{this.name1}}<br>
      출생일: {{this.date1}} {{this.date1Type}}<br>
      사망일: {{this.date2}} {{this.date2Type}}<br>
      <hr>
      <div v-if="selectedType2 === '없음'">
        위패 유무: X<br>
      </div>
      <div v-else>
        위패 유무: O<br>
        <div v-if="name0 !== '없음'">
          위패 종류: 본관<br>
          본관 내용: {{this.name0}}<br>
        </div>
        <div v-else>
          위패 종류: 일반<br>
          <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
            {{this.selectedType}}명: {{this.name2}}<br>
          </div>
          고인 성함: {{this.name1}}<br>
        </div>
      </div>
    </div>

    <!-- <a v-if="isIOS" class="title8" :href="iosSMSEntry">아이폰 SMS 보내기</a> -->
    <!-- <a v-if="isAndroid" class="title8" :href="androidSMSEntry">안드로이드 SMS 보내기</a><br> -->
    <!-- <a v-if="isUnknown" class="title8">문자호환되지 않는 기종입니다.</a> -->
  </div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import html2canvas from 'html2canvas';

export default {
  data() {
    return {
      isFullscreen: false,

      engraveCapturedImage: null,
      tabletCapturedImage: null,
      engraveImageContainerVisible: true,
      tabletImageContainerVisible: true,
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
    },

    // 외자 이름
    encodedName1() {
      const trimmedName1 = this.name1.trim();

      if(trimmedName1.length === 2){
        return trimmedName1.substr(0, 1) + " " + trimmedName1.substr(1, 1);
      }
      return trimmedName1;
    },
    // 마크 선택
    getResult2Mark() {
      let markImageUrl = '';

      if (this.type === '일반') {
        return 'resultText2_mark1';
      } else if (this.type === '기독교') {
        return 'resultText2_mark2';
      } else if (this.type === '불교') {
        return 'resultText2_mark3';
      } else if (this.type === '천주교') {
        return 'resultText2_mark4';
      }  else if (this.selectedType === 'SGI' || this.selectedType === '묘법') {
        return 'resultText2_mark5';
      }  else if (this.selectedType === '금') {
        return 'resultText2_mark6';
      }
    },
    getResult3Mark() {
      let markImageUrl = '';

      if (this.type === '일반') {
        return 'resultText3_mark1';
      } else if (this.type === '기독교') {
        return 'resultText3_mark2';
      } else if (this.type === '불교') {
        return 'resultText3_mark3';
      } else if (this.type === '천주교') {
        return 'resultText3_mark4';
      }
    },
    getResult4Mark() {
      let markImageUrl = '';

      if (this.type === '기독교') {
        return 'resultText4_mark2';
      } else if (this.type === '불교') {
        return 'resultText4_mark3';
      } else if (this.type === '천주교') {
        return 'resultText4_mark4';
      }
    },
    isIOS() {
      return this.checkMobile() === 'ios';
    },
    iosSMSEntry() {
      const phoneNumber = '01045097485';

      const message = encodeURIComponent(this.getMsg());
      return `sms:${phoneNumber}&body=${message}`;
    },
    isAndroid() {
      return this.checkMobile() === 'android';
    },
    androidSMSEntry() {
      const phoneNumber = '01045097485';
      const message = encodeURIComponent(this.getMsg());
      return `sms:${phoneNumber}?body=${message}`;
    },
    isUnknown() {
      return this.checkMobile() === 'unknown';
    },
  },
  mounted() {
    this.captureAndDisplay();
  },
  methods: {
    checkMobile() {
      // 현재 사용자 기기가 iOS인지 Android인지 확인하는 메서드
      const userAgent = navigator.userAgent || navigator.vendor || window.opera;
      if (/iPad|iPhone|iPod/.test(userAgent) && !window.MSStream) {
        return "ios";
      } else if (/android/i.test(userAgent)) {
        return "android";
      }
      return "unknown";
    },
    toggleFullscreen() {
      this.isFullscreen = !this.isFullscreen;
      if (this.isFullscreen) {
        this.zoomIn();
      } else {
        this.zoomOut();
      }
    },
    zoomIn() {
      const imageContainer = document.querySelector('.container');
      imageContainer.style.transform = 'scale(2)'; // 예시로 확대 배율을 2배로 설정
    },
    zoomOut() {
      const imageContainer = document.querySelector('.container');
      imageContainer.style.transform = 'scale(1)'; // 원래 크기로 설정
    },
    getMsg() {
      var msg = '[각인 주문]\n'
        + '각인 종류: ' + this.type + '\n상세 종류: ' + this.selectedType;
        
      if(this.selectedType === '직분' || this.selectedType === '법명' || this.selectedType === '세례명')
        msg += '\n' + this.selectedType + '명: ' + this.name2;

      msg += '\n\n고인 성함: ' + this.name1
        + '\n출생일: ' + this.date1 + ' ' + this.date1Type 
        + '\n사망일: ' + this.date2 + ' ' + this.date2Type;

      if(this.selectedType2 == '없음')
        msg += '\n위패 유무: X';
      else {
        msg += '\n위패 유무: O';
        if(this.name0 !== '없음'){
          msg += '\n위패 종류: 본관' + '\n본관 내용: ' + this.name0;
        }else{
          msg += '\n위패 종류: 일반';
          if(this.selectedType === '직분' || this.selectedType === '법명' || this.selectedType === '세례명')
            msg += '\n' + this.selectedType + '명: ' + this.name2;
          msg += '\n고인 성함: ' + this.name1;
        }
      }
      console.log(msg);

      return msg;
    },
    async captureAndDisplay() {
      // 각인
      const engraveImageContainer = this.$refs.engraveImageContainer;

      // 이미지 컨테이너 캡처
      const engraveCanvas = await html2canvas(engraveImageContainer);
      const engraveCapturedImageDataUrl = engraveCanvas.toDataURL("각인 예시/png");

      this.engraveCapturedImage = engraveCapturedImageDataUrl;
      this.engraveImageContainerVisible = !this.engraveImageContainerVisible; // 이미지 컨테이너를 숨김

      // 위패
      const tabletImageContainer = this.$refs.tabletImageContainer;

      // 이미지 컨테이너 캡처
      const tabletCanvas = await html2canvas(tabletImageContainer);
      const tabletCapturedImageDataUrl = tabletCanvas.toDataURL("위패 예시/png");

      this.tabletCapturedImage = tabletCapturedImageDataUrl;
      this.tabletImageContainerVisible = !this.tabletImageContainerVisible; // 이미지 컨테이너를 숨김
    },
    async downloadContainer() {
      const container = document.querySelector('.container'); // 캡처할 요소 선택
      const canvas = await html2canvas(container); // HTML2Canvas를 사용하여 캡처

      // 이미지 데이터를 URL로 변환
      const imageDataURL = canvas.toDataURL('image/png');

      // 이미지 다운로드 링크 생성
      const link = document.createElement('a');
      link.href = imageDataURL;
      link.download = '태림원 예시.png'; // 다운로드될 이미지 파일의 이름 설정
      link.target = '_blank'; // 새 창에서 열리도록 설정
      link.click(); // 클릭 이벤트 실행
    },
    updateRouteData(){
      if(this.name0 === '없음')
        this.name0 = '';
    },
  },
};
</script>

<style>
.fullscreen {
  z-index: 1000;
  background-color: rgba(0, 0, 0, 0.8);
}
/* ======================== */
.container {
  display: flex;
  /* justify-content: space-between; 가로로 나란히 정렬 */
  justify-content: center; /* 가운데 정렬 추가 */
  align-items: flex-end;

  margin: 0 auto;
  width: 40vw;
  /* max-width: 1200px; */

  /* text-align: center; */

  background-color: rgb(255, 255, 255);
}
/* 캡처 이미지 출력 */
.engrave_container {
  position: relative;
  width: 30vw;
  /* height: 50vh; */
  margin-bottom: -10px;
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(226, 245, 100); */
}
.engrave_image {
  max-width: 30vw;
  max-height: 60vh;
}
.tablet_container {
  position: relative;
  width: 10vw;
  /* height: 50vh; */
  margin-bottom: -10px;

  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(226, 245, 100); */
}
.tablet_image {
  max-width: 10vw;
  max-height: 30vh;
}
/*======각인 시작======*/
/* 이미지 출력 */
.image-text-container {
  /* position: relative; */
  width: 540px;
  height: 580px;
  background-image: url('../../assets/images/engrave/background/engrave.png');
  background-repeat: no-repeat;
  background-size: cover;
  /* background-color: rgb(226, 245, 100); */
}
/* 텍스트 출력 */
.text-container {
  width: 160px;
  height: 270px;

  /* 중심 포지션 270 240 */
  transform: translate(190px, 270px);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}

/*===좌우 글씨===*/
.resultText1 {
  display: flex;
  justify-content: center;
  align-items: center;

  width: 33%;
  height: 100%;
  /* background-color: rgba(9, 255, 0, 0.551); */
}
/* 한자 */
.resultText1_0_1 {
  color: black;
  font-family: "HYHaeSo";
  font-weight: 900;
 
  font-size: 28px;
  margin-top: 30px;
  margin-bottom: 3px;

  width: 100%;
  /* height: 100%; */

  /* 가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(17, 255, 0, 0.551); */
}
.resultText1_0_2 {
  color: black;
  font-family: "HYHaeSo";
  font-weight: 900;
 
  font-size: 28px;
  margin-top: 30px;
  margin-bottom: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  letter-spacing: -6px;

  width: 100%;
  /* height: 100%; */

  /* 가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(17, 255, 0, 0.551); */
}
.resultText1_0_3 {
  color: black;
  font-family: "HYHaeSo";
  font-weight: 900;
 
  font-size: 28px;
  margin-top: 30px;
  margin-bottom: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  letter-spacing: -4px;

  /* 가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(17, 255, 0, 0.551); */
}
/* 숫자 */
.resultText1_1 {
  color: black;
  font-family: "CENTURY";
  font-weight: 900;

  font-size: 18px;
  margin-top: -4px;

  width: 100%;
  /* height: 100%; */

  margin-left: 13px;
  
  /* 가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(255, 157, 0, 0.551); */
}
/* 점 */
.resultText1_2 {
  color: black;
  font-weight: bold;
  font-family: "CENTURY";

  font-size: 18px;
  margin-top: -5px;
  margin-bottom: -1px;

  width: 100%;
  /* height: 100%; */

  margin-left: 18px;

  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(0, 30, 255, 0.551); */
}
/* 한자 */
.resultText1_3 {
  color: black;
  font-family: "HYHaeSo";
  font-weight: 900;
 
  font-size: 28px;
  margin-top: 3px;
  /* letter-spacing:-0em; */

  width: 100%;
  /* height: 100%; */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(17, 255, 0, 0.551); */
}

/*===이름 + 마크===*/
.resultText2 {
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 33%;
  height: 100%;
  /* background-color: rgba(226, 74, 74, 0.662); */
}

/*==마크==*/
/* 일반 */
.resultText2_mark1 {
  width: 60px;
  height: 60px;

  margin-top: 15px;
  background-image: url('../../assets/images/marks/일반.png');
  background-repeat: no-repeat;
  background-size: contain;

  /* background-color: rgba(226, 74, 74, 0.662); */
}
/* 기독교 */
.resultText2_mark2 {
  width: 60px;
  height: 60px;

  margin-top: 15px;
  margin-left: 4px;
  background-image: url('../../assets/images/marks/Christian.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 불교 */
.resultText2_mark3 {
  width: 60px;
  height: 60px;

  margin-top: 15px;
  background-image: url('../../assets/images/marks/Buddhism.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 천주교 */
.resultText2_mark4 {
  width: 60px;
  height: 60px;

  margin-top: 15px;
  margin-left: 4px;
  background-image: url('../../assets/images/marks/Catholic.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* SGI, 묘법(검정)) */
.resultText2_mark5 {
  width: 50px;
  height: 50px;

  margin-top: 15px;
  margin-left: 4px;
  background-image: url('../../assets/images/marks/묘법(검정).png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 묘법, 묘법(금)*/
.resultText2_mark6 {
  width: 50px;
  height: 50px;

  margin-top: 15px;
  margin-left: 4px;
  background-image: url('../../assets/images/marks/묘법(금).png');
  background-repeat: no-repeat;
  background-size: contain;
}

/*==이름==*/
/* 일반, 기독교, 불교, 천주교 */
.resultText2_0 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:25px;

  /* background-color: rgba(145, 108, 108, 0.662); */
}
.resultText2_0-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:5px;
  /* background-color: rgba(145, 108, 108, 0.662); */
}
/* 형제(아래) */
.resultText2_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:20px;
  margin-bottom: -20px;

  /* background-color: rgba(70, 79, 10, 0.475); */
}
.resultText2_1-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 38px;
  letter-spacing:5px;
  margin-bottom: -10px;

  /* background-color: rgba(70, 79, 10, 0.475); */
}
.resultText2_1_down {
  color: black;
  font-family: "HYGungSo";
  font-size: 21px;
  /* letter-spacing: -6px; */

  margin-bottom: 5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(70, 79, 10, 0.475); */
}
/* SGI(위) */
.resultText2_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:20px;
  margin-bottom: -20px;
  /* background-color: rgba(70, 79, 10, 0.481); */
}
.resultText2_2-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 38px;
  letter-spacing:5px;
  margin-bottom: -10px;
  /* background-color: rgb(70, 79, 10); */
}
.resultText2_2_up {
  color: black;
  font-family: "HYGungSo";
  font-size: 21px;
  /* letter-spacing: -6px; */

  margin-top: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(70, 79, 10, 0.475); */
}
/* 묘법(위아래) */
.resultText2_3 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing: 10px;
  margin-top: -5px;
  margin-bottom: -15px;
  /* background-color: rgba(70, 79, 10, 0.511); */
}
.resultText2_3-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 30px;
  letter-spacing:5px;
  margin-bottom: -10px;
  /* background-color: rgb(70, 79, 10); */
}
.resultText2_3_up {
  color: black;
  font-family: "HYHaeSo";
  font-weight: 900;
  font-size: 24px;
  /* letter-spacing: -6px; */

  margin-top: -8px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(70, 79, 10, 0.475); */
}
.resultText2_3_down {
  color: black;
  font-family: "HYHaeSo";
  font-weight: 900;
  font-size: 30px;
  /* letter-spacing: -6px; */

  margin-bottom: -2px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(70, 79, 10, 0.475); */
}
/* 세례명(아래) */
/* 이름1 */
.resultText2_4_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:20px;
  margin-top: 0px;
  margin-bottom: -20px;

  /* background-color: rgb(70, 79, 10); */
}
.resultText2_4_1-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 38px;
  letter-spacing:5px;
  margin-bottom: -10px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_4_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 23px;
  letter-spacing: -1px;

  margin-top: 3px;
  margin-bottom: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 23px;
  letter-spacing: -4px;

  margin-top: 3px;
  margin-bottom: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-5 {
  color: black;
  font-family: "HYGungSo";
  font-size: 21px;
  letter-spacing: -6px;

  margin-top: 3px;
  margin-bottom: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-6 {
  color: black;
  font-family: "HYGungSo";
  font-size: 18.5px;
  letter-spacing: -6px;

  margin-top: 5px;
  margin-bottom: -5px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  /* background-color: rgb(246, 71, 2); */
}
/* 직분(위)*/
/* 이름1 */
.resultText2_5_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:18px;
  margin-top: 0px;
  margin-bottom: -20px;
  /* background-color: rgb(70, 79, 10); */
}
.resultText2_5_1-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 38px;
  letter-spacing:5px;
  margin-bottom: -10px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_5_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 21px;
  /* letter-spacing: -6px; */

  margin-top: -10px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_5_2-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 21px;
  letter-spacing: -5px;

  margin-top: -10px;
  writing-mode: horizontal-tb; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
  /* background-color: rgb(246, 71, 2); */
}
/*======각인 끝======*/
/*======위패 시작======*/
/* 이미지 출력 */
.image-text-container2 {
  /* position: relative; */
  width: 200px;
  height: 480px;
  background-image: url('../../assets/images/tablet/background/tablet.png');
  background-repeat: no-repeat;
  background-size: cover;
  /* background-color: rgb(226, 245, 100); */
}
/* 텍스트 출력 */
.text-container2 {
  width: 150px;
  height: 420px;

  /* 중심 포지션 270 240 */
  transform: translate(27px, 45px);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}

/*===이름 + 마크===*/
.resultText3 {
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 100%;
  height: 100%;
  /* background-color: rgba(93, 184, 249, 0.56); */
}

/*==마크==*/
/* 일반 */
.resultText3_mark1 {
  width: 70px;
  height: 70px;

  background-image: url('../../assets/images/marks/일반.png');
  background-repeat: no-repeat;
  background-size: contain;

  /* background-color: rgba(226, 74, 74, 0.662); */
}
/* 기독교 */
.resultText3_mark2 {
  width: 80px;
  height: 80px;

  margin-left: 2px;
  background-image: url('../../assets/images/marks/Christian.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 불교 */
.resultText3_mark3 {
  width: 80px;
  height: 80px;

  background-image: url('../../assets/images/marks/Buddhism.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 천주교 */
.resultText3_mark4 {
  width: 100px;
  height: 100px;

  margin-left: 2px;
  background-image: url('../../assets/images/marks/Catholic.png');
  background-repeat: no-repeat;
  background-size: contain;
}

/*===위패 본관x===*/
/* 일반, 법명 */
.resultText3_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 60px;
  margin-top: 20px;
  letter-spacing:50px;

  /* background-color: rgba(251, 0, 0, 0.384); */
}

.resultText3_1-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 60px;
  margin-top: 5px;
  letter-spacing:25px;

  /* background-color: rgba(251, 0, 0, 0.384); */
}

/* 위 글자 (직분)*/
/* 이름1 */
.resultText3_2_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 50px;
  margin-top: 20px;
  letter-spacing:40px;

  /* background-color: rgba(8, 218, 127, 0.473); */
}
.resultText3_2_1-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 50px;
  margin-top: 8px;
  letter-spacing:20px;

  /* background-color: rgba(8, 218, 127, 0.473); */
}
/* 이름2 */
.resultText3_2_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 35px;
  margin-top: -8px;
  letter-spacing:-3px;

  writing-mode: horizontal-tb;
  /* background-color: rgba(246, 71, 2, 0.414); */
}
.resultText3_2_2-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 35px;
  margin-top: -8px;
  letter-spacing:-3px;

  writing-mode: horizontal-tb;
  /* background-color: rgba(246, 71, 2, 0.414); */
}

/* 아래 글자(세례명) */
/* 이름1 */
.resultText3_3_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 50px;
  margin-top: 10px;
  margin-bottom: -25px;
  letter-spacing:40px;

  /* background-color: rgb(70, 79, 10); */
}
.resultText3_3_1-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 50px;
  margin-top: 10px;
  margin-bottom: -10px;
  letter-spacing:15.5px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText3_3_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 35px;
  margin-bottom: 10px;
  letter-spacing:-3px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText3_3_2-4 {
  color: black;
  font-family: "HYGungSo";
  font-size: 35px;
  margin-bottom: 10px;
  letter-spacing:-3px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText3_3_2-5 {
  color: black;
  font-family: "HYGungSo";
  font-size: 33px;
  margin-bottom: 10px;
  letter-spacing:-7px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText3_3_2-6 {
  color: black;
  font-family: "HYGungSo";
  font-size: 30px;
  margin-bottom: 10px;
  letter-spacing:-9px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}

/*===위패 본관===*/
/*==마크==*/
/* 기독교 */
.resultText4_mark2 {
  width: 75px;
  height: 75px;

  margin-left: 2px;
  background-image: url('../../assets/images/marks/Christian.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 불교 */
.resultText4_mark3 {
  width: 60px;
  height: 60px;

  background-image: url('../../assets/images/marks/Buddhism.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 천주교 */
.resultText4_mark4 {
  width: 95px;
  height: 95px;

  margin-left: 2px;
  background-image: url('../../assets/images/marks/Catholic.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 일반 */
/* 본관 (5-9 글자) */
.resultText4_1_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  margin-top: 20px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */
  height: 100%; /* 부모 컨테이너의 높이에 맞추기 위해 */
  /* background-color: rgb(251, 0, 0); */
}
/* 글자 수에 따른 letter-spacing 설정 */
.resultText4_1_1[data-letter-count="5"] {
  letter-spacing: 35px; /* 5글자일 때의 설정 */
}
.resultText4_1_1[data-letter-count="6"] {
  letter-spacing: 28px; /* 6글자일 때의 설정 */
}
.resultText4_1_1[data-letter-count="7"] {
  letter-spacing: 17px; /* 7글자일 때의 설정 */
}
.resultText4_1_1[data-letter-count="8"] {
  margin-top: 10px;
  letter-spacing: 9px; /* 8글자일 때의 설정 */
}
.resultText4_1_1[data-letter-count="9"] {
  margin-top: 10px;
  letter-spacing: 4px; /* 9글자일 때의 설정 */
}

/* 불교 */
.resultText4_1_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  margin-top: 15px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */
  height: 100%; /* 부모 컨테이너의 높이에 맞추기 위해 */
  /* background-color: rgb(251, 0, 0); */
}
/* 글자 수에 따른 letter-spacing 설정 */
.resultText4_1_2[data-letter-count="5"] {
  letter-spacing: 30px; /* 5글자일 때의 설정 */
}
.resultText4_1_2[data-letter-count="6"] {
  letter-spacing: 18px; /* 6글자일 때의 설정 */
}
.resultText4_1_2[data-letter-count="7"] {
  margin-top: 5px;
  letter-spacing: 9px; /* 7글자일 때의 설정 */
}
.resultText4_1_2[data-letter-count="8"] {
  margin-top: 0px;
  letter-spacing: 4px; /* 8글자일 때의 설정 */
}
.resultText4_1_2[data-letter-count="9"] {
  margin-top: -5px;
  letter-spacing: -1px; /* 9글자일 때의 설정 */
}

/* 위 글자 (직분)*/
.resultText4_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100%;
  width: 100%;
  /* background-color: rgba(0, 225, 255, 0.408); */
}
/* 이름1 */
.resultText4_2_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 35px;
  margin-top: 5px;
  margin-bottom: -5px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */
  height: 100%; /* 부모 컨테이너의 높이에 맞추기 위해 */
  /* background-color: rgb(251, 0, 0); */
}
/* 글자 수에 따른 letter-spacing 설정 */
.resultText4_2_1[data-letter-count="5"] {
  letter-spacing: 15px; /* 5글자일 때의 설정 */
}
.resultText4_2_1[data-letter-count="6"] {
  letter-spacing: 7px; /* 6글자일 때의 설정 */
}
.resultText4_2_1[data-letter-count="7"] {
  margin-top: -5px;
  letter-spacing: 2px; /* 7글자일 때의 설정 */
}
.resultText4_2_1[data-letter-count="8"] {
  margin-top: -5px;
  letter-spacing: -2px; /* 8글자일 때의 설정 */
}
.resultText4_2_1[data-letter-count="9"] {
  font-size: 30px;
  margin-top: -5px;
  letter-spacing: -1px; /* 9글자일 때의 설정 */
}
/* 이름2 */
.resultText4_2_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 28px;
  margin-top: 5px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */

  writing-mode: horizontal-tb;
  /* background-color: rgba(255, 0, 238, 0.408); */
}
/* 아래 */
.resultText4_2_3 {
  color: black;
  font-family: "HYHaeSO";
  font-size: 28px;
  font-weight: 900;
  margin-bottom: 5px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */

  writing-mode: horizontal-tb;
  /* background-color: rgba(255, 0, 238, 0.408); */
}

/* 아래 글자(세례명) */
.resultText4_3 {
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100%;
  width: 100%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_3_1 {
  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  margin-top: 10px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */
  height: 100%; /* 부모 컨테이너의 높이에 맞추기 위해 */

  /* background-color: rgb(70, 79, 10); */
}
/* 글자 수에 따른 letter-spacing 설정 */
.resultText4_3_1[data-letter-count="5"] {
  margin-top: 15px;
  letter-spacing: 15px; /* 5글자일 때의 설정 */
}
.resultText4_3_1[data-letter-count="6"] {
  letter-spacing: 7px; /* 6글자일 때의 설정 */
}
.resultText4_3_1[data-letter-count="7"] {
  letter-spacing: 0px; /* 7글자일 때의 설정 */
}
.resultText4_3_1[data-letter-count="8"] {
  font-size: 35px;
  margin-top: 5px;
  letter-spacing: 0.5px; /* 8글자일 때의 설정 */
}
.resultText4_3_1[data-letter-count="9"] {
  font-size: 33px;
  margin-top: 3px;
  letter-spacing: -1px; /* 9글자일 때의 설정 */
}
/* 이름2 */
.resultText4_3_2 {
  color: black;
  font-family: "HYGungSo";
  font-size: 28px;
  margin-bottom: 7px;
  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText4_3_2-6 {
  color: black;
  font-family: "HYGungSo";
  font-size: 28px;
  margin-bottom: 3px;
  letter-spacing: -6px; /* 9글자일 때의 설정 */

  display: flex;
  flex-direction: column; /* 세로로 정렬하기 위해 열 방향으로 설정 */
  align-items: center; /* 텍스트를 수직 가운데 정렬 */
  justify-content: center; /* 텍스트를 수평 가운데 정렬 */

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/*======위패 끝======*/

/* 예시 밑에 - type */
.title6 {
  font-size: 20px;
  font-family: "BMEULJIROTTF";
}
</style>
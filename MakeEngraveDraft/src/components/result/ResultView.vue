<template>
  <div class="">
    <router-link :to="'/engrave/engraveCreate/engraveDetail?type='
           + type + '&selectedType=' + selectedType + '&showRouterView=true'" class="title4">
          👉 [이전 페이지]
    </router-link>
    <div class="container">
      <!-- 각인 -->
      <div class="engrave_container">
        <div class="title2">● 각인 예시 
          <div class="title6">&nbsp;&nbsp;- {{type}} 
            <span v-if="type !== selectedType"> [{{selectedType}}] </span>
          </div>
        </div>
        <div class="engrave_image_container" :class="{ 'fullscreen1': isFullscreen1 }" @click="toggleFullscreen1">
          <img class="engrave_image" v-if="capturedImage" :src="capturedImage" alt="Captured" />
        </div>
      </div>
      <!-- 위패 -->
      
    </div>
    <!-- 각인 -->
    <div v-if="imageContainerVisible" class="image-text-container" ref="imageContainer">
      <div class="text-container">
        <span class="resultText1">
          <span v-if="type == '일반' || type == '불교' || type == '묘법' || type == 'SGI'" class="resultText1_0">生</span>
          <span v-if="type == '기독교'" class="resultText1_0">出生</span>
          <span v-if="type == '천주교'" class="resultText1_0">出生</span>

          <span class="resultText1_1">{{date1_1.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date1_1.substr(1, 1)}}</span>
          <span class="resultText1_1">{{date1_1.substr(2, 1)}}</span>
          <span class="resultText1_1">{{date1_1.substr(3, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date1_2.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date1_2.substr(1, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date1_3.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date1_3.substr(1, 1)}}</span>
          <span v-if="date1Type==='음력'" class="resultText1_3">陰</span>
          <span v-if="date1Type==='양력'" class="resultText1_3">陽</span>
        </span>
        <!-- 그대로 -->
        <span class="resultText2_0" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교'
                                  || selectedType === '천주교'">
          <div :style="result2_mark"></div>
          <span class="resultText2_0_1" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_0_1-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <!-- 아래 글자 1 -->
        <span class="" v-if="selectedType === '형제'">
                    <div :style="result2_mark"></div>

          <span class="resultText2_1" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_1-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <!-- 위 글자 1 -->
        <span class="" v-if="selectedType === 'SGI'">
                    <div :style="result2_mark"></div>

          <span class="resultText2_2" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_2-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <!-- 위 아래 글자 -->
        <span class="" v-if="selectedType === '묘법'">
                    <div :style="result2_mark"></div>

          <span class="resultText2_3" v-if="name1.length === 2 || name1.length === 3">
            {{encodedName1}}
          </span>
          <span class="resultText2_3-4" v-if="name1.length === 4">
            {{encodedName1}}
          </span>
        </span>
        <!-- 아래 글자 2 -->
        <span class="resultText2_4" v-if="selectedType === '세례명'">
                    <div :style="result2_mark"></div>

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
        <!-- 위 글자 2-->
        <span class="resultText2_5" v-if="selectedType === '직분' || selectedType === '법명'">
                    <div :style="result2_mark"></div>

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
          <span v-if="type == '일반' || type == '불교' || type == '묘법' || type == 'SGI'" class="resultText1_0">卒</span>
          <span v-if="type == '기독교'" class="resultText1_0">召天</span>
          <span v-if="type == '천주교'" class="resultText1_0">終妙</span>

          <span class="resultText1_1">{{date2_1.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date2_1.substr(1, 1)}}</span>
          <span class="resultText1_1">{{date2_1.substr(2, 1)}}</span>
          <span class="resultText1_1">{{date2_1.substr(3, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date2_2.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date2_2.substr(1, 1)}}</span>
          <span class="resultText1_2">•</span>
          <span class="resultText1_1">{{date2_3.substr(0, 1)}}</span>
          <span class="resultText1_1">{{date2_3.substr(1, 1)}}</span>
          <span v-if="date2Type==='음력'" class="resultText1_3">陰</span>
          <span v-if="date2Type==='양력'" class="resultText1_3">陽</span>
        </span>
      </div>
    </div>
    <!-- 위패 -->
    <div class="image-text-container2" v-if="selectedType2 !== '없음'">
      <div class="title2">● 위패 예시
        <div class="title6">&nbsp;&nbsp;- {{type}} 
          <span v-if="name0 !== '없음'"> [본관] </span>
        </div>
      </div>
      <div class="image-text-container1_2">
        <div class="image-container2" :class="{ 'fullscreen2': isFullscreen2 }" @click="toggleFullscreen2">
          <div class="text-container2">
            <!-- <span class="resultText3"> -->
            <!-- 위패 추가 -->
            <div v-if="selectedType2 !== '없음'">
              <!-- 본관 선택 o -->
              <div v-if="name0 !== '없음'">
                <!-- 일반 -->
                <span class="resultText4_1_1" v-if="type === '일반'">
                  {{name0}}
                </span>
                <!-- 불교 -->
                <span class="resultText4_1_2" v-if="type === '불교'">
                  {{name0}}
                </span>
                <!-- 위 글자 -->
                <span class="resultText4_2" v-if="type === '기독교'">
                  <span class="resultText4_2_2">{{name2}}</span>
                  <span class="resultText4_2_1">{{name0}}</span>
                </span>
                <!-- 아래 글자 -->
                <span class="resultText4_3" v-if="type === '천주교'">
                  <span class="resultText4_3_1">{{name0}}</span>
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
              <!-- 본관 선택 x-->
              <div v-else>
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
                <span class="resultText3_2" v-if="type === '기독교'">
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
                <span class="resultText3_3" v-if="type === '천주교'">
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
            </div>
          </div>
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
import html2canvas from 'html2canvas';

export default {
  data() {
    return {
      type: this.$route.query.type,
      name0: this.$route.query.name0,
      name1: this.$route.query.name1,
      name2: this.$route.query.name2,
      date1: this.$route.query.date1,
      date1Type: this.$route.query.date1Type,
      date2: this.$route.query.date2,
      date2Type: this.$route.query.date2Type,
      selectedType: this.$route.query.selectedType, // 초기 선택 타입 설정
      selectedType2: this.$route.query.selectedType2, // 초기 선택 타입 설정

      date1_1: this.$route.query.date1.substr(0,4),
      date1_2: this.$route.query.date1.substr(5,2),
      date1_3: this.$route.query.date1.substr(8,2),
      date2_1: this.$route.query.date2.substr(0,4),
      date2_2: this.$route.query.date2.substr(5,2),
      date2_3: this.$route.query.date2.substr(8,2),

      isFullscreen1: false,
      isFullscreen2: false,

      capturedImage: null,
      imageContainerVisible: true,
    };
  },
  computed: {
    encodedName1() {
      const trimmedName1 = this.name1.trim();

      if(trimmedName1.length === 2){
        return trimmedName1.substr(0, 1) + " " + trimmedName1.substr(1, 1);
      }
      return trimmedName1;
    },
    result2_mark() {
      let markImageUrl = '';

      if (this.selectedType === '일반' || this.selectedType === '형제') {
        markImageUrl = `url('../../assets/images/marks/일반.png')`;
      } else if (this.selectedType === '기독교' || this.selectedType === '직분') {
        markImageUrl = `url('../../assets/images/marks/Christian.png')`;
      } else if (this.selectedType === '불교' || this.selectedType === '법명') {
        markImageUrl = `url('../../assets/images/marks/Buddhism.png')`;
      } else if (this.selectedType === '천주교' || this.selectedType === '세례명') {
        markImageUrl = `url('../../assets/images/marks/Catholic.png')`;
      }  else if (this.selectedType === 'SGI') {
        markImageUrl = `url('../../assets/images/marks/묘법(검정).png')`;
      }  else if (this.selectedType === '묘법') {
        markImageUrl = `url('../../assets/images/marks/묘법(검정).png')`;
      }

      return {
        backgroundImage: markImageUrl,
        /* 다른 스타일 속성도 여기에 추가 가능 */
        width: '60px',
        height: '60px',
        marginTop: '15px',
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'contain',

        backgroundColor: 'rgba(70, 79, 10, 0.475)',
      };
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
    toggleFullscreen1() {
      this.isFullscreen1 = !this.isFullscreen1;
      if (this.isFullscreen1) {
        this.zoomIn1();
      } else {
        this.zoomOut1();
      }
    },
    zoomIn1() {
      const imageContainer = document.querySelector('.engrave_image_container');
      imageContainer.style.transform = 'scale(2)'; // 예시로 확대 배율을 2배로 설정
    },
    zoomOut1() {
      const imageContainer = document.querySelector('.image-container1');
      imageContainer.style.transform = 'scale(1)'; // 원래 크기로 설정

      const textContainer = document.querySelector('.text-container');
      textContainer.style.transform = 'translate(0%, -107%)';
    },
    toggleFullscreen2() {
      this.isFullscreen2 = !this.isFullscreen2;
      if (this.isFullscreen2) {
        this.zoomIn2();
      } else {
        this.zoomOut2();
      }
    },
    zoomIn2() {
      const imageContainer = document.querySelector('.image-container2');
      imageContainer.style.transform = 'scale(2)'; // 예시로 확대 배율을 2배로 설정

      const textContainer = document.querySelector('.text-container2');
      textContainer.style.transform = 'translate(-100%, 12%)';
    },
    zoomOut2() {
      const imageContainer = document.querySelector('.image-container2');
      imageContainer.style.transform = 'scale(1)'; // 원래 크기로 설정

      const textContainer = document.querySelector('.text-container2');
      textContainer.style.transform = 'translate(0%, -100%)';
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
      const imageContainer = this.$refs.imageContainer;

      // 이미지 컨테이너 캡처
      const canvas = await html2canvas(imageContainer);
      const capturedImageDataUrl = canvas.toDataURL("image/png");

      this.capturedImage = capturedImageDataUrl;
      // this.imageContainerVisible = !this.imageContainerVisible; // 이미지 컨테이너를 숨김
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
.fullscreen1 {
  position: fixed;
  top: 0;
  left: 28vw;
  width: 100vw;
  height: 100vh;
  z-index: 1000;
  background-color: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
}

.fullscreen2 {
  position: fixed;
  top: 0;
  left: 20vw;
  width: 100vw;
  height: 100vh;
  z-index: 1000;
  background-color: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
}

/* ======================== */

.container {
  display: flex;
  /* justify-content: space-between; 가로로 나란히 정렬 */
  justify-content: center; /* 가운데 정렬 추가 */
  align-items: center;

  margin: 0 auto;
  max-width: 1200px; /* 원하는 최대 너비 설정 */

  /* text-align: center; */

  background-color: darkblue;
}

/*======각인 시작======*/
/* 캡처 이미지 출력 */
.engrave_container {
  position: relative;
  width: 40vw;
  /* height: 50vh; */
  background-repeat: no-repeat;
  background-position: center;
  margin-right: 10%;
  background-color: rgb(226, 245, 100);
}
.engrave_image {
  width: 40vw;
  height: auto;
}
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

.resultText1 {
  display: flex;
  justify-content: center;
  align-items: center;

  width: 33%;
  height: 100%;
  /* background-color: rgba(9, 255, 0, 0.551); */
}

.resultText1_0 {
  color: black;
  font-family: "HYHaeSo";
 
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

.resultText1_1 {
  color: black;
  font-family: "CENTURY";
  font-weight: 900;

  font-size: 18px;
  margin-top: -4px;

  width: 100%;
  /* height: 100%; */
  
  /* 가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(255, 157, 0, 0.551); */
}

.resultText1_2 {
  color: black;
  font-weight: bold;

  font-size: 15px;
  margin-top: -6px;
  margin-bottom: 0px;

  width: 100%;
  /* height: 100%; */

  display: flex;
  justify-content: center;
  align-items: center;

  /* background-color: rgba(0, 30, 255, 0.551); */
}

.resultText1_3 {
  color: black;
  font-family: "HYHaeSo";
  /* font-weight: 900; */
 
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

/* 이름 */
.resultText2_0 {
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 33%;
  height: 100%;
  /* background-color: rgba(226, 74, 74, 0.662); */
}

.resultText2_0_0 {
  width: 60px;
  height: 60px;

  margin-top: 15px;
  background-image: url('../../assets/images/marks/일반.png');
  background-repeat: no-repeat;
  background-size: contain;

  /* background-color: rgba(226, 74, 74, 0.662); */
}

.resultText2_0_1 {
  /* display: flex; */
  /* justify-content: center; */
  /* align-items: center; */

  color: black;
  font-family: "HYGungSo";
  font-weight: 500;
  font-size: 40px;
  letter-spacing:25px;

  /* background-color: rgba(145, 108, 108, 0.662); */
}

.resultText2_0_1-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";
  font-size: 40px;
  letter-spacing:5px;

  height: auto;
  width: 10px;
  /* background-color: rgba(145, 108, 108, 0.662); */
}
/* // 여기까지 */


/* 형제 */
.resultText2_1 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 8.4px;
  /* margin-top: -1px; */
  padding-bottom: 7.0px;
  letter-spacing:1.0px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.475); */
}
.resultText2_1-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 7.2px;
  padding-bottom: 8.0px;
  letter-spacing:-0.2px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.475); */
}
/* SGI */
.resultText2_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 8.4px;
  padding-top: 3.0px;
  letter-spacing:2.0px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgba(70, 79, 10, 0.481); */
}

.resultText2_2-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 7.9px;
  padding-top: 4.0px;
  letter-spacing:0.2px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}

/* 묘법 */
.resultText2_3 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 8.4px;
  padding-bottom: 3.0px;
  letter-spacing:0.7px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}

.resultText2_3-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 7.2px;
  padding-bottom: 3.0px;
  letter-spacing:-0.5px;

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(70, 79, 10); */
}
/* 아래 글자(세례명) */
.resultText2_4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  /* margin-top: -3%; */

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText2_4_1 {
  font-size: 8.4px;
  margin-top: 3px;
  letter-spacing:2.0px;

  /* background-color: rgb(70, 79, 10); */
}
.resultText2_4_1-4 {
  font-size: 7.5px;
  margin-top: 2px;
  padding-bottom: 1.5px;
  letter-spacing:0.5px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_4_2 {
  font-size: 4.5px;
  margin-top: -3px;
  letter-spacing:-0.5px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-4 {
  font-size: 4.2px;
  margin-top: -3px;
  letter-spacing:-0.8px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-5 {
  font-size: 3.8px;
  margin-top: -3px;
  letter-spacing:-0.8px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText2_4_2-6 {
  font-size: 3.5px;
  margin-top: -3px;
  letter-spacing:-1.1px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 위 글자 직분*/
.resultText2_5 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  /* margin-top: -3%; */

  height: 100%;
  width: 33.3333%;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText2_5_1 {
  font-size: 8.4px;
  /* margin-top: 3px; */
  letter-spacing:2.0px;

  /* background-color: rgb(70, 79, 10); */
}
.resultText2_5_1-4 {
  font-size: 7.9px;
  margin-top: -1px;
  letter-spacing:0.0px;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText2_5_2 {
  font-size: 4.5px;
  margin-top: -1px;
  letter-spacing:-0.5px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 이름2 */
.resultText2_5_2-4 {
  font-size: 4.2px;
  margin-top: -1px;
  letter-spacing:-0.5px;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/*======각인 끝======*/
/*======위패 시작======*/

/*======위패 끝======*/

.image-text-container2 {
  position: relative;
  width: 40vw;
  height: 50vh;
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(0, 255, 17); */
}
.image-text-container1_1 {
  display: flex;
  width: 400px;
  height: 675px;
  /* background-color: rgba(245, 100, 221, 0.537); */
}

.image-text-container1_2 {
  display: flex;
  /* background-color: rgba(100, 156, 245, 0.537); */
}


.image-container2 img {
  width: 20vw;
  height: auto;
}



.text-container2 {
  width: 20vw;
  height: 48vw;

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}



/* 위패 */
/* 일반 */
.resultText3_1 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 8vw;
  padding-top: 3vw;
  letter-spacing:7.0vw;

  height: auto;
  width: 20vw;

  /* background-color: rgba(251, 0, 0, 0.384); */
}

.resultText3_1-4 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";
  /* font-weight: 900; */

  font-size: 8vw;
  padding-top: 1vw;
  letter-spacing:3.5vw;

  height: auto;
  width: 20vw;

  /* background-color: rgba(251, 0, 0, 0.384); */
}
/* 위 글자 (직분)*/
.resultText3_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  /* margin-top: 19%; */

  height: auto;
  width: 20vw;
  /* background-color: rgba(221, 255, 0, 0.443); */
}
/* 이름1 */
.resultText3_2_1 {
  font-size: 7.2vw;
  margin-top: 2vw;
  letter-spacing:6.0vw;

  /* background-color: rgba(8, 218, 127, 0.473); */
}
.resultText3_2_1-4 {
  font-size: 7.2vw;
  margin-top: 0vw;
  margin-bottom: 2vw;
  letter-spacing:2.5vw;

  /* background-color: rgba(8, 218, 127, 0.473); */
}
/* 이름2 */
.resultText3_2_2 {
  font-size: 4.8vw;
  margin-top: 1vw;
  letter-spacing:-0.6vw;

  writing-mode: horizontal-tb;
  /* background-color: rgba(246, 71, 2, 0.414); */
}
.resultText3_2_2-4 {
  font-size: 4.8vw;
  margin-top: 1vw;
  letter-spacing:-0.6vw;

  writing-mode: horizontal-tb;
  /* background-color: rgba(246, 71, 2, 0.414); */
}
/* 아래 글자(세례명) */
.resultText3_3 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  /* margin-top: 19%; */

  height: auto;
  width: 20vw;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText3_3_1 {
  font-size: 7.6vw;
  margin-top: 0vw;
  letter-spacing:6.0vw;

  /* background-color: rgb(70, 79, 10); */
}
.resultText3_3_1-4 {
  font-size: 7.6vw;
  margin-bottom: 3vw;
  letter-spacing:2.0vw;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText3_3_2 {
  font-size: 5.2vw;
  margin-top: -6vw;
  letter-spacing:-0.6vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText3_3_2-4 {
  font-size: 5.2vw;
  margin-top: -3.5vw;
  letter-spacing:-1.0vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText3_3_2-5 {
  font-size: 4.5vw;
  margin-top: -3.5vw;
  letter-spacing:-1.0vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText3_3_2-6 {
  font-size: 4.0vw;
  margin-top: -3.0vw;
  letter-spacing:-1.2vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 위패 */
/* 본관 */
/* 일반 */
.resultText4_1_1 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 6.6vw;
  padding-bottom: 11vw;
  letter-spacing:0.2vw;
  
  height: auto;
  width: 20vw;
  /* background-color: rgb(251, 0, 0); */
}
/* 불교 */
.resultText4_1_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  font-size: 6.6vw;
  margin-top: -5vw;
  letter-spacing:-0.8vw;
  
  height: auto;
  width: 20vw;
  /* background-color: rgb(251, 0, 0); */
}
/* 위 글자 (직분)*/
.resultText4_2 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  margin-top: -18%;

  height: auto;
  width: 20vw;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_2_1 {
  font-size: 5.8vw;
  margin-top: -1.0vw;
  letter-spacing:0.1vw;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText4_2_2 {
  font-size: 4.2vw;
  margin-top: -1vw;
  letter-spacing:-0.05vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
/* 아래 글자(세례명) */
.resultText4_3 {
  display: flex;
  justify-content: center;
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  /* margin-top: 0%; */

  height: auto;
  width: 20vw;
  /* background-color: rgb(221, 255, 0); */
}
/* 이름1 */
.resultText4_3_1 {
  font-size: 5.8vw;
  margin-top: 0vw;
  letter-spacing:0.8vw;

  /* background-color: rgb(70, 79, 10); */
}
/* 이름2 */
.resultText4_3_2 {
  font-size: 4.2vw;
  margin-top: -1vw;
  letter-spacing:-0.5vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}
.resultText4_3_2-6 {
  font-size: 3.8vw;
  margin-top: -1vw;
  letter-spacing:-1.0vw;

  writing-mode: horizontal-tb;
  /* background-color: rgb(246, 71, 2); */
}

.title6 {
  font-size: 15px;
  font-family: "BMEULJIROTTF";
}

.title7 {
  font-size: 13px;
  font-weight: bold;
  color: rgb(111, 103, 255);
  font-family: "BMEULJIROTTF";
  cursor: pointer;
}

.title8 {
  font-size: 20px;
  font-family: "BMEULJIROTTF";
  color: rgb(255, 149, 0);

  text-align: center;
}
</style>
<template>
  <div class="">
    <router-link :to="'/engrave/engraveCreate/engraveDetail?type='
           + type + '&selectedType=' + selectedType + '&showRouterView=true'" class="title4">
          👉 [이전 페이지]
    </router-link>
    <div class="container" id="capture-element">
      <!-- 각인 -->
      <div class="image-text-container">
        <div class="title2">● 각인 예시 
          <div class="title6">&nbsp;&nbsp;- {{type}} 
            <span v-if="type !== selectedType"> [{{selectedType}}] </span>
          </div>
        </div>
        <div class="image-text-container1_1">
          <div class="image-container1">
            <div v-if="type === '일반' && selectedType === '일반'">
              <img src="../../assets/images/engrave/background/일반(공).png" height="100%" alt="일반">
            </div>
            <div v-if="type === '일반' && selectedType === '형제'">
              <img src="../../assets/images/engrave/background/일반형제(공).png" height="100%" alt="형제">
            </div>
            <div v-if="type === '기독교'">
              <img src="../../assets/images/engrave/background/기독교(공).png" height="100%" alt="기독교">
            </div>
            <div v-if="type === '불교'">
              <img src="../../assets/images/engrave/background/불교(공).png" height="100%" alt="불교">
            </div>
            <div v-if="type === '천주교'">
              <img src="../../assets/images/engrave/background/천주교(공).png" height="100%" alt="천주교">
            </div>
            <div v-if="type === 'SGI'">
              <img src="../../assets/images/engrave/background/sgi(공).png" height="100%" alt="SGI">
            </div>
            <div v-if="type === '묘법'">
              <img src="../../assets/images/engrave/background/묘법(공).png" height="100%" alt="묘법">
            </div>
          </div>
          <div class="text-container">
            <span class="resultText1">
              <span class="resultText1_1">{{date1_1}}</span>
              <span class="resultText1_2">•</span>
              <span class="resultText1_1">{{date1_2}}</span>
              <span class="resultText1_2">•</span>
              <span class="resultText1_1">{{date1_3}}</span>
              <span v-if="date1Type==='음력'" class="resultText1_3">陰</span>
              <span v-if="date1Type==='양력'" class="resultText1_3">陽</span>
            </span>
            <!-- <div v-if="selectedType === '일반'"> -->
            <!-- 그대로 -->
            <span class="" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교'
                                      || selectedType === '천주교'">
              <span class="resultText2" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 아래 글자 1 -->
            <span class="" v-if="selectedType === '형제'">
              <span class="resultText2_1" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_1-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 위 글자 1 -->
            <span class="" v-if="selectedType === 'SGI'">
              <span class="resultText2_2" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_2-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 위 아래 글자 -->
            <span class="" v-if="selectedType === '묘법'">
              <span class="resultText2_3" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText2_3-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 아래 글자 2 -->
            <span class="resultText2_4" v-if="selectedType === '세례명'">
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
            <!-- </div> -->
            <span class="resultText1">
              <span class="resultText1_1">{{date2_1}}</span>
              <span class="resultText1_2">•</span>
              <span class="resultText1_1">{{date2_2}}</span>
              <span class="resultText1_2">•</span>
              <span class="resultText1_1">{{date2_3}}</span>
              <span v-if="date2Type==='음력'" class="resultText1_3">陰</span>
              <span v-if="date2Type==='양력'" class="resultText1_3">陽</span>
            </span>
          </div>
        </div>
        <!-- 본관에 따른 데이터 전송값 변경 -->
          <span v-if="name0 !== '없음'">
            <router-link :to="'/engrave/resultEngrave?' + 'type=' + type 
                        + '&name0=' + name0 + '&name1=' + name1 + '&name2='+ name2 
                        + '&date1=' + date1 + '&date1Type=' + date1Type 
                        + '&date2=' + date2 + '&date2Type=' + date2Type
                        + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;🔎 크게보기</router-link>
          </span>
          <span v-else>
            <router-link :to="'/engrave/resultEngrave?' + 'type=' + type 
                        + '&name0=' + '없음' + '&name1=' + name1 + '&name2='+ name2 
                        + '&date1=' + date1 + '&date1Type=' + date1Type 
                        + '&date2=' + date2 + '&date2Type=' + date2Type
                        + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;🔎 크게보기</router-link>
          </span>
      </div>
      <!-- 위패 -->
      <div class="image-text-container2" v-if="selectedType2 !== '없음'">
        <div class="title2">● 위패 예시
          <div class="title6">&nbsp;&nbsp;- {{type}} 
            <span v-if="name0 !== '없음'"> [본관] </span>
          </div>
        </div>
        <div class="image-text-container1_1">
          <div class="image-container2">
            <div v-if="name0 !== '없음'">
              <div v-if="type === '일반'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/일반(본관출력시안).png" height="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/기독교(본관출력시안).png" height="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/불교(본관출력시안).png" height="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../assets/images/memorialTablet/background/본관출력시안/천주교(본관출력시안).png" height="100%" alt="천주교">
              </div>
            </div>
            <div v-else>
              <div v-if="type === '일반'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/일반(이름출력시안).png" height="100%" alt="일반">
              </div>
              <div v-if="type === '기독교'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/기독교(이름출력시안).png" height="100%" alt="기독교">
              </div>
              <div v-if="type === '불교'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/불교(이름출력시안).png" height="100%" alt="불교">
              </div>
              <div v-if="type === '천주교'">
                <img src="../../assets/images/memorialTablet/background/이름출력시안/천주교(이름출력시안).png" height="100%" alt="천주교">
              </div>
            </div>
          </div>
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
                  <span class="resultText4_3_2">{{name2}}</span>
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
        <!-- 본관에 따른 데이터 전송값 변경 -->
          <router-link :to="'/engrave/resultTablet?' + 'type=' + type 
                      + '&name0=' + name0 + '&name1=' + name1 + '&name2='+ name2 
                      + '&date1=' + date1 + '&date1Type=' + date1Type 
                      + '&date2=' + date2 + '&date2Type=' + date2Type
                      + '&selectedType=' + selectedType + '&selectedType2=' + selectedType2" class="title7">&nbsp;&nbsp;&nbsp;🔎 크게보기</router-link>
      </div>
      <br>
    </div>

    <br>
    <br>
    <br>
    <br>
    <br>
    <!-- <a v-if="checkMobile() === 'android'" class="title8" href="sms:01045097485?body=보내고 싶은 메시지%0adddㅇㅇㅇ">안드로이드 문자보내기</a> -->
    <!-- <a v-if="checkMobile() === 'ios'" class="title8" href="sms:01045097485&body=보내고 싶은 메시지%0adddㅇㅇㅇ">iOS8 미만 문자보내기</a> -->
    <!-- <div v-if="checkMobile() === 'other'" class="title4" href="sms:01045097485;body=보내고 싶은 메시지">iOS8 이상 문자보내기</div> -->
    <a v-if="isIOS" class="title8" :href="iosSMSEntry">아이폰 SMS 보내기</a>
    <a v-if="isAndroid" class="title8" :href="androidSMSEntry">안드로이드 SMS 보내기</a><br>
    <a v-if="isAndroid" class="title8" :href="androidMMSEntry">안드로이드 MMS 보내기</a>
    <a v-if="isUnknown" class="title8">호환되지 않는 기종입니다.</a>

    <br>
    <button @click="captureAndSendMMS">MMS 보내기</button>
    <br>
    <button @click="captureAndDisplayImage">캡처 및 이미지 표시</button>
    <br>
    <img :src="capturedImageData" alt="캡처된 이미지">
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

      capturedImageData: '',
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
    isIOS() {
      return this.checkMobile() === 'ios';
    },
    iosSMSEntry() {
      const phoneNumber = '01045097485';
      const message = encodeURIComponent('보내고 싶은 메시지\n새로운 줄');
      return `sms:${phoneNumber}&body=${message}`;
    },
    isAndroid() {
      return this.checkMobile() === 'android';
    },
    androidSMSEntry() {
      const phoneNumber = '01045097485';
      const message = encodeURIComponent('보내고 싶은 메시지\n아에이오우');
      return `sms:${phoneNumber}?body=${message}`;
    },
    androidMMSEntry() {
      const phoneNumber = '01045097485';
      const message = encodeURIComponent('보내고 싶은 메시지\n새로운 줄');
      const imageData = this.capturedImageData;
      const mmsURL = `content://com.android.mms.MessageBuilder`;
      return `${mmsURL}?recipient=${phoneNumber}&attachment=${imageData}&subject=${message}`;
    },
    isUnknown() {
      return this.checkMobile() === 'unknown';
    },
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
    async captureAndSendMMS() {
      const phoneNumber = '01045097485';
      const message = encodeURIComponent('보내고 싶은 메시지');
      const mmsWithImageURL = `${mmsURL}?recipient=${phoneNumber}&attachment=${imageData}&subject=${message}`;

      // 이미지 데이터는 이미 capturedImageData에 저장되어 있으므로 그대로 사용
      const imageData = this.capturedImageData;

      // Android Intent URI를 생성하여 MMS 앱을 엽니다.
      const intentURI = `sms:${phoneNumber}?body=${message}&attachment=${imageData}`;

      // 생성된 URI로 Android Intent를 호출합니다.
      window.location.href = intentURI;
    },
    async captureAndDisplayImage() {
      const captureElement = document.getElementById('capture-element');
      const canvas = await html2canvas(captureElement);
      this.capturedImageData = canvas.toDataURL('image/jpeg');
    },
  },
};
</script>

<style>

</style>
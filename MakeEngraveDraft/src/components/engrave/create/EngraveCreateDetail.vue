<template>
  <div class="">
    <div class="app">
      <div class="title2">
        ● [{{engraveType}}] 각인 종류 <br>
      </div>
      <div class="text-align-center">
        <div class="scroll-container">
          <span class="link-container">
            <!-- 일반 -->
            <div v-if="engraveType === '일반'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '일반' }"
                @click="updateSelectType('일반')"
              >
                <img src="../../../assets/images/engrave/example/일반.png" width="80" height="130" alt="일반">
                <span class="selectText">[기본]</span>
              </div>
              <div
                class="link-item"
                :class="{ selected: selectedType === '형제' }"
                @click="updateSelectType('형제')"
              >
                <img src="../../../assets/images/engrave/example/일반(형제).png" width="80" height="130" alt="형제">
                <span class="selectText">[형제]</span>
              </div>
            </div>
            <!-- 기독교 -->
            <div v-if="engraveType === '기독교'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '직분' }"
                @click="updateSelectType('직분')"
              >
                <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="기독교(직분)">
                <span class="selectText">[기본]</span>
              </div>
              <div
                class="link-item"
                :class="{ selected: selectedType === '기독교' }"
                @click="updateSelectType('기독교')"
              >
                <img src="../../../assets/images/engrave/example/기독교(직분x).png" width="80" height="130" alt="기독교(직분x)">
                <span class="selectText">[직분X]</span>
              </div>
            </div>
            <!-- 불교 -->
            <div v-if="engraveType === '불교'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '불교' }"
                @click="updateSelectType('불교')"
              >
                <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교">
                <span class="selectText">[기본]</span>
              </div>
              <div
                class="link-item"
                :class="{ selected: selectedType === '법명' }"
                @click="updateSelectType('법명')"
              >
                <img src="../../../assets/images/engrave/example/불교(법명).png" width="80" height="130" alt="불교(법명)">
                <span class="selectText">[법명]</span>
              </div> 
              <!-- <div
                class="link-item"
                :class="{ selected: selectedType === '불교[검정]' }"
                @click="updateSelectType('불교[검정]')"
              >
                <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교[검정]">
                <span class="selectText">[기본[검정]]</span>
              </div> -->
              <!-- <div
                class="link-item"
                :class="{ selected: selectedType === '법명[검정]' }"
                @click="updateSelectType('법명[검정]')"
              >
                <img src="../../../assets/images/engrave/example/불교(법명).png" width="80" height="130" alt="불교(법명)[검정]">
                <span class="selectText">[법명[검정]]</span>
              </div>  -->
            </div>
            <!-- 천주교 -->
            <div v-if="engraveType === '천주교'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '세례명' }"
                @click="updateSelectType('세례명')"
              >
                <img src="../../../assets/images/engrave/example/천주교.png" width="80" height="130" alt="천주교(세례명)">
                <span class="selectText">[기본]</span>
              </div>
              <div
                class="link-item"
                :class="{ selected: selectedType === '천주교' }"
                @click="updateSelectType('천주교')"
              >
                <img src="../../../assets/images/engrave/example/천주교(세례명x).png" width="80" height="130" alt="천주교)">
                <span class="selectText">[세례명X]</span>
              </div>
            </div>
            <!-- SGI -->
            <div v-if="engraveType === 'SGI'">
              <div
                class="link-item"
                :class="{ selected: selectedType === 'SGI' }"
                @click="updateSelectType('SGI')"
              >
                <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI">
                <span class="selectText">[기본]</span>
              </div>
              <div
                class="link-item"
                :class="{ selected: selectedType === 'SGI(금색)' }"
                @click="updateSelectType('SGI(금색)')"
              >
                <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI(금색)">
                <span class="selectText">[금색]</span>
              </div>
            </div>
            <!-- 묘법 -->
            <div v-if="engraveType === '묘법'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '묘법' }"
                @click="updateSelectType('묘법')"
              >
                <img src="../../../assets/images/engrave/example/묘법.png" width="80" height="130" alt="묘법">
                <span class="selectText">[기본]</span>
              </div>
              <div
                class="link-item"
                :class="{ selected: selectedType === '묘법(금색)' }"
                @click="updateSelectType('묘법(금색)')"
              >
                <img src="../../../assets/images/engrave/example/묘법.png" width="80" height="130" alt="묘법(금색)">
                <span class="selectText">[금색]</span>
              </div>
            </div>
            <!-- 순복음 -->
            <div v-if="engraveType === '순복음'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '순복음' }"
                @click="updateSelectType('순복음')"
              >
                <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="묘법">
                <span class="selectText">[기본]</span>
              </div>
            </div>
            <!-- 원불교 -->
            <div v-if="engraveType === '원불교'">
              <div
                class="link-item"
                :class="{ selected: selectedType === '원불교' }"
                @click="updateSelectType('원불교')"
              >
                <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="묘법">
                <span class="selectText">[기본]</span>
              </div>
            </div>
          </span>
        </div>
      </div>
    </div>
    <hr>
    <!-- 정보입력 -->
    <div class="app">
      <div class="text-align-center">
        <span class="info-text-align-center">
          <div class="title3">
            정보 입력
          </div>
          <div>
            고인명<br>
            <input v-model="name1" type="text" placeholder="홍길동" style="height: 30px; width: 100%;"/>
            <div v-if="showName1KoreanWarning" class="warning_text">
              - 고인명을 한국어로 올바르게 입력해주세요.
            </div>
            <div v-else-if="showName1Warning" class="warning_text">
              - 고인명을 2~4글자로 입력해주세요.
            </div>
          </div>
          <!-- 날짜 입력 -->
          <div>
            생년월일 (숫자만 입력)<br>
            <input v-model="date1" type="text" :placeholder="Date1Placeholder" @input="handleDateInput($event, 'date1')" style="height: 30px; width: 79%; margin-right: 1%;">
            <!-- 날짜 입력 -->
            <select name="date1Type" v-model="date1Type" style="height: 30px; width: 20%;">
              <option value="양력" selected="selected">양력</option>
              <option value="음력">음력</option>
            </select>
            <div v-if="showDate1Warning" class="warning_text">
              - 생년월일을 {{ getDateWarningMessage(date1) }}
            </div>
          </div>
          <div>
            사망월일 (숫자만 입력)<br>
            <input v-model="date2" type="text" :placeholder="todayDate2Placeholder" @input="handleDateInput($event, 'date2')" style="height: 30px; width: 79%; margin-right: 1%">
            <select name="date2Type" v-model="date2Type" style="height: 30px; width: 20%;">
              <option value="양력" selected="selected">양력</option>
              <option value="음력">음력</option>
            </select>
            <div v-if="showDate2Warning" class="warning_text">
              - 사망월일을 {{ getDateWarningMessage(date2) }}
            </div>
          </div>
          <div v-if="selectedType === '직분' || selectedType === '순복음'">
            직분
          </div>
          <div v-if="selectedType === '법명' || selectedType === '법명[검정]'">
            법명
          </div>
          <div v-if="selectedType === '세례명'">
            세례명
          </div>
          <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명' || selectedType === '법명[검정]' || selectedType === '순복음'">
            <input v-model="name2" type="text" :placeholder="defaultName2Placeholder" style="height: 30px; width: 100%;"/>
            <div v-if="showName2KoreanWarning" class="warning_text">
              - {{selectedType}}을 한국어로 올바르게 입력해주세요.
            </div>
            <div v-else-if="showName2Warning && (selectedType === '직분' || selectedType === '법명'|| selectedType === '법명[검정]' || selectedType === '순복음')" class="warning_text">
              - {{selectedType}}을 2~4글자로 입력해주세요.
            </div>
            <div v-else-if="showName2Warning2 && (selectedType === '세례명')" class="warning_text">
              - {{selectedType}}을 2~6글자로 입력해주세요.
            </div>
          </div>
          <div v-if="engraveType !== '일반' && engraveType !== 'SGI' && engraveType !== '묘법' && engraveType !== '순복음'">
            종교
            <input v-model="religion" type="text" :placeholder="defaultReligionPlaceholder" style="height: 30px; width: 100%;"/>
            <div v-if="showReligionKoreanWarning"  class="warning_text">
              - 종교를 한국어로 올바르게 입력해주세요.
            </div>
          </div>
          <div>
            유골함 종류<br>
            <select :value="selectedUrnType" @change="updateSelectedUrnType" style="height: 30px; width: 100%;">
              <option value="">유골함 선택</option>
              <!-- <option value="" disabled>유골함 선택</option> -->
              <optgroup label="유골함">
                <option v-for="urn in urnTypes" :value="urn" :key="urn">
                  {{ urn }}
                </option>
              </optgroup>
              <optgroup label="합골함">
                <option v-for="bone in boneTypes" :value="bone" :key="bone">
                  {{ bone }}
                </option>
              </optgroup>
            </select>
            <!--=====합골 추가 내용=====-->
            <div v-if="selectedUrnType.startsWith('합골')">
              <br>
              <hr>
              <div :class="boneClass">
                <div class="title3">
                  합골 추가 내용 입력
                </div>
                <!-- 각인 -->
                <div class="title2">
                  ● 유골함 각인 종류
                </div>
                <!-- 가로 스크롤 가능한 컨테이너 -->
                <div class="text-align-center">
                  <div class="scroll-container">
                    <div class="link-container">
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '일반' }"
                          @click="boneEngraveType = '일반', boneSelectedType = '일반'">
                        <img src="../../../assets/images/engrave/example/일반.png" alt="일반">
                        <span class="selectText">[일반]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '기독교' }"
                          @click="boneEngraveType = '기독교', boneSelectedType = '직분'">
                        <img src="../../../assets/images/engrave/example/기독교.png" alt="기독교">
                        <span class="selectText">[기독교]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '불교' }"
                          @click="boneEngraveType = '불교', boneSelectedType = '불교'">
                        <img src="../../../assets/images/engrave/example/불교.png" alt="불교">
                        <span class="selectText">[불교]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '천주교' }"
                          @click="boneEngraveType = '천주교', boneSelectedType = '세례명'">
                        <img src="../../../assets/images/engrave/example/천주교.png" alt="천주교">
                        <span class="selectText">[천주교]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === 'SGI' }"
                          @click="boneEngraveType = 'SGI', boneSelectedType = 'SGI'">
                        <img src="../../../assets/images/engrave/example/sgi.png" alt="SGI">
                        <span class="selectText">[SGI]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '묘법' }"
                          @click="boneEngraveType = '묘법', boneSelectedType = '묘법'">
                        <img src="../../../assets/images/engrave/example/묘법.png" alt="묘법">
                        <span class="selectText">[묘법]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '순복음' }"
                          @click="boneEngraveType = '순복음', boneSelectedType = '순복음'">
                        <img src="../../../assets/images/engrave/example/기독교.png" alt="순복음">
                        <span class="selectText">[순복음]</span>
                      </div>
                      <div class="link-item"
                          :class="{ selected: boneEngraveType === '원불교' }"
                          @click="boneEngraveType = '원불교', boneSelectedType = '원불교'">
                        <img src="../../../assets/images/engrave/example/불교.png" alt="원불교">
                        <span class="selectText">[원불교]</span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="text-align-center">
                  <div class="scroll-container">
                    <span class="link-container">
                      <!-- 일반 -->
                      <div v-if="boneEngraveType === '일반'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '일반' }"
                          @click="updateBoneSelectType('일반')"
                        >
                          <img src="../../../assets/images/engrave/example/일반.png" width="80" height="130" alt="일반">
                          <span class="selectText">[기본]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '형제' }"
                          @click="updateBoneSelectType('형제')"
                        >
                          <img src="../../../assets/images/engrave/example/일반(형제).png" width="80" height="130" alt="형제">
                          <span class="selectText">[형제]</span>
                        </div>
                      </div>
                      <!-- 기독교 -->
                      <div v-if="boneEngraveType === '기독교'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '직분' }"
                          @click="updateBoneSelectType('직분')"
                        >
                          <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="기독교(직분)">
                          <span class="selectText">[기본]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '기독교' }"
                          @click="updateBoneSelectType('기독교')"
                        >
                          <img src="../../../assets/images/engrave/example/기독교(직분x).png" width="80" height="130" alt="기독교(직분x)">
                          <span class="selectText">[직분X]</span>
                        </div>
                      </div>
                      <!-- 불교 -->
                      <div v-if="boneEngraveType === '불교'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '불교' }"
                          @click="updateBoneSelectType('불교')"
                        >
                          <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교">
                          <span class="selectText">[기본]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '법명' }"
                          @click="updateBoneSelectType('법명')"
                        >
                          <img src="../../../assets/images/engrave/example/불교(법명).png" width="80" height="130" alt="불교(법명)">
                          <span class="selectText">[법명]</span>
                        </div> 
                        <!-- <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '불교[검정]' }"
                          @click="updateBoneSelectType('불교[검정]')"
                        >
                          <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교[검정]">
                          <span class="selectText">[기본[검정]]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '법명[검정]' }"
                          @click="updateBoneSelectType('법명[검정]')"
                        >
                          <img src="../../../assets/images/engrave/example/불교(법명).png" width="80" height="130" alt="불교(법명[검정]">
                          <span class="selectText">[법명[검정]]</span>
                        </div>  -->
                      </div>
                      <!-- 천주교 -->
                      <div v-if="boneEngraveType === '천주교'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '세례명' }"
                          @click="updateBoneSelectType('세례명')"
                        >
                          <img src="../../../assets/images/engrave/example/천주교.png" width="80" height="130" alt="천주교(세례명)">
                          <span class="selectText">[기본]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '천주교' }"
                          @click="updateBoneSelectType('천주교')"
                        >
                          <img src="../../../assets/images/engrave/example/천주교(세례명x).png" width="80" height="130" alt="천주교)">
                          <span class="selectText">[세례명X]</span>
                        </div>
                      </div>
                      <!-- SGI -->
                      <div v-if="boneEngraveType === 'SGI'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === 'SGI' }"
                          @click="updateBoneSelectType('SGI')"
                        >
                          <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI">
                          <span class="selectText">[기본]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === 'SGI(금색)' }"
                          @click="updateBoneSelectType('SGI(금색)')"
                        >
                          <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI(금색)">
                          <span class="selectText">[금색]</span>
                        </div>
                      </div>
                      <!-- 묘법 -->
                      <div v-if="boneEngraveType === '묘법'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '묘법' }"
                          @click="updateBoneSelectType('묘법')"
                        >
                          <img src="../../../assets/images/engrave/example/묘법.png" width="80" height="130" alt="묘법">
                          <span class="selectText">[기본]</span>
                        </div>
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '묘법(금색)' }"
                          @click="updateBoneSelectType('묘법(금색)')"
                        >
                          <img src="../../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="묘법(금색)">
                          <span class="selectText">[금색]</span>
                        </div>
                      </div>
                      <!-- 순복음 -->
                      <div v-if="boneEngraveType === '순복음'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '순복음' }"
                          @click="updateBoneSelectType('순복음')"
                        >
                          <img src="../../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="묘법">
                          <span class="selectText">[기본]</span>
                        </div>
                      </div>
                      <!-- 원불교 -->
                      <div v-if="boneEngraveType === '원불교'">
                        <div
                          class="link-item"
                          :class="{ selected: boneSelectedType === '원불교' }"
                          @click="updateBoneSelectType('원불교')"
                        >
                          <img src="../../../assets/images/engrave/example/불교.png" width="80" height="130" alt="묘법">
                          <span class="selectText">[기본]</span>
                        </div>
                      </div>
                    </span>
                  </div>
                </div>
                <!-- 성별 -->
                <div>
                  성별
                  <select v-model="boneSex" style="height: 30px; width: 100%;">
                    <option value="남성" selected>남성</option>
                    <option value="여성">여성</option>
                  </select>
                </div>
                <!-- 이름 -->
                <div>
                  고인명<br>
                  <input v-model="boneName1" type="text" placeholder="홍길동" style="height: 30px; width: 100%;"/>
                  <div v-if="showBoneName1KoreanWarning" class="warning_text">
                    - 고인명을 한국어로 올바르게 입력해주세요.
                  </div>
                  <div v-else-if="showBoneName1Warning" class="warning_text">
                    - 고인명을 2~4글자로 입력해주세요.
                  </div>
                </div>
                <!-- 날짜 입력 -->
                <div>
                  생년월일 (숫자만 입력)<br>
                  <input v-model="boneDate1" type="text" :placeholder="Date1Placeholder" @input="handleDateInput($event, 'boneDate1')" style="height: 30px; width: 79%; margin-right: 1%;">
                  <!-- 날짜 입력 -->
                  <select name="boneDate1Type" v-model="boneDate1Type" style="height: 30px; width: 20%;">
                    <option value="양력" selected="selected">양력</option>
                    <option value="음력">음력</option>
                  </select>
                  <div v-if="showBoneDate1Warning" class="warning_text">
                    - 생년월일을 {{ getDateWarningMessage(boneDate1) }}
                  </div>
                </div>
                <div>
                  사망월일 (숫자만 입력)<br>
                  <input v-model="boneDate2" type="text" :placeholder="todayDate2Placeholder" @input="handleDateInput($event, 'boneDate2')" style="height: 30px; width: 79%; margin-right: 1%">
                  <select name="boneDate2Type" v-model="boneDate2Type" style="height: 30px; width: 20%;">
                    <option value="양력" selected="selected">양력</option>
                    <option value="음력">음력</option>
                  </select>
                  <div v-if="showBoneDate2Warning" class="warning_text">
                    - 사망월일을 {{ getDateWarningMessage(boneDate2) }}
                  </div>
                </div>
                <div v-if="boneEngraveType !== '일반' && boneEngraveType !== 'SGI' && boneEngraveType !== '묘법' && boneEngraveType !== '순복음'">
                  종교
                  <input v-model="boneReligion" type="text" :placeholder="defaultReligionPlaceholder" style="height: 30px; width: 100%;"/>
                  <div v-if="showBoneReligionKoreanWarning"  class="warning_text">
                    - 종교를 한국어로 올바르게 입력해주세요.
                  </div>
                </div>
                <div v-if="boneSelectedType === '직분' || boneSelectedType === '순복음'">
                  직분
                </div>
                <div v-if="boneSelectedType === '법명'|| boneSelectedType === '법명[검정]'">
                  법명
                </div>
                <div v-if="boneSelectedType === '세례명'">
                  세례명
                </div>
                <div v-if="boneSelectedType === '직분' || boneSelectedType === '법명' || boneSelectedType === '세례명'|| boneSelectedType === '법명[검정]' || boneSelectedType === '순복음'">
                  <input v-model="boneName2" type="text" :placeholder="defaultName2Placeholder" style="height: 30px; width: 100%;"/>
                  <div v-if="showBoneName2KoreanWarning" class="warning_text">
                    - {{selectedType}}을 한국어로 올바르게 입력해주세요.
                  </div>
                  <div v-else-if="showBoneName2Warning && (selectedType === '직분' || selectedType === '법명'|| boneSelectedType === '법명[검정]' || boneSelectedType === '순복음')" class="warning_text">
                    - {{selectedType}}을 2~4글자로 입력해주세요.
                  </div>
                  <div v-else-if="showBoneName2Warning2 && (selectedType === '세례명')" class="warning_text">
                    - {{selectedType}}을 2~6글자로 입력해주세요.
                  </div>
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
    <div v-if="showRouterView" class="app">
      <router-link :to="{name: 'tabletCreateView'}" @click.native="updateRouteData()" class="title4">
        👉 위패 주문하기
        <span class="title4_1">
          (Click!!)
        </span>
      </router-link>
      <br>
      <br>
      <div class="title8">
        ● 특이사항 (40자 이내)<br>
        <input v-model="note" type="text" placeholder="특이사항을 적어주세요." style="height: 30px; width: 100%;"/>
        <div v-if="showNoteWarning" class="warning_text">
          - 특이사항을 40글자 이하로 입력해주세요.
        </div>
      </div>
      <br>
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
      'getEngraveType',
      'getSelectedType',
      'getShowRouterView',

      'getName1',
      'getName2',
      'getDate1',
      'getDate1Type',
      'getDate2',
      'getDate2Type',
      'getReligion',
      'getSelectedUrnType',

      'getBoneEngraveType',
      'getBoneSelectedType',
      'getBoneSex',
      'getBoneName1',
      'getBoneName2',
      'getBoneDate1',
      'getBoneDate1Type',
      'getBoneDate2',
      'getBoneDate2Type',
      'getBoneReligion',
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
    religion: {
      get() {
        return this.$store.getters.getReligion;
      },
      set(value) {
        this.$store.commit('updateReligion', value);
      }
    },
    urnTypes() {
      return ['유골함1', '유골함2', '유골함3'];
    },
    boneTypes() {
      return ['합골함1', '합골함2', '합골함3'];
    },
    selectedUrnType: {
      get() {
        return this.$store.getters.getSelectedUrnType;
      },
      set(value) {
        this.$store.commit('updateSelectedUrnType', value);
      }
    },
    boneEngraveType: {
      get() {
        return this.$store.getters.getBoneEngraveType;
      },
      set(value) {
        this.$store.commit('updateBoneEngraveType', value);
      }
    },
    boneSelectedType: {
      get() {
        return this.$store.getters.getBoneSelectedType;
      },
      set(value) {
        this.$store.commit('updateBoneSelectedType', value);
      }
    },
    boneSex: {
      get() {
        return this.$store.getters.getBoneSex;
      },
      set(value) {
        this.$store.commit('updateBoneSex', value);
      }
    },
    boneName1: {
      get() {
        return this.$store.getters.getBoneName1;
      },
      set(value) {
        this.$store.commit('updateBoneName1', value);
      }
    },
    boneName2: {
      get() {
        return this.$store.getters.getBoneName2;
      },
      set(value) {
        this.$store.commit('updateBoneName2', value);
      }
    },
    boneDate1: {
      get() {
        return this.$store.getters.getBoneDate1;
      },
      set(value) {
        this.$store.commit('updateBoneDate1', value);
      },
    },
    boneDate1Type: {
      get() {
        return this.$store.getters.getBoneDate1Type;
      },
      set(value) {
        this.$store.commit('updateBoneDate1Type', value);
      }
    },
    boneDate2: {
      get() {
        return this.$store.getters.getBoneDate2;
      },
      set(value) {
        return this.$store.commit('updateBoneDate2', value);
      },
    },
    boneDate2Type: {
      get() {
        return this.$store.getters.getBoneDate2Type;
      },
      set(value) {
        this.$store.commit('updateBoneDate2Type', value);
      }
    },
    boneReligion: {
      get() {
        return this.$store.getters.getBoneReligion;
      },
      set(value) {
        this.$store.commit('updateBoneReligion', value);
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
    defaultName2Placeholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      return this.selectedType;
    },
    defaultReligionPlaceholder(){
      if(this.engraveType == '기독교' || this.engraveType == '불교' || this.engraveType == '천주교'){
        this.religion = this.engraveType;
        return this.engraveType;
      }
      else{
        this.religion = '';
        return '종교 입력';
      }
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
    showBoneName1Warning() {
      const boneName1Length = this.boneName1.trim().length;
      return (boneName1Length < 2 || boneName1Length > 4) && boneName1Length !== 0;
    },
    showBoneName1KoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.boneName1.length === 0)
        return false;

      return !(koreanRegex.test(this.boneName1) && !koreanConsonantVowelRegex.test(this.boneName1));
    },
    showBoneName2Warning() {
      const name2Length = this.name2.trim().length;
      return (name2Length < 2 || name2Length > 4) && name2Length !== 0;
    },
    showBoneName2Warning2() {
      const name2Length = this.name2.trim().length;
      return (name2Length < 2 || name2Length > 6) && name2Length !== 0;
    },
    showBoneName2KoreanWarning() {
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
    showBoneDate1Warning() {
      return this.showDateWarning(this.boneDate1);
    },
    showBoneDate2Warning() {
      return this.showDateWarning(this.boneDate2);
    },
    showReligionKoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.religion.length === 0)
        return false;

      return !(koreanRegex.test(this.religion) && !koreanConsonantVowelRegex.test(this.religion));
    },
    showBoneReligionKoreanWarning() {
      // 한글 문자에 대한 정규식
      const koreanRegex= /^[가-힣]*$/;
      const koreanConsonantVowelRegex = /^[가-힣&&[^ㅏ-ㅣㅑ-ㅣㅓ-ㅣㅕ-ㅣㅗ-ㅣㅛ-ㅣㅜ-ㅣㅠ-ㅣㅡ-ㅣ]]*$/;

      if(this.boneReligion.length === 0)
        return false;

      return !(koreanRegex.test(this.boneReligion) && !koreanConsonantVowelRegex.test(this.boneReligion));
    },
    showNoteWarning() {
      if(this.note.length === 0)
        return false;
      const noteLength = this.note.trim().length;
      return (noteLength < 1 || noteLength > 40) && noteLength !== 0;
    },
    boneClass() {
      // bonsex 변수 값에 따라 다른 클래스를 반환
      return {
        'bone': this.boneSex === '',
        'bone-male': this.boneSex === '남성',
        'bone-female': this.boneSex === '여성',
        // 다른 클래스에 따른 조건 추가
      };
    },
  },
  mounted() {
    // 페이지 로드 시 로그인 상태 확인
    const authenticated = localStorage.getItem('authenticated');
    const user = JSON.parse(localStorage.getItem('user')); // 유저 정보를 JSON으로 파싱

    if (authenticated === 'true' && user) {
      // console.log('이미 인증된 상태입니다.');
    }else{
      // console.log('인증이 필요한 상태입니다.');
      // 홈 페이지로 이동
      this.$router.push({ name: 'main' }); // 'home'은 홈 라우터의 이름이라 가정
    }
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);
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
    updateBoneSelectType(boneSelectedType){
      this.$store.commit('updateBoneSelectedType', boneSelectedType);
    },
    encodedName1() {
      const trimmedName1 = this.name1.trim();
      return trimmedName1 === '' ? '홍길동' : encodeURIComponent(trimmedName1);
    },
    encodedName2() {
      const trimmedName2 = this.name2.trim();

      if(this.engraveType === '기독교')
        return trimmedName2 === '' ? '직분' : encodeURIComponent(trimmedName2);
      else if(this.engraveType === '불교')
        return trimmedName2 === '' ? '법명' : encodeURIComponent(trimmedName2);
      else if(this.engraveType === '천주교')
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
      if (formattedDate.endsWith('-') || isNaN(formattedDate.slice(-1))) {
        formattedDate = formattedDate.slice(0, -1);
      }

      // 날짜 형식으로 변환한 값을 갱신
      if (targetDate === 'date1') {
        this.date1 = formattedDate;
      } else if (targetDate === 'date2') {
        this.date2 = formattedDate;
      } else if (targetDate === 'boneDate1') {
        this.boneDate1 = formattedDate;
      } else if (targetDate === 'boneDate2') {
        this.boneDate2 = formattedDate;
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
      this.name1 = decodeURIComponent(this.encodedName1());
      this.name2 = decodeURIComponent(this.encodedName2());
      this.date1 = decodeURIComponent(this.encodedDate1());
      this.date2 = decodeURIComponent(this.encodedDate2());
      if(this.engraveType == 'SGI' || this.engraveType == '묘법')
        this.$store.commit('updateSelectedType2', '문구');
      else
        this.$store.commit('updateSelectedType2', this.engraveType);
      this.showRouterView = false;
      this.$store.commit('updateName3', '')
    },
    updateRouteData2(){
      this.name1 = decodeURIComponent(this.encodedName1());
      this.name2 = decodeURIComponent(this.encodedName2());
      this.date1 = decodeURIComponent(this.encodedDate1());
      this.date2 = decodeURIComponent(this.encodedDate2());
      this.$store.commit('updateSelectedType2', '없음');
      // 로컬스토리지 저장
      this.$store.commit('updateName3', '없음')
    },
    updateSelectedUrnType(event) {
      this.selectedUrnType = event.target.value;
    },
  },
};
</script>

<style>
.info-text-align-center {
  width: 100%;
  display: inline-block;
  text-align: left;
  /* background-color: blue; */
}
.bone {
  padding: 20px;
  border-radius: 10px;
  background-color: rgb(243, 243, 243); /* 예시로 남성일 때 파란색 배경 */
}

.bone-male {
  padding: 20px;
  border-radius: 10px;
  background-color: rgb(115, 148, 219); /* 예시로 남성일 때 파란색 배경 */
}

.bone-female {
  padding: 20px;
  border-radius: 10px;
  background-color: rgb(229, 143, 156); /* 예시로 여성일 때 분홍색 배경 */
}
</style>
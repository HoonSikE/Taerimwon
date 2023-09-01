<template>
  <div class="">
    <div class="app">
      <router-link v-if="showRouterView" :to="{name: 'engraveDetail'}" class="title4">
            👉 [이전 페이지]
      </router-link>
      <router-link v-if="!showRouterView" :to="{name: 'tabletCreateView'}" @click.native="updateRouteData()" class="title4">
            👉 [이전 페이지]
      </router-link>

      <div class="title">
        <!-- <span class="title-gray">1-2-3-</span>4<br>
        결과 -->
        <!-- <div class="title2"> -->
        <span>각인</span>
        <span class="title6">({{engraveType}} 
          <span v-if="engraveType !== selectedType"> [{{selectedType}}] </span>
          )
        </span>
        <span v-if="selectedType2 !== '없음'">
          / 위패
          <span class="title6">({{engraveType}} 
            <span v-if="name3 !== '없음'"> [본관] </span>
            )
          </span>
        </span>
          예시
      <!-- </div> -->
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
      <div class="container" :class="{ 'fullscreen': isFullscreen }" @click="toggleFullscreen">
        <!-- 합골 -->
        <div v-if="selectedUrnType.startsWith('합골')" class="boneEngrave_container">
          <div class="boneEngrave_image_container">
            <img class="boneEngrave_image" v-if="boneEngraveCapturedImage" :src="boneEngraveCapturedImage" alt="합골 예시 사진" />
          </div>
        </div>
        <!-- 각인 -->
        <div v-else class="engrave_container">
          <div class="engrave_image_container">
            <img class="engrave_image" v-if="engraveCapturedImage" :src="engraveCapturedImage" alt="각인 예시 사진" />
          </div>
        </div>
        <!-- 위패 -->
        <div class="tablet_container" v-if="selectedType2 !== '없음' && selectedType2 !== '문구'">
          <div class="tablet_image_container">
            <img class="tablet_image" v-if="tabletCapturedImage" :src="tabletCapturedImage" alt="위패 예시 사진" />
          </div>
        </div>
        <div class="tablet_container2" v-if="selectedType2 === '문구'">
          <div class="tablet_image_container2">
            <img class="tablet_image2" v-if="tabletCapturedImage2" :src="tabletCapturedImage2" alt="문구 예시 사진" />
          </div>
        </div>
        <div class="tablet_container3" v-if="selectedTabletType.endsWith('(사진)')">
          <div class="tablet_image_container3">
            <img class="tablet_image3" v-if="tabletCapturedImage3" :src="tabletCapturedImage3" alt="위패(사진) 사진" />
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
       <!-- 합골 -->
      <div v-if="selectedUrnType.startsWith('합골') && boneEngraveImageContainerVisible" class="image-text-container_bone" ref="boneEngraveImageContainer">
        <!-- 기존 / 추가 -->
        <div v-if="boneSex === '남성'" class="text-container_bone1_1">
          <span class="resultText1">
            <span v-if="boneEngraveType == '일반' || boneEngraveType == '불교' || boneEngraveType == '묘법' || boneEngraveType == 'SGI' || boneEngraveType == '원불교'" class="resultText1_0_1">生</span>
            <span v-if="boneEngraveType == '기독교' || boneEngraveType == '순복음'" class="resultText1_0_2">出生</span>
            <span v-if="boneEngraveType == '천주교'" class="resultText1_0_2">出生</span>

            <span class="resultText1_1">{{boneDate1.substr(0, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(1, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(2, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(3, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate1.substr(5, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(6, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate1.substr(8, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(9, 1)}}</span>
            <span v-if="boneDate1Type==='음력'" class="resultText1_3">陰</span>
            <span v-if="boneDate1Type==='양력'" class="resultText1_3">陽</span>
          </span>
          <!-- 일반, 기독교, 불교, 천주교-->
          <span class="resultText2" v-if="boneSelectedType === '일반' || boneSelectedType === '기독교' || boneSelectedType === '불교'|| boneSelectedType === '불교[검정]'
                                    || boneSelectedType === '천주교' || boneEngraveType == '순복음' || boneEngraveType == '원불교'">
            <div v-if="boneEngraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark_bone"></div>
            <span class="resultText2_0" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_0-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
          </span>
          <!-- 형제(아래)) -->
          <span class="resultText2" v-if="boneSelectedType === '형제'">
            <div v-if="boneEngraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark_bone"></div>
            <span class="resultText2_1" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_1-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_1_down">
              형제
            </span>
          </span>
          <!-- SGI(위)) -->
          <span class="resultText2" v-if="boneSelectedType === 'SGI' || boneSelectedType === 'SGI(금색)'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_2_up">
              SGI
            </span>
            <span class="resultText2_2" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_2-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
          </span>
          <!-- 묘볍(위아래) -->
          <span class="resultText2" v-if="boneSelectedType === '묘법' || boneSelectedType === '묘법(금색)'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_3_up">
              妙法
            </span>
            <span class="resultText2_3" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_3-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_3_down">
              位
            </span>
          </span>
          <!-- 세례명(아래)) -->
          <span class="resultText2" v-if="boneSelectedType === '세례명'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_4_1" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_4_1-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_4_2" v-if="boneName2.length === 2 || boneName2.length === 3">
              {{boneName2}}
            </span>
            <span class="resultText2_4_2-4" v-if="boneName2.length === 4">
              {{boneName2}}
            </span>
            <span class="resultText2_4_2-5" v-if="boneName2.length === 5">
              {{boneName2}}
            </span>
            <span class="resultText2_4_2-6" v-if="boneName2.length === 6">
              {{boneName2}}
            </span>
          </span>
          <!-- 직분, 법명(위)-->
          <span class="resultText2" v-if="boneSelectedType === '직분' || selectedType === '법명' || selectedType === '법명[검정]'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_5_2" v-if="boneName2.length === 2 || boneName2.length === 3">
              {{boneName2}}
            </span>
            <span class="resultText2_5_2-4" v-if="boneName2.length === 4">
              {{boneName2}}
            </span>
            <span class="resultText2_5_1" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_5_1-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
          </span>
          <span class="resultText1">
            <span v-if="boneEngraveType == '일반' || boneEngraveType == '불교' || boneEngraveType == '묘법' || boneEngraveType == 'SGI' || boneEngraveType == '원불교'" class="resultText1_0_1">卒</span>
            <span v-if="boneEngraveType == '기독교' || boneEngraveType == '순복음'" class="resultText1_0_2">召天</span>
            <span v-if="boneEngraveType == '천주교'" class="resultText1_0_3">善終</span>

            <span class="resultText1_1">{{boneDate2.substr(0, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(1, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(2, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(3, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate2.substr(5, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(6, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate2.substr(8, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(9, 1)}}</span>
            <span v-if="boneDate2Type==='음력'" class="resultText1_3">陰</span>
            <span v-if="boneDate2Type==='양력'" class="resultText1_3">陽</span>
          </span>
        </div>
        <div v-if="boneSex === '남성'" class="text-container_bone1_2">
          <span class="resultText1">
            <span v-if="engraveType == '일반' || engraveType == '불교' || engraveType == '묘법' || engraveType == 'SGI' || engraveType == '원불교'" class="resultText1_0_1">生</span>
            <span v-if="engraveType == '기독교' || engraveType == '순복음'" class="resultText1_0_2">出生</span>
            <span v-if="engraveType == '천주교'" class="resultText1_0_2">出生</span>

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
          <span class="resultText2" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교' || selectedType === '불교[검정]'
                                    || selectedType === '천주교' || selectedType === '순복음' || selectedType === '원불교'">
            <div v-if="engraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark"></div>
            <span class="resultText2_0" v-if="name1.length === 2 || name1.length === 3">
              {{encodedName1}}
            </span>
            <span class="resultText2_0-4" v-if="name1.length === 4">
              {{encodedName1}}
            </span>
          </span>
          <!-- 형제(아래)) -->
          <span class="resultText2" v-if="selectedType === '형제'">
            <div v-if="engraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark"></div>
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
          <span class="resultText2" v-if="selectedType === 'SGI' || selectedType === 'SGI(금색)'">
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
          <span class="resultText2" v-if="selectedType === '묘법' || selectedType === '묘법(금색)'">
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
          <!-- 직분 / 법명(위)-->
          <span class="resultText2" v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '법명[검정]'">
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
            <span v-if="engraveType == '일반' || engraveType == '불교' || engraveType == '묘법' || engraveType == 'SGI' || engraveType == '원불교'" class="resultText1_0_1">卒</span>
            <span v-if="engraveType == '기독교' || engraveType == '순복음'" class="resultText1_0_2">召天</span>
            <span v-if="engraveType == '천주교'" class="resultText1_0_3">善終</span>

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
        <!-- 추가 / 기존 -->
        <div v-if="boneSex === '여성'" class="text-container_bone2_1">
          <span class="resultText1">
            <span v-if="engraveType == '일반' || engraveType == '불교' || engraveType == '묘법' || engraveType == 'SGI' || engraveType == '원불교'" class="resultText1_0_1">生</span>
            <span v-if="engraveType == '기독교' || engraveType == '순복음'" class="resultText1_0_2">出生</span>
            <span v-if="engraveType == '천주교'" class="resultText1_0_2">出生</span>

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
          <span class="resultText2" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교' || selectedType === '불교[검정]'
                                    || selectedType === '천주교' || selectedType === '순복음' || selectedType === '원불교'">
            <div v-if="engraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark"></div>
            <span class="resultText2_0" v-if="name1.length === 2 || name1.length === 3">
              {{encodedName1}}
            </span>
            <span class="resultText2_0-4" v-if="name1.length === 4">
              {{encodedName1}}
            </span>
          </span>
          <!-- 형제(아래)) -->
          <span class="resultText2" v-if="selectedType === '형제'">
            <div v-if="engraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark"></div>
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
          <span class="resultText2" v-if="selectedType === 'SGI' || selectedType === 'SGI(금색)'">
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
          <span class="resultText2" v-if="selectedType === '묘법' || selectedType === '묘법(금색)'">
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
          <!-- 직분 / 법명(위)-->
          <span class="resultText2" v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '법명[검정]'">
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
            <span v-if="engraveType == '일반' || engraveType == '불교' || engraveType == '묘법' || engraveType == 'SGI' || engraveType == '원불교'" class="resultText1_0_1">卒</span>
            <span v-if="engraveType == '기독교' || engraveType == '순복음'" class="resultText1_0_2">召天</span>
            <span v-if="engraveType == '천주교'" class="resultText1_0_3">善終</span>

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
        <div v-if="boneSex === '여성'" class="text-container_bone2_2">
          <span class="resultText1">
            <span v-if="boneEngraveType == '일반' || boneEngraveType == '불교' || boneEngraveType == '묘법' || boneEngraveType == 'SGI' || boneEngraveType == '원불교'" class="resultText1_0_1">生</span>
            <span v-if="boneEngraveType == '기독교' || boneEngraveType == '순복음'" class="resultText1_0_2">出生</span>
            <span v-if="boneEngraveType == '천주교'" class="resultText1_0_2">出生</span>

            <span class="resultText1_1">{{boneDate1.substr(0, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(1, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(2, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(3, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate1.substr(5, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(6, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate1.substr(8, 1)}}</span>
            <span class="resultText1_1">{{boneDate1.substr(9, 1)}}</span>
            <span v-if="boneDate1Type==='음력'" class="resultText1_3">陰</span>
            <span v-if="boneDate1Type==='양력'" class="resultText1_3">陽</span>
          </span>
          <!-- 일반, 기독교, 불교, 천주교-->
          <span class="resultText2" v-if="boneSelectedType === '일반' || boneSelectedType === '기독교' || boneSelectedType === '불교' || boneSelectedType === '불교[검정]'
                                    || boneSelectedType === '천주교' || boneEngraveType == '순복음' || boneEngraveType == '원불교'">
            <div v-if="boneEngraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark_bone"></div>
            <span class="resultText2_0" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_0-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
          </span>
          <!-- 형제(아래)) -->
          <span class="resultText2" v-if="boneSelectedType === '형제'">
            <div v-if="boneEngraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark_bone"></div>
            <span class="resultText2_1" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_1-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_1_down">
              형제
            </span>
          </span>
          <!-- SGI(위)) -->
          <span class="resultText2" v-if="boneSelectedType === 'SGI' || boneSelectedType === 'SGI(금색)'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_2_up">
              SGI
            </span>
            <span class="resultText2_2" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_2-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
          </span>
          <!-- 묘볍(위아래) -->
          <span class="resultText2" v-if="boneSelectedType === '묘법' || boneSelectedType === '묘법(금색)'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_3_up">
              妙法
            </span>
            <span class="resultText2_3" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_3-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_3_down">
              位
            </span>
          </span>
          <!-- 세례명(아래)) -->
          <span class="resultText2" v-if="boneSelectedType === '세례명'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_4_1" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_4_1-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_4_2" v-if="boneName2.length === 2 || boneName2.length === 3">
              {{boneName2}}
            </span>
            <span class="resultText2_4_2-4" v-if="boneName2.length === 4">
              {{boneName2}}
            </span>
            <span class="resultText2_4_2-5" v-if="boneName2.length === 5">
              {{boneName2}}
            </span>
            <span class="resultText2_4_2-6" v-if="boneName2.length === 6">
              {{boneName2}}
            </span>
          </span>
          <!-- 직분 / 법명(위)-->
          <span class="resultText2" v-if="boneSelectedType === '직분' || boneSelectedType === '법명' || boneSelectedType === '법명[검정]'">
            <div :class="getResult2Mark_bone"></div>
            <span class="resultText2_5_2" v-if="boneName2.length === 2 || boneName2.length === 3">
              {{boneName2}}
            </span>
            <span class="resultText2_5_2-4" v-if="boneName2.length === 4">
              {{boneName2}}
            </span>
            <span class="resultText2_5_1" v-if="boneName1.length === 2 || boneName1.length === 3">
              {{encodedBoneName1}}
            </span>
            <span class="resultText2_5_1-4" v-if="boneName1.length === 4">
              {{encodedBoneName1}}
            </span>
          </span>
          <span class="resultText1">
            <span v-if="boneEngraveType == '일반' || boneEngraveType == '불교' || boneEngraveType == '묘법' || boneEngraveType == 'SGI' || boneEngraveType == '원불교'" class="resultText1_0_1">卒</span>
            <span v-if="boneEngraveType == '기독교' || boneEngraveType == '순복음'" class="resultText1_0_2">召天</span>
            <span v-if="boneEngraveType == '천주교'" class="resultText1_0_3">善終</span>

            <span class="resultText1_1">{{boneDate2.substr(0, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(1, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(2, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(3, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate2.substr(5, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(6, 1)}}</span>
            <span class="resultText1_2">•</span>
            <span class="resultText1_1">{{boneDate2.substr(8, 1)}}</span>
            <span class="resultText1_1">{{boneDate2.substr(9, 1)}}</span>
            <span v-if="boneDate2Type==='음력'" class="resultText1_3">陰</span>
            <span v-if="boneDate2Type==='양력'" class="resultText1_3">陽</span>
          </span>
        </div>
      </div>
      <!--=====각인=====-->
      <div v-if="!selectedUrnType.startsWith('합골') && engraveImageContainerVisible" class="image-text-container" ref="engraveImageContainer">
        <div class="text-container">
          <span class="resultText1">
            <span v-if="engraveType == '일반' || engraveType == '불교' || engraveType == '묘법' || engraveType == 'SGI' || engraveType == '원불교'" class="resultText1_0_1">生</span>
            <span v-if="engraveType == '기독교' || engraveType == '순복음'" class="resultText1_0_2">出生</span>
            <span v-if="engraveType == '천주교'" class="resultText1_0_2">出生</span>

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
          <span class="resultText2" v-if="selectedType === '일반' || selectedType === '기독교' || selectedType === '불교' || selectedType === '불교[검정]'
                                    || selectedType === '천주교' || selectedType === '순복음' || selectedType === '원불교'">
            <div v-if="engraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark"></div>
            <span class="resultText2_0" v-if="name1.length === 2 || name1.length === 3">
              {{encodedName1}}
            </span>
            <span class="resultText2_0-4" v-if="name1.length === 4">
              {{encodedName1}}
            </span>
          </span>
          <!-- 형제(아래)) -->
          <span class="resultText2" v-if="selectedType === '형제'">
            <div v-if="engraveType ==='일반'" class="getResult2Mark_0">
              故
            </div>
            <div v-else :class="getResult2Mark"></div>
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
          <span class="resultText2" v-if="selectedType === 'SGI' || selectedType === 'SGI(금색)'">
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
          <span class="resultText2" v-if="selectedType === '묘법' || selectedType === '묘법(금색)'">
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
          <!-- 직분 / 법명(위)-->
          <span class="resultText2" v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '법명[검정]'">
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
            <span v-if="engraveType == '일반' || engraveType == '불교' || engraveType == '묘법' || engraveType == 'SGI' || engraveType == '원불교'" class="resultText1_0_1">卒</span>
            <span v-if="engraveType == '기독교' || engraveType == '순복음'" class="resultText1_0_2">召天</span>
            <span v-if="engraveType == '천주교'" class="resultText1_0_3">善終</span>

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
      <!--=====위패=====-->
      <div v-if="!selectedTabletType.endsWith('(사진)') && tabletImageContainerVisible && selectedType2 !== '없음' && selectedType2 !== '문구'" class="image-text-container2" ref="tabletImageContainer">
        <div class="text-container2">
          <!-- 본관 선택 x-->
          <div v-if="name3 === '없음'" class="resultText3">
            <div v-if="engraveType ==='일반'" class="getResult3Mark_0">
              故
            </div>
            <div v-else :class="getResult3Mark"></div>
            <!-- 일반 -->
            <span class="" v-if="engraveType === '일반' || engraveType === '불교' || engraveType === '불교[검정]' ">
              <span class="resultText3_1" v-if="name1.length === 2 || name1.length === 3">
                {{encodedName1}}
              </span>
              <span class="resultText3_1-4" v-if="name1.length === 4">
                {{encodedName1}}
              </span>
            </span>
            <!-- 위 글자 -->
            <span class="" v-if="engraveType === '기독교'">
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
            <span class="" v-if="engraveType === '천주교'">
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
            <span class="resultText4_1_1" data-letter-count="5" v-if="engraveType === '일반' && name3.length=== 5">
              {{name3}}
            </span>
            <span class="resultText4_1_1" data-letter-count="6" v-if="engraveType === '일반' && name3.length=== 6">
              {{name3}}
            </span>
            <span class="resultText4_1_1" data-letter-count="7" v-if="engraveType === '일반' && name3.length=== 7">
              {{name3}}
            </span>
            <span class="resultText4_1_1" data-letter-count="8" v-if="engraveType === '일반' && name3.length=== 8">
              {{name3}}
            </span>
            <span class="resultText4_1_1" data-letter-count="9" v-if="engraveType === '일반' && name3.length=== 9">
              {{name3}}
            </span>
            <!-- 불교 -->
            <span class="resultText4_1_2" data-letter-count="5" v-if="engraveType === '불교' && name3.length=== 5">
              {{name3}}
            </span>
            <span class="resultText4_1_2" data-letter-count="6" v-if="engraveType === '불교' && name3.length=== 6">
              {{name3}}
            </span>
            <span class="resultText4_1_2" data-letter-count="7" v-if="engraveType === '불교' && name3.length=== 7">
              {{name3}}
            </span>
            <span class="resultText4_1_2" data-letter-count="8" v-if="engraveType === '불교' && name3.length=== 8">
              {{name3}}
            </span>
            <span class="resultText4_1_2" data-letter-count="9" v-if="engraveType === '불교' && name3.length=== 9">
              {{name3}}
            </span>
            <!-- 기독교 직분 위 글자 -->
            <span class="resultText4_2" v-if="engraveType === '기독교'">
              <!-- 직분 -->
              <span class="resultText4_2_2">{{name2}}</span>
              <!-- 이름 -->
              <span class="resultText4_2_1" data-letter-count="5" v-if="name3.length=== 5">
                {{name3}}
              </span>
              <span class="resultText4_2_1" data-letter-count="6" v-if="name3.length=== 6">
                {{name3}}
              </span>
              <span class="resultText4_2_1" data-letter-count="7" v-if="name3.length=== 7">
                {{name3}}
              </span>
              <span class="resultText4_2_1" data-letter-count="8" v-if="name3.length=== 8">
                {{name3}}
              </span>
              <span class="resultText4_2_1" data-letter-count="9" v-if="name3.length=== 9">
                {{name3}}
              </span>
              <!-- 아래 -->
              <span class="resultText4_2_3">
                召天
              </span>
            </span>
            <!-- 천주교 세례명 아래 글자 -->
            <span class="resultText4_3" v-if="engraveType === '천주교'">
              <!-- 이름 -->
              <span class="resultText4_3_1" data-letter-count="5" v-if="name3.length=== 5">
                {{name3}}
              </span>
              <span class="resultText4_3_1" data-letter-count="6" v-if="name3.length=== 6">
                {{name3}}
              </span>
              <span class="resultText4_3_1" data-letter-count="7" v-if="name3.length=== 7">
                {{name3}}
              </span>
              <span class="resultText4_3_1" data-letter-count="8" v-if="name3.length=== 8">
                {{name3}}
              </span>
              <span class="resultText4_3_1" data-letter-count="9" v-if="name3.length=== 9">
                {{name3}}
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
      <!-- 문구 -->
      <div v-if="!selectedTabletType.endsWith('(사진)') && tabletImageContainerVisible2 && selectedType2 === '문구'" class="image-text-container2" ref="tabletImageContainer2">
        <div class="text-container2">
          <div v-if="name3Type === 'one'" class="resultText5">
            <div class="resultText5_1">
              {{ name3_1 }}
            </div>
          </div>
          <div v-if="name3Type === 'two'" class="resultText5">
            <div class="resultText5_2_1">
              {{ name3_2 }}
            </div>
            <div class="resultText5_2_2">
              {{ name3_1 }}
            </div>
          </div>
          <div v-if="name3Type === 'three'" class="resultText5">
            <div class="resultText5_3_1">
              {{ name3_3 }}
            </div>
            <div class="resultText5_3_2">
              {{ name3_2 }}
            </div>
            <div class="resultText5_3_3">
              {{ name3_1 }}
            </div>     
          </div> 
        </div>
      </div>
      <!-- 사진 -->
      <div v-if="selectedTabletType.endsWith('(사진)') && tabletImageContainerVisible3" class="image-text-container3" ref="tabletImageContainer3">
        <img v-if="imageUrl" :src="imageUrl" alt="Uploaded Image" style="width: 145px; height: auto; margin-left: 30px; margin-bottom: 20px;"/>
      </div>
      <br>
      <hr>
      <div>
        <div v-if="selectedLocation == '화장장'">
          화장장: {{ cremationArea }}<br>
          화장시간: {{ cremationTime }}<br>
        </div>
        <div v-else-if="selectedLocation == '장례식장'">
          장례식장 명: {{ cremationArea }}<br>
          호수: {{ funeralNumber }}<br>
          함 도착시간: {{ funeralTime }}<br>
        </div>
        <div v-else-if="selectedLocation == '장지'">
          장지명: {{ burialName }}<br>
          함 도착시간: {{ burialTime }}<br>
        </div>
        <hr>
        상주명: {{ clientName }}<br>
        상주번호: {{ clientPhone }}<br>
        <hr>
        고인명: {{name1}}<br>
        생년월일: {{date1}} {{date1Type}}<br>
        사망월일: {{date2}} {{date2Type}}<br>
        <div v-if="engraveType != '일반' && engraveType != 'SGI' && engraveType != '묘법'">
          종교: {{religion }}
        </div>
        <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
          {{selectedType}}명: {{name2}}<br>
        </div>
        <hr>
        유골함 각인 종류: {{engraveType}} [{{selectedType}}]<br>
        유골함 종류: {{ selectedUrnType }}<br>
        <hr>
        <div v-if="selectedUrnType.startsWith('합골')">
          합골 추가 정보<br>
           - 성별: {{ boneSex }}<br>
           - 고인명: {{ boneName1 }}<br>

           <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
            - {{ selectedType }}: {{ boneName2 }}<br>
          </div>
           - 생년월일: {{ boneDate1 }} {{ boneDate1Type }}<br>
           - 사망월일: {{ boneDate2 }} {{ boneDate2Type }}<br>
          <div v-if="engraveType !== '일반' && engraveType !== 'SGI' && engraveType !== '묘법'">
            - 종교: {{ boneReligion }}<br>
          </div>
           - 유골함 각인 종류: {{boneEngraveType}} [{{boneSelectedType}}]<br>
          <hr>
        </div>
        <div v-if="selectedType2 === '없음'">
          위패 종류: 없음<br>
        </div>
        <div v-else>
          <div v-if="!selectedTabletType.endsWith('(사진)')">
            위패 각인 종류: {{selectedType2}}<br>
            위패 종류: {{ selectedTabletType }}<br>
            <div v-if="name3 !== '없음' && selectedType2 != '문구'">
              위패 내용: {{this.name3}}<br>
            </div>
            <div v-if="selectedType2 != '문구'">
              <div v-if="selectedType === '직분' || selectedType === '법명' || selectedType === '세례명'">
                {{this.selectedType}}: {{this.name2}}<br>
              </div>
              고인 성함: {{this.name1}}<br>
            </div>
            <div v-if="selectedType2 === '문구'">
              문구 내용<br>
              <div v-if="name3Type === 'one' || name3Type === 'two' || name3Type === 'three'">
                - 문구1: {{ name3_1 }}
              </div>
              <div v-if="name3Type === 'two' || name3Type === 'three'">
                - 문구2: {{ name3_2 }}
              </div>
              <div v-if="name3Type === 'three'">
                - 문구3: {{ name3_3 }}
              </div>
           </div>
          </div>
          <div v-else>
            위패 종류: {{ selectedTabletType }}<br>
            <div class="text-align-center">
              <img v-if="imageUrl" :src="imageUrl" alt="Uploaded Image" class="tablet_Image_Container" style="width: 145px; height: auto;"/>
              <div>
                <!-- 사진 다운로드 버튼 -->
                <button class="download-button" @click="downloadContainer2">사진 다운로드</button>
              </div>
            </div>
          </div>
        </div>
        <hr>
        발주자명: {{ leaderName }}<br>
        전화번호: {{ leaderPhone }}<br>
        소속: {{ leaderDepartment }}<br>
        <hr>
        특이사항: {{ note }}<br>
        <hr>
      </div>
    </div>
    <div class="appbr">
      <br>
    </div>
    <div class="app">
        <button v-if="isIOS" class="order_button" @click="openSMS('ios'); addOrder();">태림원(인천지점) 발주</button>
        <button v-if="isAndroid" class="order_button" @click="openSMS('android'); addOrder();">태림원(인천지점) 발주</button><br>
        <button v-if="isUnknown" class="order_button" @click="addOrder();">문자 호환되지 않는 기종입니다.</button>
        <!-- <button v-if="isUnknown" class="order_button" :onclick="iosSMSEntry">태림원(인천지점) 발주</button> -->
    </div>
    <div class="appbr">
      <br>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex';
import html2canvas from 'html2canvas';
import { getFirestore, collection, addDoc, getDocs, updateDoc, doc, where, query } from "firebase/firestore";
import { getStorage, ref, uploadBytes } from "firebase/storage";

export default {
  data() {
    return {
      leaderName: '',
      leaderPhone: '',
      leaderDepartment: '',

      isFullscreen: false,

      boneEngraveCapturedImage: null,
      engraveCapturedImage: null,
      tabletCapturedImage: null,
      tabletCapturedImage2: null,
      tabletCapturedImage3: null,

      boneEngraveImageContainerVisible: true,
      engraveImageContainerVisible: true,

      tabletImageContainerVisible: true,
      tabletImageContainerVisible2: true,
      tabletImageContainerVisible3: true,

      isOrder: false,
    };
  },
  computed: {
    ...mapGetters([
      // 'getLeaderName',
      // 'getLeaderPhone',
      // 'getLeaderDepartment',

      'getClientName',
      'getClientPhone',

      'getSelectedLocation',

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

      'getSelectedTabletType',

      'getSelectedType2',
      'getNote',

      'getSelectedFile',
      'getImageUrl',
    ]),
    // leaderName: {
    //   get() {
    //     return this.$store.getters.getLeaderName;
    //   },
    // },
    // leaderPhone: {
    //   get() {
    //     return this.$store.getters.getLeaderPhone;
    //   },
    // },
    // leaderDepartment: {
    //   get() {
    //     return this.$store.getters.getLeaderDepartment;
    //   },
    // },
    clientName: {
      get() {
        return this.$store.getters.getClientName;
      },
    },
    clientPhone: {
      get() {
        return this.$store.getters.getClientPhone;
      },
    },
    selectedLocation: {
      get() {
        return this.$store.getters.getSelectedLocation;
      },
    },
    cremationArea: {
      get() {
        return this.$store.getters.getCremationArea;
      },
    },
    cremationTime: {
      get() {
        return this.$store.getters.getCremationTime;
      },
    },
    selectedUrnType: {
      get() {
        return this.$store.getters.getSelectedUrnType;
      },
    },
    funeralName: {
      get() {
        return this.$store.getters.getFuneralName;
      },
    },
    funeralNumber: {
      get() {
        return this.$store.getters.getFuneralNumber;
      },
    },
    funeralTime: {
      get() {
        return this.$store.getters.getFuneralTime;
      },
    },
    burialName: {
      get() {
        return this.$store.getters.getBurialName;
      },
    },
    burialTime: {
      get() {
        return this.$store.getters.getBurialTime;
      },
    },
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
    },
    name3_1: {
      get() {
        return this.$store.getters.getName3_1;
      },
    },
    name3_2: {
      get() {
        return this.$store.getters.getName3_2;
      },
    },
    name3_3: {
      get() {
        return this.$store.getters.getName3_3;
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
    religion: {
      get() {
        return this.$store.getters.getReligion;
      },
    },
    selectedUrnType: {
      get() {
        return this.$store.getters.getSelectedUrnType;
      },
    },
    boneEngraveType: {
      get() {
        return this.$store.getters.getBoneEngraveType;
      },
    },
    boneSelectedType: {
      get() {
        return this.$store.getters.getBoneSelectedType;
      },
    },
    boneSex: {
      get() {
        return this.$store.getters.getBoneSex;
      },
    },
    boneName1: {
      get() {
        return this.$store.getters.getBoneName1;
      },
    },
    boneName2: {
      get() {
        return this.$store.getters.getBoneName2;
      },
    },
    boneDate1: {
      get() {
        return this.$store.getters.getBoneDate1;
      },
    },
    boneDate1Type: {
      get() {
        return this.$store.getters.getBoneDate1Type;
      },
    },
    boneDate2: {
      get() {
        return this.$store.getters.getBoneDate2;
      },
    },
    boneDate2Type: {
      get() {
        return this.$store.getters.getBoneDate2Type;
      },
    },
    boneReligion: {
      get() {
        return this.$store.getters.getBoneReligion;
      },
    },
    selectedTabletType: {
      get() {
        return this.$store.getters.getSelectedTabletType;
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
    note: {
      get() {
        return this.$store.getters.getNote;
      },
    },
    selectedFile: {
      get() {
        return this.$store.getters.getSelectedFile;
      },
      set(value) {
        this.$store.commit('updateSelectedFile', value);
      }
    },
    imageUrl: {
      get() {
        return this.$store.getters.getImageUrl;
      },
      set(value) {
        this.$store.commit('updateImageUrl', value);
      }
    },

    // 외자 이름
    encodedBoneName1() {
      const trimmedBoneName1 = this.boneName1.trim();

      if(trimmedBoneName1.length === 2){
        return trimmedBoneName1.substr(0, 1) + " " + trimmedBoneName1.substr(1, 1);
      }
      return trimmedBoneName1;
    },
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

      if (this.engraveType === '일반') {
        return 'resultText2_mark1';
      } else if (this.engraveType === '기독교') {
        return 'resultText2_mark2';
      } else if (this.selectedType === '불교' || this.selectedType === '법명') {
        return 'resultText2_mark3';
      } else if (this.selectedType === '불교[검정]' || this.selectedType === '법명[검정]') {
        return 'resultText2_mark3_2';
      } else if (this.engraveType === '천주교') {
        return 'resultText2_mark4';
      }  else if (this.selectedType === 'SGI' || this.selectedType === '묘법') {
        return 'resultText2_mark5';
      }  else if (this.selectedType === 'SGI(금색)' || this.selectedType === '묘법(금색)') {
        return 'resultText2_mark5_2';
      } else if (this.selectedType === '순복음') {
        return 'resultText2_mark6';
      } else if (this.selectedType === '원불교') {
        return 'resultText2_mark7';
      }
    },
    getResult2Mark_bone() {
      let markImageUrl = '';

      if (this.boneEngraveType === '일반') {
        return 'resultText2_mark1';
      } else if (this.boneEngraveType === '기독교') {
        return 'resultText2_mark2';
      } else if (this.boneSelectedType === '불교' || this.boneSelectedType === '법명') {
        return 'resultText2_mark3';
      } else if (this.boneSelectedType === '불교[검정]' || this.boneSelectedType === '법명[검정]') {
        return 'resultText2_mark3_2';
      } else if (this.boneEngraveType === '천주교') {
        return 'resultText2_mark4';
      }  else if (this.boneSelectedType === 'SGI' || this.boneSelectedType === '묘법') {
        return 'resultText2_mark5';
      }  else if (this.boneSelectedType === 'SGI(금색)' || this.boneSelectedType === '묘법(금색)') {
        return 'resultText2_mark5_2';
      } else if (this.boneSelectedType === '순복음') {
        return 'resultText2_mark6';
      } else if (this.boneSelectedType === '원불교') {
        return 'resultText2_mark7';
      }
    },
    getResult3Mark() {
      let markImageUrl = '';

      if (this.engraveType === '일반') {
        return 'resultText3_mark1';
      } else if (this.engraveType === '기독교') {
        return 'resultText3_mark2';
      } else if (this.engraveType === '불교') {
        return 'resultText3_mark3';
      } else if (this.engraveType === '불교[검정]') {
        return 'resultText3_mark3_2';
      } else if (this.engraveType === '천주교') {
        return 'resultText3_mark4';
      }
    },
    getResult4Mark() {
      let markImageUrl = '';

      if (this.engraveType === '기독교') {
        return 'resultText4_mark2';
      } else if (this.engraveType === '불교') {
        return 'resultText4_mark3';
      } else if (this.engraveType === '천주교') {
        return 'resultText4_mark4';
      }
    },
    isIOS() {
      return this.checkMobile() === 'ios';
    },
    isAndroid() {
      return this.checkMobile() === 'android';
    },
    isUnknown() {
      return this.checkMobile() === 'unknown';
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

    // (사진)으로 끝나면 selectedType2를 ''로 설정
    if (this.selectedTabletType.endsWith('(사진)')) {
      this.selectedType2 = '';
    }

    this.captureAndDisplay();

    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);

    // 로컬 스토리지에서 정보 가져오기
    const savedInfo = localStorage.getItem('savedInfo');
    if (savedInfo) {
      const info = JSON.parse(savedInfo);
      this.leaderName = info.leaderName;
      this.leaderPhone = info.leaderPhone;
      this.leaderDepartment = info.leaderDepartment;
      this.saveInfo = true;
    }
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
      var msg = '[명지사]';

      if(this.selectedLocation == '화장장')
        msg += '\n화장장: ' + this.cremationArea
            + '\n화장시간: ' + this.cremationTime; 
      else if(this.selectedLocation == '장례식장')
        msg += '\n장례식장 명: ' + this.cremationArea
            + '\n호수: ' + this.funeralNumber
            + '\n함 도착시간: ' + this.funeralTime; 
      else if(this.selectedLocation == '장지')
        msg += '\n장지명: ' + this.burialName
              + '\n함 도착시간: ' + this.burialTime; 

      msg += '\n\상주명: ' + this.clientName
      + '\n상주번호: ' + this.clientPhone; 

      msg += '\n\n고인명: ' + this.name1
        + '\n생년월일: ' + this.date1 + ' ' + this.date1Type 
        + '\n사망월일: ' + this.date2 + ' ' + this.date2Type;

      if(this.engraveType != '일반' && this.engraveType != 'SGI' && this.engraveType != '묘법')
        msg += '\n종교구분: ' + this.religion;
        
      if(this.selectedType === '직분' || this.selectedType === '법명' || this.selectedType === '세례명')
        msg += '\n' + this.selectedType + '명: ' + this.name2;

      msg += '\n\n유골함 각인 종류: ' + this.engraveType + ' [' + this.selectedType + ']'
        + '\n유골함 종류: ' + this.selectedUrnType;

      if(this.selectedUrnType.startsWith('합골')) {
        msg += '\n\n합골 추가 정보'
          + '\n - 성별' + this.boneSex
          + '\n - 고인명' + this.boneName1;

        if(this.selectedType === '직분' || this.selectedType === '법명' || this.selectedType === '세례명')
          msg += '\n - ' + this.selectedType + ": "+ this.boneName2;
        
          msg += '\n - 생년월일' + this.boneDate1 + " " + this.boneDate1Type
              + '\n - 사망월일' + this.boneDate2 + " " + this.boneDate2Type;

          if(this.engraveType !== '일반' && this.engraveType !== 'SGI' && this.engraveType !== '묘법')
            msg += '\n - 종교: ' + this.boneReligion;
      }

      if(this.selectedType2 == '없음')
        msg += '\n위패 종류: 없음';
      else {
        if(!selectedTabletType.endsWith('(사진)')) {
          msg += '\n위패 각인 종류: ' + this.selectedType2
              + '\n위패 종류: ' + this.selectedTabletType;
          if(this.name3 !== '없음' && this.selectedType2 != '문구'){
            msg += '\n위패 내용: ' + this.name3;
          }
          if(this.selectedType2 != '문구') {
            if(this.selectedType === '직분' || this.selectedType === '법명' || this.selectedType === '세례명')
              msg += '\n' + this.selectedType + ': ' + this.name2;
            msg += '\n고인 성함: ' + this.name1;
          }else {
            msg += '\n문구 내용: ' + this.name3;
            if(this.name3Type === 'one' || this.name3Type === 'two' || this.name3Type === 'three')
              msg += '\n- 문구1: ' + this.name3_1;
            if(this.name3Type === 'one' || this.name3Type === 'two' || this.name3Type === 'three')
              msg += '\n- 문구2: ' + this.name3_2;
            if(this.name3Type === 'one' || this.name3Type === 'two' || this.name3Type === 'three')
              msg += '\n- 문구3: ' + this.name3_3;
          }
        }else{
          msg += '\n위패 각인 종류: ' + this.selectedType2;
        }
      }

      msg += '\n\n발주자명: ' + this.leaderName
        + '\n전화번호: ' + this.leaderPhone
        + '\n소속: ' + this.leaderDepartment
        + '\n\n특이사항: ' + this.note;

      console.log(msg);

      return msg;
    },
    async captureAndDisplay() {
      if(this.selectedUrnType.startsWith('합골')){
        // 합골각인
        const boneEngraveImageContainer = this.$refs.boneEngraveImageContainer;

        // 이미지 컨테이너 캡처
        const boneEngraveCanvas = await html2canvas(boneEngraveImageContainer);
        const boneEngraveCapturedImageDataUrl = boneEngraveCanvas.toDataURL("합골 예시/png");

        this.boneEngraveCapturedImage = boneEngraveCapturedImageDataUrl;
        this.boneEngraveImageContainerVisible = !this.boneEngraveImageContainerVisible; // 이미지 컨테이너를 숨김
      }else{
        // 각인
        const engraveImageContainer = this.$refs.engraveImageContainer;

        // 이미지 컨테이너 캡처
        const engraveCanvas = await html2canvas(engraveImageContainer);
        const engraveCapturedImageDataUrl = engraveCanvas.toDataURL("각인 예시/png");

        this.engraveCapturedImage = engraveCapturedImageDataUrl;
        this.engraveImageContainerVisible = !this.engraveImageContainerVisible; // 이미지 컨테이너를 숨김
      }
      // 위패
      if(this.selectedType2 !== '없음' && this.selectedType2 !== '문구' && !this.selectedTabletType.endsWith('(사진)')){
        const tabletImageContainer = this.$refs.tabletImageContainer;

        // 이미지 컨테이너 캡처
        const tabletCanvas = await html2canvas(tabletImageContainer);
        const tabletCapturedImageDataUrl = tabletCanvas.toDataURL("위패 예시/png");

        this.tabletCapturedImage = tabletCapturedImageDataUrl;
        this.tabletImageContainerVisible = !this.tabletImageContainerVisible; // 이미지 컨테이너를 숨김
      }
      if(this.selectedType2 === '문구' && !this.selectedTabletType.endsWith('(사진)')){
        const tabletImageContainer2 = this.$refs.tabletImageContainer2;

        // 이미지 컨테이너 캡처
        const tabletCanvas = await html2canvas(tabletImageContainer2);
        const tabletCapturedImage2DataUrl = tabletCanvas.toDataURL("문구 예시/png");

        this.tabletCapturedImage2 = tabletCapturedImage2DataUrl;
        this.tabletImageContainerVisible2 = !this.tabletImageContainerVisible2; // 이미지 컨테이너를 숨김
      }
      if(this.selectedTabletType.endsWith('(사진)')){
        // 위패
        const tabletImageContainer3 = this.$refs.tabletImageContainer3;

        // 이미지 컨테이너 캡처
        const tabletCanvas = await html2canvas(tabletImageContainer3);
        const tabletCapturedImage3DataUrl = tabletCanvas.toDataURL("위패(사진) 예시/png");

        this.tabletCapturedImage3 = tabletCapturedImage3DataUrl;
        this.tabletImageContainerVisible3 = !this.tabletImageContainerVisible3; // 이미지 컨테이너를 숨김
      }
    },
    async downloadContainer() {
      const container = document.querySelector('.container'); // 캡처할 요소 선택
      const canvas = await html2canvas(container, { scale: 3 }); // HTML2Canvas를 사용하여 캡처

      // 이미지 데이터를 URL로 변환
      const imageDataURL = canvas.toDataURL('image/png');

      // 이미지 다운로드 링크 생성
      const link = document.createElement('a');
      link.href = imageDataURL;
      link.download = '태림원 예시.png'; // 다운로드될 이미지 파일의 이름 설정
      link.target = '_blank'; // 새 창에서 열리도록 설정
      link.click(); // 클릭 이벤트 실행
    },
    async downloadContainer2() {
      const container = document.querySelector('.tablet_Image_Container'); // 캡처할 요소 선택
      const canvas = await html2canvas(container, { scale: 3 }); // HTML2Canvas를 사용하여 캡처

      // 이미지 데이터를 URL로 변환
      const imageDataURL = canvas.toDataURL('image/png');

      // 이미지 다운로드 링크 생성
      const link = document.createElement('a');
      link.href = imageDataURL;
      link.download = this.selectedTabletType + '.png'; // 다운로드될 이미지 파일의 이름 설정
      link.target = '_blank'; // 새 창에서 열리도록 설정
      link.click(); // 클릭 이벤트 실행
    },
    updateRouteData(){
      if(this.name3 === '없음')
        this.name3 = '';
    },
    dataURLtoBlob(dataURL) {
      const arr = dataURL.split(',');
      const mime = arr[0].match(/:(.*?);/)[1];
      const bstr = atob(arr[1]);
      let n = bstr.length;
      const u8arr = new Uint8Array(n);

      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }

      return new Blob([u8arr], { type: mime });
    },
    openSMS(platform) {
    const phoneNumber = this.leaderPhone;
    const message = encodeURIComponent(this.getMsg());

    if (platform === 'ios') {
      window.location.href = `sms:${phoneNumber}&body=${message}`;
    } else if (platform === 'android') {
      window.location.href = `sms:${phoneNumber}?body=${message}`;
    }
    },
    async addOrder(){
      if(this.isOrder){
        console.log("데이터가 이미 추가되었습니다.");
        return;
      }
      const firestore = getFirestore();

      // 인증 시도 시 사용자 정보 Firestore에 저장
      try {
        // 현재 날짜와 시간 생성
        const currentDate = new Date();
        const currentDate2 =
          currentDate.getFullYear().toString() +
          (currentDate.getMonth() + 1).toString() +
          currentDate.getDate().toString() +
          currentDate.getHours().toString() +
          currentDate.getMinutes().toString() +
          currentDate.getSeconds().toString();
        // Firestore에 사용자 정보 저장
        const phonesCollection = collection(firestore, "orders");
        
        const storage = getStorage();
        let firePath = 'images/' + this.name1 + currentDate2 + '.jpg';
        const imageRef = ref(storage, firePath);

        if (this.selectedTabletType.endsWith('(사진)')) {
          if(this.imageUrl){
            try {
              const blobImage = this.dataURLtoBlob(this.imageUrl);
              await uploadBytes(imageRef, blobImage);
              console.log('이미지 업로드 완료!');
            } catch (error) {
              console.error('이미지 업로드 오류:', error);
            }
          }
        }else{
          this.imageUrl = null
        }
        if(!this.imageUrl)
          firePath = '';
        // console.log(this.imageUrl);
        const newOrder = {
          // 발주자 정보
          leaderName: this.leaderName,
          leaderPhone: this.leaderPhone,
          leaderDepartment: this.leaderDepartment,
          // 상주 정보
          clientName: this.clientName,
          clientPhone: this.clientPhone,
          // 발주 장소 입력
          selectedLocation: this.selectedLocation,
          // 화장장
          cremationArea: this.cremationArea,
          cremationTime: this.cremationTime,
          // 장례식장
          funeralName: this.funeralName,
          funeralNumber: this.funeralNumber,
          funeralTime: this.funeralTime,
          // 장지
          burialName: this.burialName,
          burialTime: this.burialTime,
          // 각인종류 (일반, 기독교, 천주교, ...)
          engraveType: this.engraveType,
          // 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
          selectedType: this.selectedType,
          // 고인 성함
          name1: this.name1,
          // 직분, 세례명, 법명
          name2: this.name2,
          // 생년월일
          date1: this.date1,
          date1Type: this.date1Type,
          // 사망월일
          date2: this.date2,
          date2Type: this.date2Type,
          // 종교
          religion: this.religion,
          // 선택된 유골 종류
          selectedUrnType: this.selectedUrnType,
          // 합골 추가 정보
          // 각인종류 (일반, 기독교, 천주교, ...)
          boneEngraveType: this.boneEngraveType,
          // 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
          boneSelectedType: this.boneSelectedType,
          boneSex: this.boneSex,
          boneName1: this.boneName1,
          boneName2: this.boneName2,
          boneDate1: this.boneDate1,
          boneDate1Type: this.boneDate1Type,
          boneDate2: this.boneDate2,
          boneDate2Type: this.boneDate2Type,
          boneReligion: this.boneReligion,
          // 위패 보기
          showRouterView: this.showRouterView,
          // 위패 종류 (일반, 본관, 문구)
          selectedType2: this.selectedType2,
          // 위패 내용
          name3: this.name3,
          // 문구
          name3Type: this.name3Type,
          name3_1: this.name3_1,
          name3_2: this.name3_2,
          name3_3: this.name3_3,
          // 선택된 위패 종류
          selectedTabletType: this.selectedTabletType,
          // 특이사항
          note: this.note,
          imageUrl: firePath,
          currentDate: currentDate, // 현재 날짜와 시간 추가
        };
        // 문서 ID를 자동으로 생성하고 추가
        const orderRef = await addDoc(phonesCollection, newOrder);
        console.log('데이터 추가 완료! 문서 ID:', orderRef.id);
        this.isOrder = true;
      } catch (error) {
        const errorCode = error.code;
        const errorMessage = error.message;
        console.error(errorCode, errorMessage);
        console.log('데이터 추가 오류!');
      }
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
  width: 60vw;
  /* max-width: 1200px; */

  /* text-align: center; */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2); /* 그림자 효과 설정 */

  background-color: rgb(239, 239, 239);
}
/* 캡처 이미지 출력 */
.boneEngrave_container {
  position: relative;
  width: 40vw;
  /* height: 50vh; */
  margin-bottom: 20px;
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(239, 239, 239); */
}
.boneEngrave_image {
  max-width: 40vw;
  max-height: 60vh;
}
.engrave_container {
  position: relative;
  width: 40vw;
  /* height: 50vh; */
  margin-bottom: -10px;
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(239, 239, 239); */
}
.engrave_image {
  max-width: 40vw;
  max-height: 60vh;
}
.tablet_container {
  position: relative;
  width: 20vw;
  /* height: 50vh; */
  margin-bottom: -10px;

  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(239, 239, 239); */
}
.tablet_image {
  max-width: 20vw;
  max-height: 30vh;
}
.tablet_container2 {
  position: relative;
  width: 20vw;
  /* height: 50vh; */
  margin-bottom: -10px;

  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(239, 239, 239); */
}
.tablet_image2 {
  max-width: 20vw;
  max-height: 30vh;
}
.tablet_container3 {
  position: relative;
  width: 20vw;
  /* height: 50vh; */
  margin-bottom: -10px;

  background-repeat: no-repeat;
  background-position: center;
  /* background-color: rgb(239, 239, 239); */
}
.tablet_image3 {
  max-width: 20vw;
  max-height: 30vh;
}
/*=====합골 시작======*/
.image-text-container_bone {
  display: flex;
  width: 670px;
  height: 650px;
  background-image: url('../../assets/images/engrave/background/bone/bone.jpeg');
  background-repeat: no-repeat;
  background-size: cover;
  background-color: rgb(239, 239, 239);
}
/* 남성일 경우 텍스트 출력 */
.text-container_bone1_1 {
  width: 160px;
  height: 270px;

  /* 중심 포지션 270 240 */
  transform: translate(140px, 280px);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}
.text-container_bone1_2 {
  width: 160px;
  height: 270px;

  /* 중심 포지션 270 240 */
  transform: translate(230px, 280px);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(26, 255, 0, 0.461); */
}
/* 여성일 경우 텍스트 출력 */
.text-container_bone2_1 {
  width: 160px;
  height: 270px;

  /* 중심 포지션 270 240 */
  transform: translate(140px, 280px);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(255, 0, 157, 0.461); */
}
.text-container_bone2_2 {
  width: 160px;
  height: 270px;

  /* 중심 포지션 270 240 */
  transform: translate(230px, 280px);

  writing-mode: vertical-lr; /* 세로로 쓰기 설정 */
  text-orientation: upright; /* 텍스트 방향 유지 */
  white-space: nowrap; /* 텍스트 줄 바꿈 방지 */

  /* background-color: rgba(26, 255, 0, 0.461); */
}
/*=====합골 끝======*/
/*======각인 시작======*/
/* 이미지 출력 */
.image-text-container {
  /* position: relative; */
  width: 540px;
  height: 580px;
  background-image: url('../../assets/images/engrave/background/engrave.png');
  background-repeat: no-repeat;
  background-size: cover;
  background-color: rgb(239, 239, 239);
}
/* 텍스트 출력 */
.text-container {
  width: 160px;
  height: 270px;

  /* 중심 포지션 270 240 */
  transform: translate(197px, 260px);

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
.getResult2Mark_0 {
  width: auto;
  height: 65px;

  font-family: "HYHaeso";
  font-size: 45px;
  font-weight: 900;
  color: black;

  margin-left: 5px;
  margin-top: 15px;

  /* background-color: antiquewhite; */
}
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
  /* background-image: url('../../assets/images/marks/기독교.gif'); */
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
/* 불교[검정] */
.resultText2_mark3_2 {
  width: 60px;
  height: 60px;

  margin-top: 15px;
  margin-left: 20px;
  background-image: url('../../assets/images/marks/불교(검정).gif');
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
  /* background-image: url('../../assets/images/marks/천주교.gif'); */
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
.resultText2_mark5_2 {
  width: 50px;
  height: 50px;

  margin-top: 15px;
  margin-left: 4px;
  background-image: url('../../assets/images/marks/묘법(금).png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 순복음*/
.resultText2_mark6 {
  width: 65px;
  height: 65px;

  margin-top: 10px;
  margin-left: 7px;
  background-image: url('../../assets/images/marks/순복음.png');
  background-repeat: no-repeat;
  background-size: contain;
}
/* 원불교*/
.resultText2_mark7 {
  width: 65px;
  height: 65px;

  margin-top: 10px;
  margin-left: 7px;
  background-image: url('../../assets/images/marks/WonBuddhism.png');
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
  background-color: rgb(239, 239, 239);
}
/* 위패(사진) */
.image-text-container3 {
  /* position: relative; */
  width: 200px;
  height: 480px;

  display: flex;
  align-items: flex-end;

  background-image: url('../../assets/images/tablet/background/tablet.png');
  background-repeat: no-repeat;
  background-size: cover;
  background-color: rgb(239, 239, 239);
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
.getResult3Mark_0 {
  width: auto;
  height: 60px;

  font-family: "HYHaeso";
  font-size: 55px;
  font-weight: 900;
  color: black;

  margin-left: 5px;
  margin-top: px;

  /* background-color: antiquewhite; */
}
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
/* 불교 */
.resultText3_mark3_2 {
  width: 80px;
  height: 80px;

  background-image: url('../../assets/images/marks/불교(검정)..gif');
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

/*======문구 시작======*/
.resultText5 {
  display: inline-block;
  /* justify-content: center; */
  align-items: center;

  color: black;
  font-family: "HYGungSo";

  width: 100%;
  height: 100%;
  /* background-color: rgba(93, 184, 249, 0.56); */
}
/* 1줄 */
.resultText5_1{
  font-size: 36px;
  margin-top: 10px;
  letter-spacing:4px;

  /* 가운데 정렬 */
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 100%;
  height: 100%;
  /* background-color: rgba(93, 184, 249, 0.56); */
}
/* 2줄 */
.resultText5_2_1{
  font-size: 36px;
  margin-top: 10px;
  letter-spacing:3px;

  /* 가운데 정렬 */
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 50%;
  height: 100%;
  /* background-color: rgba(93, 184, 249, 0.56); */
}
.resultText5_2_2{
  font-size: 36px;
  margin-top: 10px;
  letter-spacing:3px;
  
  /* 가운데 정렬 */
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 50%;
  height: 100%;
  /* background-color: rgba(249, 93, 171, 0.56); */
}
/* 3줄 */
.resultText5_3_1{
  font-size: 31px;
  margin-top: 10px;
  letter-spacing:5px;

  margin-left: 5px;

  /* 가운데 정렬 */
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 33.33%;
  height: 100%;
  /* background-color: rgba(186, 249, 93, 0.56); */
}
.resultText5_3_2{
  font-size: 31px;
  margin-top: 10px;
  letter-spacing:5px;

  /* 가운데 정렬 */
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 33.33%;
  height: 100%;
  /* background-color: rgba(93, 249, 119, 0.56); */
}
.resultText5_3_3{
  font-size: 31px;
  margin-top: 10px;
  letter-spacing:5px;

  margin-left: -5px;

  /* 가운데 정렬 */
  display: flex;
  /* justify-content: center; */
  align-items: center;

  width: 33.33%;
  height: 100%;
  /* background-color: rgba(93, 184, 249, 0.56); */
}
/*======문구 끝======*/

/* 예시 밑에 - type */
.title6 {
  font-size: 20px;
  font-family: "BMEULJIROTTF";
}
</style>
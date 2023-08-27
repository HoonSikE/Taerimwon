<template>
  <div class="">
    <div class="title">
      각인 생성하기 
    </div>
    <div class="title2">
      ● 각인 종류
    </div>
    
    <!-- 가로 스크롤 가능한 컨테이너 -->
    <div class="text-align-center">
      <div class="scroll-container">
        <div class="link-container">
          <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('일반', '일반')" class="link-item"
              :class="{ selected: selectedType === '일반' }"
              >
            <img src="../../assets/images/engrave/example/일반.png" width="80" height="130" alt="일반">
            <span class="selectText">[일반]</span>
          </router-link>
          <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('기독교', '직분')" class="link-item"
              :class="{ selected: selectedType === '직분' }"
              @click="selectedType = '직분'">
            <img src="../../assets/images/engrave/example/기독교.png" width="80" height="130" alt="기독교">
            <span class="selectText">[기독교]</span>
          </router-link>
          <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('불교', '불교')" class="link-item"
              :class="{ selected: selectedType === '불교' }"
              @click="selectedType = '불교'">
            <img src="../../assets/images/engrave/example/불교.png" width="80" height="130" alt="불교">
            <span class="selectText">[불교]</span>
          </router-link>
          <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('천주교', '세례명')" class="link-item"
              :class="{ selected: selectedType === '세례명' }"
              @click="selectedType = '세례명'">
            <img src="../../assets/images/engrave/example/천주교.png" width="80" height="130" alt="천주교">
            <span class="selectText">[천주교]</span>
          </router-link>
          <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('SGI', 'SGI')" class="link-item"
              :class="{ selected: selectedType === 'SGI' }"
              @click="selectedType = 'SGI'">
            <img src="../../assets/images/engrave/example/sgi.png" width="80" height="130" alt="SGI">
            <span class="selectText">[SGI]</span>
          </router-link>
          <router-link :to="{name: 'engraveDetail'}" @click.native="updateRouteData('묘법', '묘법')" class="link-item"
              :class="{ selected: selectedType === '묘법' }"
              @click="selectedType = '묘법'">
            <img src="../../assets/images/engrave/example/묘법.png" width="80" height="130" alt="묘법">
            <span class="selectText">[묘법]</span>
          </router-link>
        </div>
      </div>
    </div>
    <hr>
    <div>
      <router-view></router-view>
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
    ]),
    type: {
      get() {
        return this.$store.getters.getType;
      },
      set(value) {
        this.$store.commit('updateType', value);
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
  },
  methods: {
    // ...mapActions(['setRouteData']),
    updateRouteData(type, selectedType){
      this.type = type;
      this.selectedType = selectedType;
    }
  }
};
</script>

<style>
.text-align-center {
  text-align: center;
  font-family: "BMEULJIROTTF";
}

.image-text-container {
  position: relative;
  width: 200px;
  width: 30%;
  /* height: 80%; */
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: blue; */
}

.title {
  text-align: center;
  color: #e92d62;
  font-size: 25px;
  font-family: "BMEULJIROTTF";
}

.title2 {
  text-align: left;
  color: #baca11;
  font-size: 20px;
  font-family: "BMEULJIROTTF";
}

.scroll-container {
  max-width: 100%; /* 화면 너비 이내로 제한 */
  overflow-x: auto; /* 가로 스크롤 가능하도록 설정 */
  white-space: nowrap; /* 자식 요소들이 한 줄로 나란히 표시될 수 있도록 설정 */
  background-color: #cfcfcf; /* 선택 시 배경색을 변경 */
  border-radius: 5px;

  padding: 3px;
  margin-top: 2px;
  margin-bottom: 6px;
}

.link-item {
  display: inline-block;
  text-align: center;
  margin-right: 12px; /* 원하는 간격으로 조정 */
  outline: none; /* 기본 테두리 제거 */
  cursor: pointer;

  margin: 3px;
  padding: 3px;
  padding-left: 5px;
  padding-right: 5px;
  background-color: #212121; /* 선택 시 배경색을 변경 */
  border-radius: 5px;
}

.link-item img {
  display: block;
  margin: 0 auto;
  width: 80px;
  /* height: 100px; */
}

.link-item .selectText {
  /* display: block; */
  margin-top: 5px;
  color: #b50303;
  font-family: "BMEULJIROTTF";
  font-size: 16px;
  white-space: nowrap;
  /* 글자가 한 줄로 유지되도록 설정 */
}

.link-item.selected {
  border: 3px solid #000000; /* 선택 시 보더 스타일을 추가하여 선택 표시 */
  background-color: #545454a6; /* 선택 시 배경색을 변경 */
}
</style>
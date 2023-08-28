<template>
  <div class="app">
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
      'getName2',
      'getShowRouterView'
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
    }
  },
  methods: {
    // ...mapActions(['setRouteData']),
    updateRouteData(type, selectedType){
      this.type = type;
      this.selectedType = selectedType;
      this.name2 = '';
      this.showRouterView = true;
    }
  }
};
</script>

<style>
.image-text-container {
  position: relative;
  width: 200px;
  width: 30%;
  /* height: 80%; */
  background-repeat: no-repeat;
  background-position: center;
  /* background-color: blue; */
}

.scroll-container {
  max-width: 100%; /* 화면 너비 이내로 제한 */
  overflow-x: auto; /* 가로 스크롤 가능하도록 설정 */
  white-space: nowrap; /* 자식 요소들이 한 줄로 나란히 표시될 수 있도록 설정 */
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
  border-radius: 5px;
}

.link-item img {
  display: block;
  margin: 0 auto;
  width: 160px;
  height: 260px;
}

.link-item {
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2); /* 그림자 효과 설정 */
  padding: 5px;
  margin-right: 15px;
}
.selectText {
  color: black;
  font-family: "BMEULJIROTTF";
  font-size: 16px;
  white-space: nowrap;
  text-decoration: none;
}

.link-item.selected {
  border: 3px solid #000000; /* 선택 시 보더 스타일을 추가하여 선택 표시 */
  background-color: #ffffffa6; /* 선택 시 배경색을 변경 */
}
</style>
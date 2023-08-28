<template>
  <div class="">
    <br>
    <div class="app">
      <div class="title">
        각인 주문하기
      </div>
    </div>
    <br>
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
    </div>
    <br>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

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
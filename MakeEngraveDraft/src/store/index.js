import { createStore } from 'vuex'

// 초기 상태 정의
const initialType = '일반';
const initialName0 = '';
const initialName1 = '';
const initialName2 = '';
const initialDate1 = '';
const initialDate1Type = '양력';
const initialDate2 = '';
const initialDate2Type = '양력';
const initialSelectedType = '일반';
const initialSelectedType2 = '';
const initialShowRouterView = true;

export const store = createStore({
  // 저장소
  state: {
    type: initialType,
    name0: initialName0,
    name1: initialName1,
    name2: initialName2,
    date1: initialDate1,
    date1Type: initialDate1Type,
    date2: initialDate2,
    date2Type: initialDate2Type,
    selectedType: initialSelectedType,
    selectedType2: initialSelectedType2,
    showRouterView: initialShowRouterView,
  },
  // state 상대 변경
  // this.$store.commit(이름, 변수);
  mutations: {
    updateType(state, newType) {
      state.type = newType;
    },
    updateName0(state, newName0) {
      state.name0 = newName0;
    },
    updateName1(state, newName1) {
      state.name1 = newName1;
    },
    updateName2(state, newName2) {
      state.name2 = newName2;
    },
    updateDate1(state, newDate1) {
      state.date1 = newDate1;
    },
    updateDate1Type(state, newDate1Type) {
      state.date1Type = newDate1Type;
    },
    updateDate2(state, newDate2) {
      state.date2 = newDate2;
    },
    updateDate2Type(state, newDate2Type) {
      state.date2Type = newDate2Type;
    },
    updateSelectedType(state, newSelectedType) {
      state.selectedType = newSelectedType;
    },
    updateSelectedType2(state, newSelectedType2) {
      state.selectedType2 = newSelectedType2;
    },
    updateShowRouterView(state, newShowRouterView) {
      state.showRouterView = newShowRouterView;
    },
    toggleRouterView(state) {
      state.showRouterView = !state.showRouterView;
    },
    // 홈으로 돌아가기 동작에 대한 mutation
    resetState(state) {
      state.type = initialType;
      state.name0 = initialName0;
      state.name1 = initialName1;
      state.name2 = initialName2;
      state.date1 = initialDate1;
      state.date1Type = initialDate1Type;
      state.date2 = initialDate2;
      state.date2Type = initialDate2Type;
      state.selectedType = initialSelectedType;
      state.selectedType2 = initialSelectedType2;
      state.showRouterView = initialShowRouterView;
    },
  },
  // 상태 변이 (복잡한 연산)
  // this.$store.dispatch(이름, 변수);
  actions: {
    updateType({ commit }, newType) {
      commit('updateType', newType);
    },
    updateName0({ commit }, newName0) {
      commit('updateName0', newName0);
    },
    updateName1({ commit }, newName1) {
      commit('updateName1', newName1);
    },
    updateName2({ commit }, newName2) {
      commit('updateName2', newName2);
    },
    updateDate1({ commit }, newDate1) {
      commit('updateDate1', newDate1);
    },
    updateDate1Type({ commit }, newDate1Type) {
      commit('updateDate1Type', newDate1Type);
    },
    updateDate2({ commit }, newDate2) {
      commit('updateDate2', newDate2);
    },
    updateDate2Type({ commit }, newDate2Type) {
      commit('updateDate2Type', newDate2Type);
    },
    updateSelectedType({ commit }, newSelectedType) {
      commit('updateSelectedType', newSelectedType);
    },
    updateSelectedType2({ commit }, newSelectedType2) {
      commit('updateSelectedType2', newSelectedType2);
    },
    updateShowRouterView({ commit }, newShowRouterView) {
      commit('updateShowRouterView', newShowRouterView);
    },
  },
  // state를 기반으로 계산
  // this.$store.getters(이름, 변수);
  getters: {
    getType(state) {
      return state.type;
    },
    getName0(state) {
      return state.name0;
    },
    getName1(state) {
      return state.name1;
    },
    getName2(state) {
      return state.name2;
    },
    getDate1(state) {
      return state.date1;
    },
    getDate1Type(state) {
      return state.date1Type;
    },
    getDate2(state) {
      return state.date2;
    },
    getDate2Type(state) {
      return state.date2Type;
    },
    getSelectedType(state) {
      return state.selectedType;
    },
    getSelectedType2(state) {
      return state.selectedType2;
    },
    getShowRouterView(state) {
      return state.showRouterView;
    },
  },
});
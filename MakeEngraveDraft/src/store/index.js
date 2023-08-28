import { createStore } from 'vuex'

// 초기 상태 정의
const initialEngraveType = '일반';
const initialName1 = '';
const initialName2 = '';
const initialName3 = '';
const initialDate1 = '';
const initialDate1Type = '양력';
const initialDate2 = '';
const initialDate2Type = '양력';
const initialReligion = '';
const initialSelectedType = '일반';
const initialSelectedType2 = '없음';
const initialSelectedUrnType = '';
const initialSelectedTabletType = '';
const initialShowRouterView = true;

export const store = createStore({
  // 저장소
  state: {
    // 각인종류 (일반, 기독교, 천주교, ...)
    engraveType: initialEngraveType,
    // 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
    selectedType: initialSelectedType,
    // 고인 성함
    name1: initialName1,
    // 직분, 세례명, 법명
    name2: initialName2,
    // 생년월일
    date1: initialDate1,
    date1Type: initialDate1Type,
    // 사망월일
    date2: initialDate2,
    date2Type: initialDate2Type,
    // 종교
    religion: initialReligion,
 
    // 선택된 유골 종류
    selectedUrnType: initialSelectedUrnType,

    // 위패 보기
    showRouterView: initialShowRouterView,
    // 위패 종류 (일반, 본관, 문구)
    selectedType2: initialSelectedType2,
    // 위패 내용
    name3: initialName3,

    // 선택된 위패 종류
    selectedTabletType: initialSelectedTabletType,
  },
  // state 상대 변경
  // this.$store.commit(이름, 변수);
  mutations: {
    updateEngraveType(state, newEngraveType) {
      state.engraveType = newEngraveType;
    },
    updateName1(state, newName1) {
      state.name1 = newName1;
    },
    updateName2(state, newName2) {
      state.name2 = newName2;
    },
    updateName3(state, newName3) {
      state.name3 = newName3;
      localStorage.setItem('vuexState', JSON.stringify(state));
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
    updateReligion(state, newReligion) {
      state.religion = newReligion;
    },
    updateSelectedUrnType(state, newSelectedUrnType) {
      state.selectedUrnType = newSelectedUrnType;
    },
    updateSelectedTabletType(state, newSelectedTabletType) {
      state.selectedTabletType = newSelectedTabletType;
    },
    updateSelectedType(state, newSelectedType) {
      state.selectedType = newSelectedType;
    },
    updateSelectedType2(state, newSelectedType2) {
      state.selectedType2 = newSelectedType2;
    },
    updateShowRouterView(state, newShowRouterView) {
      state.showRouterView = newShowRouterView;
      localStorage.setItem('vuexState', JSON.stringify(state));
    },
    toggleRouterView(state) {
      state.showRouterView = !state.showRouterView;
      localStorage.setItem('vuexState', JSON.stringify(state));
    },
    // 홈으로 돌아가기 동작에 대한 mutation
    resetState(state) {
      state.engraveType = initialEngraveType;
      state.name1 = initialName1;
      state.name2 = initialName2;
      state.name3 = initialName3;
      state.date1 = initialDate1;
      state.date1Type = initialDate1Type;
      state.date2 = initialDate2;
      state.date2Type = initialDate2Type;
      state.religion = initialReligion;
      state.selectedType = initialSelectedType;
      state.selectedType2 = initialSelectedType2;
      state.selectedUrnType = initialSelectedUrnType,
      state.selectedTabletType = initialSelectedTabletType,
      state.showRouterView = initialShowRouterView;
      localStorage.setItem('vuexState', JSON.stringify(state));
    },
    resetState2(state) {
      state.name3 = initialName3;
      state.selectedType2 = initialSelectedType2;
      state.showRouterView = initialShowRouterView;
      localStorage.removeItem('vuexState'); // LocalStorage에서 상태 제거
    },
  },
  // 상태 변이 (복잡한 연산)
  // this.$store.dispatch(이름, 변수);
  actions: {
    updateEngraveType({ commit }, newEngraveType) {
      commit('updateEngraveType', newEngraveType);
    },
    updateName1({ commit }, newName1) {
      commit('updateName1', newName1);
    },
    updateName2({ commit }, newName2) {
      commit('updateName2', newName2);
    },
    updateName3({ commit }, newName3) {
      commit('updateName3', newName3);
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
    updateReligion({ commit }, newReligion) {
      commit('updateReligion', newReligion);
    },
    updateSelectedUrnType({ commit }, newSelectedUrnType) {
      commit('updateSelectedUrnType', newSelectedUrnType);
    },
    updateSelectedTabletType({ commit }, newSelectedTabletType) {
      commit('updateSelectedTabletType', newSelectedTabletType);
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
  // this.$store.getters.이름;
  getters: {
    getEngraveType(state) {
      return state.engraveType;
    },
    getName1(state) {
      return state.name1;
    },
    getName2(state) {
      return state.name2;
    },
    getName3(state) {
      return state.name3;
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
    getReligion(state) {
      return state.religion;
    },
    getSelectedUrnType(state) {
      return state.selectedUrnType;
    },
    getSelectedTabletType(state) {
      return state.selectedTabletType;
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
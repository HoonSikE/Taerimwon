import { createStore } from 'vuex'

// 초기 상태 정의
// const initial = '';
// const initialLeaderName = '';
// const initialLeaderPhone = '010-';
// const initialLeaderDepartment = '';
const initialClientName = '';
const initialClientPhone = '010-';
const initialSelectedLocation = '화장장';
const initialCremationArea = '';
const initialCremationTime = '';
const initialFuneralName = '';
const initialFuneralNumber = '';
const initialFuneralTime = '';
const initialburialName = '';
const initialburialTime = '';
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
const initialNote = '';

export const store = createStore({
  // 저장소
  state: {
    // 팀장 정보
    // leaderName: initialLeaderName,
    // leaderPhone: initialLeaderPhone,
    // leaderDepartment: initialLeaderDepartment,
    // 상주 정보
    clientName: initialClientName,
    clientPhone: initialClientPhone,
    // 발주 장소 입력
    selectedLocation: initialSelectedLocation,
    // 화장장
    cremationArea: initialCremationArea,
    cremationTime: initialCremationTime,
    // 장례식장
    funeralName: initialFuneralName,
    funeralNumber: initialFuneralNumber,
    funeralTime: initialFuneralTime,
    // 장지
    burialName: initialburialName,
    burialTime: initialburialTime,
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
    // 합골 추가 정보
    boneSex: '',
    boneName1: initialName1,
    boneName2: initialName2,
    boneDate1: initialDate1,
    boneDate1Type: initialDate1Type,
    boneDate2: initialDate2,
    boneDate2Type: initialDate2Type,
    boneReligion: initialReligion,
    // 위패 보기
    showRouterView: initialShowRouterView,
    // 위패 종류 (일반, 본관, 문구)
    selectedType2: initialSelectedType2,
    // 위패 내용
    name3: initialName3,
    // 선택된 위패 종류
    selectedTabletType: initialSelectedTabletType,
    // 특이사항
    note: initialNote,
  },
  // state 상대 변경
  // this.$store.commit(이름, 변수);
  mutations: {
    // update(state, new) {
    //   state. = new;
    // },
    // updateLeaderName(state, newLeaderName) {
    //   state.leaderName = newLeaderName;
    // },
    // updateLeaderPhone(state, newLeaderPhone) {
    //   state.leaderPhone = newLeaderPhone;
    // },
    // updateLeaderDepartment(state, newLeaderDepartment) {
    //   state.leaderDepartment = newLeaderDepartment;
    // },
    updateClientName(state, newClientName) {
      state.clientName = newClientName;
    },
    updateClientPhone(state, newClientPhone) {
      state.clientPhone = newClientPhone;
    },
    updateSelectedLocation(state, newSelectedLocation) {
      state.selectedLocation = newSelectedLocation;
    },
    updateCremationArea(state, newCremationArea) {
      state.cremationArea = newCremationArea;
    },
    updateCremationTime(state, newCremationTime) {
      state.cremationTime = newCremationTime;
    },
    updateFuneralName(state, newFuneralName) {
      state.funeralName = newFuneralName;
    },
    updateFuneralNumber(state, newFuneralNumber) {
      state.funeralNumber = newFuneralNumber;
    },
    updateFuneralTime(state, newFuneralTime) {
      state.funeralTime = newFuneralTime;
    },
    updateBurialName(state, newBurialName) {
      state.burialName = newBurialName;
    },
    updateBurialTime(state, newBurialTime) {
      state.burialTime = newBurialTime;
    },
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
    updateBoneSex(state, newBoneSex) {
      state.boneSex = newBoneSex;
    },
    updateBoneName1(state, newBoneName1) {
      state.boneName1 = newBoneName1;
    },
    updateBoneName2(state, newBoneName2) {
      state.boneName2 = newBoneName2;
    },
    updateBoneDate1(state, newBoneDate1) {
      state.boneDate1 = newBoneDate1;
    },
    updateBoneDate1Type(state, newBoneDate1Type) {
      state.boneDate1Type = newBoneDate1Type;
    },
    updateBoneDate2(state, newBoneDate2) {
      state.boneDate2 = newBoneDate2;
    },
    updateBoneDate2Type(state, newBoneDate2Type) {
      state.boneDate2Type = newBoneDate2Type;
    },
    updateBoneReligion(state, newBoneReligion) {
      state.BoneReligion = newBoneReligion;
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
    updateNote(state, newNote) {
      state.note = newNote;
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
      // state.leaderName = initialLeaderName,
      // state.leaderPhone = initialLeaderPhone,
      // state.leaderDepartment = initialLeaderDepartment,
      state.clientName = initialClientName,
      state.clientPhone = initialClientPhone,
      state.selectedLocation = initialSelectedLocation,
      state.cremationArea = initialCremationArea,
      state.cremationTime = initialCremationTime,
      state.funeralName = initialFuneralName,
      state.funeralNumber = initialFuneralNumber,
      state.funeralTime = initialFuneralTime,
      state.burialName = initialburialName,
      state.burialTime = initialburialTime,
      state.engraveType = initialEngraveType;
      state.boneSex = '',
      state.boneName1 = initialName1,
      state.boneName2 = initialName2,
      state.boneDate1 = initialDate1,
      state.boneDate1Type = initialDate1Type,
      state.boneDate2 = initialDate2,
      state.boneDate2Type = initialDate2Type,
      state.boneReligion = initialReligion,
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
      state.note = initialNote;
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
    // update({ commit }, new) {
    //   commit('update', new);
    // },
    // updateLeaderName({ commit }, newLeaderName) {
    //   commit('updateLeaderName', newLeaderName);
    // },
    // updateLeaderPhone({ commit }, newLeaderPhone) {
    //   commit('updateLeaderPhone', newLeaderPhone);
    // },
    // updateLeaderDepartment({ commit }, newLeaderDepartment) {
    //   commit('updateLeaderDepartment', newLeaderDepartment);
    // },
    // updateClientName({ commit }, newClientName) {
    //   commit('updateClientName', newClientName);
    // },
    // updateClientPhone({ commit }, newClientPhone) {
    //   commit('updateClientPhone', newClientPhone);
    // },
    // updateSelectedLocation({ commit }, newSelectedLocation) {
    //   commit('updateSelectedLocation', newSelectedLocation);
    // },
    // updateEngraveType({ commit }, newEngraveType) {
    //   commit('updateEngraveType', newEngraveType);
    // },
    // updateName1({ commit }, newName1) {
    //   commit('updateName1', newName1);
    // },
    // updateName2({ commit }, newName2) {
    //   commit('updateName2', newName2);
    // },
    // updateName3({ commit }, newName3) {
    //   commit('updateName3', newName3);
    // },
    // updateDate1({ commit }, newDate1) {
    //   commit('updateDate1', newDate1);
    // },
    // updateDate1Type({ commit }, newDate1Type) {
    //   commit('updateDate1Type', newDate1Type);
    // },
    // updateDate2({ commit }, newDate2) {
    //   commit('updateDate2', newDate2);
    // },
    // updateDate2Type({ commit }, newDate2Type) {
    //   commit('updateDate2Type', newDate2Type);
    // },
    // updateReligion({ commit }, newReligion) {
    //   commit('updateReligion', newReligion);
    // },
    // updateSelectedUrnType({ commit }, newSelectedUrnType) {
    //   commit('updateSelectedUrnType', newSelectedUrnType);
    // },
    // updateSelectedTabletType({ commit }, newSelectedTabletType) {
    //   commit('updateSelectedTabletType', newSelectedTabletType);
    // },
    // updateSelectedType({ commit }, newSelectedType) {
    //   commit('updateSelectedType', newSelectedType);
    // },
    // updateSelectedType2({ commit }, newSelectedType2) {
    //   commit('updateSelectedType2', newSelectedType2);
    // },
    // updateShowRouterView({ commit }, newShowRouterView) {
    //   commit('updateShowRouterView', newShowRouterView);
    // },
    // updateNote({ commit }, newNote) {
    //   commit('updateNote', newNote);
    // },
  },
  // state를 기반으로 계산
  // this.$store.getters.이름;
  getters: {
    // get(state) {
    //   return state.;
    // },
    // getLeaderName(state) {
    //   return state.leaderName;
    // },
    // getLeaderPhone(state) {
    //   return state.leaderPhone;
    // },
    // getLeaderDepartment(state) {
    //   return state.leaderDepartment;
    // },
    getClientName(state) {
      return state.clientName;
    },
    getClientPhone(state) {
      return state.clientPhone;
    },
    getSelectedLocation(state) {
      return state.selectedLocation;
    },
    getCremationArea(state) {
      return state.cremationArea;
    },
    getCremationTime(state) {
      return state.cremationTime;
    },
    getFuneralName(state) {
      return state.funeralName;
    },
    getFuneralNumber(state) {
      return state.funeralNumber;
    },
    getFuneralTime(state) {
      return state.funeralTime;
    },
    getBurialName(state) {
      return state.burialName;
    },
    getBurialTime(state) {
      return state.burialTime;
    },
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
    getBoneSex(state) {
      return state.boneSex;
    },
    getBoneName1(state) {
      return state.boneName1;
    },
    getBoneName2(state) {
      return state.boneName2;
    },
    getBoneDate1(state) {
      return state.boneDate1;
    },
    getBoneDate1Type(state) {
      return state.boneDate1Type;
    },
    getBoneDate2(state) {
      return state.boneDate2;
    },
    getBoneDate2Type(state) {
      return state.boneDate2Type;
    },
    getBoneReligion(state) {
      return state.boneReligion;
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
    getNote(state) {
      return state.note;
    },
  },
});
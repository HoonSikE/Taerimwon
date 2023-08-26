import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  // 저장소
  state: {
    Data: [], // 데이터 리스트
  },
  // state 상대 변경
  mutations: {
    addItem(state, newItem) {
      state.items.push(newItem);
    },
    updateItem(state, updatedItem) {
      const index = state.items.findIndex(item => item.id === updatedItem.id);
      if (index !== -1) {
        state.items[index] = updatedItem;
      }
    },
    deleteItem(state, itemId) {
      state.items = state.items.filter(item => item.id !== itemId);
    },
  },
  // 상태 변이
  actions: {
    createItem({ commit }, newItem) {
      commit('addItem', newItem);
    },
    editItem({ commit }, updatedItem) {
      commit('updateItem', updatedItem);
    },
    removeItem({ commit }, itemId) {
      commit('deleteItem', itemId);
    },
  },
  // state를 기반으로 계산
  getters: {
    getItems: state => state.items,
    getItemById: state => id => state.items.find(item => item.id === id),
  },
});
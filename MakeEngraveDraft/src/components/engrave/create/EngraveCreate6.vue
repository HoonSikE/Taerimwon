<template>
  <div class="">
    <div>
      ● 종류
      정보 입력 ({{type}})
      <div class="link-container">
        <button
          class="link-item"
          :class="{ selected: selectedType === 'default' }"
          @click="selectedType = 'default'"
        >
          <img src="../../../assets/images/engrave/example/묘법.png" width="80" height="100" alt="기독교(직분x)">
          <span class="selectText">[기본]</span>
        </button>
      </div>
      <div>
        고인성함 입력<br/>
        <input v-model="name" type="text" :placeholder="defaultNamePlaceholder"/>
      </div>
      <div>
        출생일 선택<br/>
        <input v-model="date1" type="date" placeholder="1900-01-01"/>
      </div>
      <div>
        사망일 선택<br/>
        <input v-model="date2" type="date"/>
      </div>
    </div>
    <hr>
    <div>
      <div>
        각인종류<br/>
        일반
      </div>
      <div>
        <router-link :to="'/engrave/result?' + 'name=' + encodedName + '&date1=' + date1 + '&date2='+ date2">각인 주문하기</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: '묘법',
      selectedType: 'default', // 초기 선택 타입 설정
      name: '',
      date1: '1900-01-01',
      date2: new Date().toISOString().substr(0, 10),
      inputValue: '', // 입력값을 저장할 데이터 속성
    };
  },
  computed: {
    encodedName() {
      const trimmedName = this.name.trim();
      return trimmedName === '' ? '홍길동' : encodeURIComponent(trimmedName);
    },
    defaultNamePlaceholder() {
      // 여기서 기본 placeholder 값을 설정합니다
      return '홍길동';
    },
  }
};
</script>

<style>
/* @media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
} */
</style>
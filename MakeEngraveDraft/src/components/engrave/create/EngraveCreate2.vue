<template>
  <div class="">
    <div>
      정보 입력 ({{type}})
      <div>
        고인성함 입력<br/>
        <input v-model="name" type="text" placeholder="고인성함"/>
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
        <router-link :to="'/engrave/result?' + 'name=' + name + '&date1=' + date1 + '&date2='+ date2">각인 주문하기</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: '기독교',
      name: '',
      date1: '1900-01-01',
      date2: new Date().toISOString().substr(0, 10),
      inputValue: '', // 입력값을 저장할 데이터 속성
    };
  },
};
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
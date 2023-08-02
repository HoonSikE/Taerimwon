<template>
  <div class="engrave">
    <h1>명지사 각인 주문하기</h1>
    <div>
      각인 안내<br/>
      <span>
        <input type="radio" name="radio1" checked>
        각인[일반]
      </span>
      <span>
        <input type="radio" name="radio2">
        각인[본관]
      </span>
      <span>
        <input type="radio" name="radio3">
        각인[본관(位)]
      </span>
      <span>
        <input type="radio" name="radio4">
        각인[기독교]
      </span>
      <span>
        <input type="radio" name="radio5">
        각인[기독교(位)]
      </span>
      <span>
        <input type="radio" name="radio6">
        각인[천주교]
      </span>
      <span>
        <input type="radio" name="radio7">
        각인[천주교(位)]
      </span>
    </div>
    <hr>
    <div>
      정보 입력
      <div>
        고인성함 입력<br/>
        <input type="text" name="name" v-bind="name" placeholder="고인성함"/>
      </div>
      <div>
        출생일 선택<br/>
        <input type="date" name="date1" v-bind="date1"/>
      </div>
      <div>
        사망일 선택<br/>
        <input type="date" name="date2" v-bind="date2"/>
      </div>
      
    </div>
    <hr>
    <div>
      <div>
        각인종류<br/>
        일반
      </div>
      <div>
        <RouterLink to="/engrave/result">각인 주문하기</RouterLink><br/>
        <input type="button" value="각인 주문하기"/>
      </div>
    </div>
  </div>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
<template>
  <div class="engrave">
    <h1>명지사 각인 주문하기</h1>
    <div>
      각인 안내<br/>
      <span>
        <input type="radio" name="radio1" checked>
        <RouterLink to="/engrave/create/1">각인[일반]</RouterLink><br/>
      </span>
      <span>
        <input type="radio" name="radio2">
        <RouterLink to="/engrave/create/2">각인[본관]</RouterLink><br/>
      </span>
      <span>
        <input type="radio" name="radio3">
        <RouterLink to="/engrave/create/2">각인[본관(位)]</RouterLink><br/>
      </span>
      <span>
        <input type="radio" name="radio4">
        <label>
        <RouterLink to="/engrave/create/3">각인[기독교]</RouterLink><br/>
        </label>
      </span>
      <span>
        <input type="radio" name="radio5">
        <RouterLink to="/engrave/create/3">각인[기독교(位)]</RouterLink><br/>
      </span>
      <span>
        <input type="radio" name="radio6">
        <RouterLink to="/engrave/create/4">각인[천주교]</RouterLink><br/>
      </span>
      <span>
        <input type="radio" name="radio7">
        <RouterLink to="/engrave/create/4">각인[천주교(位)]</RouterLink><br/>
      </span>
    </div>
    <hr>
    <div>
      <router-view></router-view>
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
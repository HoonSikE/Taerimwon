<template>
  <div class="">
    <h1>명지사 각인 주문접수</h1>
    <div>
      각인
      <hr>
      <div>
        <span>
          <img src="http://" alt="사진"/>
        </span>
        <br/>
        <span>
          각인종류<br/>
          각인 [일반]<br/>
          <!-- <RouterLink to="/engrave/sample">각인시안 미리보기</RouterLink><br/> -->
        <router-link :to="'/engrave/sample?' + 'name=' + $route.query.name + '&date1=' + $route.query.date1 + '&date2='+ $route.query.date2">각인시안 미리보기</router-link>
        </span>
      </div>
    </div>
    <div>
      <!-- <div>
        {{$route.query.name}}
        {{$route.query.date1}}
        {{$route.query.date2}}
      </div> -->
      <!-- <router-link :to="'/engrave/sample?' + 'name=' + $route.query.name + '&date1=' + $route.query.date1 + '&date2='+ $route.query.date2">각인시안 미리보기</router-link> -->
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

<template>
  <div class="engrave">
    <div>
      정보 입력 (천주교)
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
<template>
  <div class="">
    <span>
</span>
    <h1>명지사 각인 주문하기</h1>
    각인 안내<br/>
    <div class="container">
      <!-- <ul>
        <li>
          <div>
            <img src="../../assets/images/example/일반.png" width="80" height="100" alt="일반">
          </div>
          <div>
            <RouterLink to="/engrave/create/1">각인[일반]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1" checked>
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/기독교.png" width="80" height="100" alt="기독교">
          </div>
          <div>
            <RouterLink to="/engrave/create/2">각인[기독교]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/불교.png" width="80" height="100" alt="불교">
          </div>
          <div>
            <RouterLink to="/engrave/create/3">각인[불교]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/천주교.png" width="80" height="100" alt="천주교">
          </div>
          <div>
            <RouterLink to="/engrave/create/4">각인[천주교]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/불교(법명).png" width="80" height="100" alt="불교(법명)">
          </div>
          <div>
            <RouterLink to="/engrave/create/3">각인[불교(법명)]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/기독교(직분x).png" width="80" height="100" alt="기독교(직분x)">
          </div>
          <div>
            <RouterLink to="/engrave/create/2">각인[기독교(직분x)]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/천주교(세례명x).png" width="80" height="100" alt="천주교(세례명x)">
          </div>
          <div>
            <RouterLink to="/engrave/create/4">각인[천주교(세례명x)]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/sgi.png" width="80" height="100" alt="SGI">
          </div>
          <div>
            <RouterLink to="/engrave/create/5">각인[SGI]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
        <li>
          <div>
            <img src="../../assets/images/example/묘법.png" width="80" height="100" alt="묘법">
          </div>
          <div>
            <RouterLink to="/engrave/create/6">각인[묘법]</RouterLink>
          </div>
          <div>
            <input type="radio" name="radio1">
          </div>
        </li>
      </ul> -->


      <span class="span1">
        <img src="../../assets/images/example/일반.png" width="80" height="100" alt="일반">
        <RouterLink to="/engrave/create/1">각인[일반]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/일반(형제).png" width="80" height="100" alt="일반/형제">
        <RouterLink to="/engrave/create/1">각인[일반(형제)]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/기독교.png" width="80" height="100" alt="기독교">
        <RouterLink to="/engrave/create/2">각인[기독교]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/불교.png" width="80" height="100" alt="불교">
        <RouterLink to="/engrave/create/3">각인[불교]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/천주교.png" width="80" height="100" alt="천주교">
        <RouterLink to="/engrave/create/4">각인[천주교]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/불교(법명).png" width="80" height="100" alt="불교(법명)">
        <RouterLink to="/engrave/create/3">각인[불교(법명)]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/기독교(직분x).png" width="80" height="100" alt="기독교(직분x)">
        <RouterLink to="/engrave/create/2">각인[기독교(직분x)]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/천주교(세례명x).png" width="80" height="100" alt="천주교(세례명x)">
        <RouterLink to="/engrave/create/4">각인[천주교(세례명x)]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/sgi.png" width="80" height="100" alt="SGI">
        <RouterLink to="/engrave/create/5">각인[SGI]</RouterLink>
        <input type="radio" name="radio1">
      </span>
      <span class="span1">
        <img src="../../assets/images/example/묘법.png" width="80" height="100" alt="묘법">
        <RouterLink to="/engrave/create/6">각인[묘법]</RouterLink>
        <input type="radio" name="radio1">
      </span>
    </div>
    <hr>
    <div>
      <router-view></router-view>
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

ul {
    list-style:none;
    margin:0;
    padding:0;
}

li {
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
}

</style>
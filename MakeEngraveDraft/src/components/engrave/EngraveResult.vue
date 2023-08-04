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
        <span>
          각인종류<br/>
          각인 [일반]<br/>
          <RouterLink to="/engrave/sample">각인시안 미리보기</RouterLink><br/>
        </span>
      </div>
    </div>
    <div>
      주문 정보
      <hr>
      <div>
        <span>선택지역</span>
        <span>경기도</span>
      </div>
      <div>
        <span>소속</span>
        <span>개인</span>
      </div>
      <div>
        <span>회사명</span>
        <span>ㅇㅇ라이프</span>
      </div>
      <div>
        <span>담당의전</span>
        <span>홍길동</span>
      </div>
      <div>
        <span>담당의전팀장 연락처</span>
        <span>010-1234-5678</span>
      </div>
      <div>
        <span>화장 장소</span>
        <span>밀양</span>
      </div>
      <div>
        <span>화장 일자/ 시간</span>
        <span>ㅇㅇ</span>
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

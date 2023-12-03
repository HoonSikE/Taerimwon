<div align="center">
  <img width="90%;" src="./README/img_logo.png" />
<br/>  
<br/> 
</div>

# 🪦 태림원[Taerimwon]

- 서비스명(발주처) : 태림원[Taerimwon]
- 개발기간 : 2023.08.10 ~ 2023.12.04 (진행 중)
- 팀원 : 박한훈, OOO

<br><br>

# 🌈 서비스 소개

## 1. 태림원[Taerimwon]이란?

- **유골함/위패 각인 주문 서비스**로, 고객정보를 기반으로 유골함과 위패의 각인 예시를 제공하고 고객을 관리하는 서비스 입니다.

## 2. 서비스 제작 배경

- 포토샵, 일러스트를 통해 제공하는 기존 방식을 개선하고, 고객이 직접 정보를 입력해 주문을 할 수 있는 서비스 제작을 요청받아 제작하게 되었습니다.

<br><br>

# 👶 주요 기능

## 🎅 회원 관리

> + ### 이메일/비밀번호, 전화, SNS (Google, Facebook, Github)
> + ### 회원가입, 제공자 등록, 로그인, 마이페이지, 회원정보 수정, 비밀번호 변경, 회원탈퇴
> + ### Firebase Authentication(인증), Firestore Database(DB 관리), Stoage(사진 저장) 사용

<br>

## 1. 회원가입
   - 프로필사진, 이메일 아이디, 비밀번호, 이름, 전화번호, 집 주소, 회사 주소 입력
   - 전화번호 인증 기능 제공
   - 주소 API를 사용한 간편 등록 가능

<div align="center">
   <img src="./README/user/Join1.png" width="30%">
   <img src="./README/user/Join2.png" width="30%">
   <h3>[일반 / SNS 로그인 회원가입]</h3>
</div>

<br>

 <div align="center">
   <img src="./README/user/PhoneAuth1.png" width="24%">
   <img src="./README/user/PhoneAuth2.png" width="19.8%">
   <img src="./README/user/PhoneAuth3.png" width="24%">
   <img src="./README/user/PhoneAuth4.png" width="24%">
   <h3>[전화번호 인증]</h3>
</div>

<br>

## 2. 제공자 등록
   - 차량 사진, 차량 이름, 차량 번호 입력
   - 유저 홈 페이지에서 제공자 홈 페이지로 이동 시 정보가 없다면 출력

<div align="center">
   <img src="./README/user/JoinProvider.png" width="30%">
   <h3>[제공자 등록]</h3>
</div>

<br>

## 3. 로그인
   - 일반 로그인 기능 제공
   - SNS 로그인(Github, Facebook, Google) 기능 제공 및 프로필 받아오기

<div align="center">
   <img src="./README/user/Login1.png" width="30%">
   <img src="./README/user/Login2.png" width="30%">
   <h3>[일반 / SNS 로그인]</h3>
</div>

<br>

## 4. 마이페이지
   - 회원 정보 조회, 즐겨찾기 수정, 회원정보 수정, 비밀번호 변경, 로그아웃, 회원탈퇴, 탑승했던 택시 정보, 챗봇 기능 제공

<div align="center">
   <img src="./README/mypage/MyPage1.png" width="30%">
   <img src="./README/mypage/MyPage2.png" width="30%">
   <h3>[마이페이지]</h3>
</div>

<br>

## 5. 회원정보 수정
   - 프로필사진, 전화번호, 집 주소, 회사 주소 수정
   - 전화번호 인증 기능 제공
   - 주소 API를 사용한 간편 등록 가능

<div align="center">
   <img src="./README/mypage/UpdateUser.png" width="30%">
   <h3>[회원정보 수정]</h3>
</div>

<br>

## 6. 비밀번호 찾기
   - 가입된 Email로 비밀번호 변경 메일 전송
   - 기존 비밀번호 재확인(ReAuth) 후 비밀번호 변경

<div align="center">
   <img src="./README/user/FindPassword1.png" width="30%">
   <img src="./README/user/FindPassword2.png" width="30%">
</div>

<br>

<div align="center">
   <img src="./README/user/FindPassword3.png" width="30%">
   <img src="./README/user/FindPassword4.png" width="32.5%">
   <img src="./README/user/FindPassword5.png" width="36%">
   <h3>[Email로 비밀번호 변경]</h3>
</div>

<br>

<div align="center">
   <img src="./README/mypage/UpdatePassword1.png" width="30%">
   <img src="./README/mypage/UpdatePassword2.png" width="30%">
   <h3>[비밀번호 변경]</h3>
</div>

<br>

## 7. 회원탈퇴
   - 사용자의 실수를 방지하기 위한 Switch, Dialog 창을 통한 이중 확인
   - 회원탈퇴 시 관련된 정보 모두 삭제 (단, 알파카 이용 내용은 상대가 조회 가능.)

<div align="center">
   <img src="./README/mypage/Withdrawal1.png" width="30%">
   <img src="./README/mypage/Withdrawal2.png" width="30%">
   <h3>[회원탈퇴]</h3>
</div>

<br><br>

## ⭐ 즐겨찾기
> + ### 주소 API 적용 (주소, 위/경도 함께 등록)
> + ### Firestore Database 사용

<br>

<div align="center">
   <img src="./README/mypage/Favorites1.png" width="30%">
   <img src="./README/mypage/Favorites2.png" width="30%">
   <img src="./README/mypage/Favorites3.png" width="30%">
   <h3>[즐겨찾기 등록]</h3>
</div>

<br>

<div align="center">
   <img src="./README/mypage/Favorites4.png" width="30%">
   <img src="./README/mypage/Favorites5.png" width="30%">
   <h3>[즐겨찾기 삭제]</h3>
</div>

<br><br>

## 🏠 유저 / 제공자 화면
> + ### 유저 홈, 제공자 홈, 제공자 차량 이용자 상세 내용
> + ### 특정조건을 달성하면 등급 획득 가능 (Unrank, Bronze, Silver, Gold 등)
> + ### Firestore Database 사용

<br>

## 1. 유저
   - 유저 정보, 즐겨찾기, 자주가는 목적지 출력

<div align="center">
   <img src="./README/home/UserHome.png" width="30%">
   <h3>[유저 홈]</h3>
</div>

<br>

   - 택시 번호, 탑승 평점 출력

<div align="center">
   <img src="./README/home/BoardedTaxiList.png" width="30%">
   <h3>[이용 택시 리스트]</h3>
</div>

<br>

   - 택시 번호, 탑승 평점, 출발/목적지, 소요 시간, 운행 거리, 요금 출력, 1:1 채팅 기능 제공

<div align="center">
   <img src="./README/home/BoardedTaxiDetail.png" width="30%">
   <h3>[이용 택시 상세 내용]</h3>
</div>

<br>

## 2. 제공자
   - 회원 정보 출력, 수익 금액, 차량 번호, 평점, 운행여부, 마감시간, 이용자 정보 조회

<div align="center">
   <img src="./README/home/ProviderHome1.png" width="30%">
   <img src="./README/home/ProviderHome2.png" width="30%">
   <h3>[제공자 홈]</h3>
</div>

<br>

   - 출발 전/후 차량 내부 사진, 승차감 평점 조회, 1:1 채팅 기능 제공

<div align="center">
   <img src="./README/home/UserList1.png" width="30%">
   <img src="./README/home/UserList2.png" width="30%">
   <h3>[제공자 차량 이용자 상세 내용]</h3>
</div>

<br><br>

## 🚕 무인 택시 이용
> + ### 출발지/목적지 설정, 택시 호출, 택시 출발, 택시 도착 및 평점, 택시 탑승 전/후 점검
> + ### Naver Map API와 Kakao 지도 API를 활용해 택시 위치와 경로를 기반으로 커스텀마커, 폴리라인 식별
> + ### 경로에 따라 남은시간, 금액 식별
> + ### 탑승 전/후 평점 시스템
> + ### Firestore Database, Storage 사용

<br>

## 1. 출발지/목적지 설정

<div align="center">
   <img src="./README/taxi/StartPoint.png" width="30%">
   <h3>[출발지/목적지 설정]</h3>
</div>

<br>

## 2. 택시 호출
   - 출발지 -> 목적지 간 경로 받아옴
   - 택시 호출 후 탑승 평점 출력

<div align="center">
   <img src="./README/taxi/CallTaxi1.png" width="30%">
   <img src="./README/taxi/CallTaxi2.png" width="30%">
   <img src="./README/taxi/CallTaxi3.png" width="30%">
   <h3>[택시 호출]</h3>
</div>

<br>

## 3. 택시 출발
   - 사용자에게 도착 후 문열림, 탑승 전 전검 기능 제공

<div align="center">
   <img src="./README/taxi/StartDriving.png" width="30%">
   <h3>[택시 출발]</h3>
</div>

<br>

## 4. 택시 도착 및 평점
   - 탑승 후 평점 및 점검 기능 제공

<div align="center">
   <img src="./README/taxi/EndDriving.png" width="30%">
   <h3>[택시 도착 및 평점]</h3>
</div>

<br>

## 5. 택시 탑승 전/후 점검
   - 탐승 전/후 사진을 통한 점검 (블랙 컨슈머 방지)

<div align="center">
   <img src="./README/taxi/CheckTaxi.png" width="30%">
   <h3>[택시 탑승 전/후 점검]</h3>
</div>

<br><br>

## 💰 결제

> + ### Boot Pay API 사용

<br>

<div align="center">
   <img src="./README/payment/Payment1.png" width="25%">
   <img src="./README/payment/Payment2.png" width="25%">
   <img src="./README/payment/Payment3.png" width="25%">
   <img src="./README/payment/Payment4.png" width="20.6%">
   <h3>[Boot Pay]</h3>
</div>

<br><br>

## 💭 1:1 채팅 / 챗봇

> + ### 1:1채팅, 챗봇
> + ### Realtime Database, Firestore Database 사용

<br>

## 1. 1:1 채팅
   - 사용자의 고유 아이디를 통해 채팅

<div align="center">
   <img src="./README/chat/PersonalChat1.png" width="30%">
   <img src="./README/chat/PersonalChat2.gipngf" width="30%">
   <h3>[1:1 채팅]</h3>
</div>

<br>

## 2. 챗봇
   - 챗봇을 이용해 사용자에게 알파카에 대한 시스템 안내 및 고객센터 기능 제공

<div align="center">
   <img src="./README/chat/ChatBot1.png" width="30%">
   <img src="./README/chat/ChatBot2.png" width="30%">
   <h3>[챗봇]</h3>
</div>

<br><br>

# 🪦 '태림원'을 이용하면 일어나는 기대효과!

 - 장점
    - 각인을 발주자가 만들 필요없이 자동으로 생성이 가능하다.
 - 단점
    - 앱을 다운로드해야 한다.
    - 지속적인 유지보수(업데이트, 수정)가 필요하다.

<br><br>

# 🏃 향후 계획

1. 안드로이드 앱 출시
2. iOS 앱 출시
3. 주문 및 블랙리스트 관리 개선

<br><br>

# 💻 기술 스택

<div align="center">
   <img src="./README/img_technical_stack.png" width="95%">
</div>

<br>

> + ### Android : **Android, Kotlin, MVVM 디자인 패턴, Hilt 라이브러리**
> + ### iOS : **iOS, swift**
> + ### API : 
> + ### Database : **Firebase** (Auth, Reatime Database, Firestore Database, Storage)
> + ### 기획 : **Figma**
> + ### 협업 : **GitLab**

<br><br>

# 👨‍👩‍👧‍👦 팀원 역할

<table>
  <tr>
    <td>팀원</td>
    <td>직무</td>
    <td>주요 업무</td>
    <td>한 줄 소감</td>
  </tr>
  <tr>
    <td rowspan="4">박한훈 😆</td>
    <td>공통</td>
    <td>- 기획 : UI/UX(Kakao Oven/**Figma**), DB설계, 기능명세서<br>- 협업 : 코드 컨벤션 구성, **Git**<br>- 기술: **Firebase**</td>
    <td rowspan="4">내용</td>
  </tr><tr>
    <td>Web</td>
    <td>내용</td>
  </tr>
  <tr>
    <td>Android</td>
    <td>**- 주요 담당 페이지 : 회원 관리(문자/SNS), 마이페이지, 회원정보 출력, 결제, 탑승택시 목록, 즐겨찾기, 차량 평가, 1;1 채팅, 챗봇**<br>- 기술: 프로젝트 기본 틀 구성, 이미지 업로드, 기능별 자료 추출, apk 추출
    기술 : Android(MVVM/Hilt), Kotlin, nav_graph 설계
    <br>- 기타 : ReadMe 작성, 포팅메뉴얼</td>
  </tr><tr>
    <td>iOS</td>
    <td>내용</td>
  </tr>
</table>

# 📚 산출물

# 
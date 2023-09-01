<template>
  <div class="app">
    <h2>주문 관리</h2>
    <table>
      <thead>
        <tr>
          <th>발주자 정보</th>
          <th>고인 정보</th>
          <th>등록 일자</th>
          <!-- 다른 열 제목 추가 -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="(order, index) in orders" :key="index" @click="showOrderDetails(order)">
          <td>
            <div>발주자 명: {{ order.leaderName }}</div>
            <div>발주자 전화번호: {{ order.leaderPhone }}</div>
          </td>
          <td>
            <div>상주 이름: {{ order.clientName }}</div>
            <div>상주 전화번호: {{ order.clientPhone }}</div>
            <div>고인명: {{ order.name1 }}</div>
          </td>
          <td>
            <div>{{ order.currentDate.toDate().toLocaleString('ko-KR') }}</div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <hr>
  <div class="app">
    <div v-if="selectedOrder">
      <h2>상세 정보</h2>
      <div>발주자 이름: {{ selectedOrder.leaderName }}</div>
      <div>발주자 전화번호: {{ selectedOrder.leaderPhone }}</div>
      <!-- 다른 발주자 정보 및 상세 정보 필드를 여기에 추가하세요 -->
      <div>
        <div v-if="selectedOrder.selectedLocation == '화장장'">
          화장장: {{ selectedOrder.cremationArea }}<br>
          화장시간: {{ selectedOrder.cremationTime }}<br>
        </div>
        <div v-else-if="selectedOrder.selectedLocation == '장례식장'">
          장례식장 명: {{ selectedOrder.cremationArea }}<br>
          호수: {{ selectedOrder.funeralNumber }}<br>
          함 도착시간: {{ selectedOrder.funeralTime }}<br>
        </div>
        <div v-else-if="selectedOrder.selectedLocation == '장지'">
          장지명: {{ selectedOrder.burialName }}<br>
          함 도착시간: {{ selectedOrder.burialTime }}<br>
        </div>
        <hr>
        상주명: {{ clientName }}<br>
        상주번호: {{ clientPhone }}<br>
        <hr>
        고인명: {{selectedOrder.name1}}<br>
        생년월일: {{selectedOrder.date1}} {{selectedOrder.date1Type}}<br>
        사망월일: {{selectedOrder.date2}} {{selectedOrder.date2Type}}<br>
        <div v-if="selectedOrder.engraveType != '일반' && selectedOrder.engraveType != 'SGI' && selectedOrder.engraveType != '묘법'">
          종교: {{selectedOrder.religion }}
        </div>
        <div v-if="selectedOrder.selectedType === '직분' || selectedOrder.selectedType === '법명' || selectedOrder.selectedType === '세례명'">
          {{selectedOrder.selectedType}}명: {{selectedOrder.name2}}<br>
        </div>
        <hr>
        유골함 각인 종류: {{selectedOrder.engraveType}} [{{selectedOrder.selectedType}}]<br>
        유골함 종류: {{ selectedOrder.selectedUrnType }}<br>
        <hr>
        <div v-if="selectedOrder.selectedUrnType.startsWith('합골')">
          합골 추가 정보<br>
           - 성별: {{ selectedOrder.boneSex }}<br>
           - 고인명: {{ selectedOrder.boneName1 }}<br>

           <div v-if="selectedOrder.selectedType === '직분' || selectedOrder.selectedType === '법명' || selectedOrder.selectedType === '세례명'">
            - {{ selectedOrder.selectedType }}: {{ selectedOrder.boneName2 }}<br>
          </div>
           - 생년월일: {{ selectedOrder.boneDate1 }} {{ selectedOrder.boneDate1Type }}<br>
           - 사망월일: {{ selectedOrder.boneDate2 }} {{ selectedOrder.boneDate2Type }}<br>
          <div v-if="selectedOrder.engraveType !== '일반' && selectedOrder.engraveType !== 'SGI' && selectedOrder.engraveType !== '묘법'">
            - 종교: {{ selectedOrder.boneReligion }}<br>
          </div>
           - 유골함 각인 종류: {{selectedOrder.boneEngraveType}} [{{selectedOrder.boneSelectedType}}]<br>
          <hr>
        </div>
        <div v-if="selectedOrder.selectedType2 === '없음'">
          위패 종류: 없음<br>
        </div>
        <div v-else>
          <div v-if="!selectedOrder.selectedTabletType.endsWith('(사진)')">
            위패 각인 종류: {{selectedOrder.selectedType2}}<br>
            위패 종류: {{ selectedOrder.selectedTabletType }}<br>
            <div v-if="selectedOrder.name3 !== '없음' && selectedOrder.selectedType2 != '문구'">
              위패 내용: {{selectedOrder.name3}}<br>
            </div>
            <div v-if="selectedOrder.selectedType2 != '문구'">
              <div v-if="selectedOrder.selectedType === '직분' || selectedOrder.selectedType === '법명' || selectedOrder.selectedType === '세례명'">
                {{selectedOrder.selectedType}}: {{selectedOrder.name2}}<br>
              </div>
              고인 성함: {{this.name1}}<br>
            </div>
            <div v-if="selectedOrder.selectedType2 === '문구'">
              문구 내용<br>
              <div v-if="name3Type === 'one' || name3Type === 'two' || name3Type === 'three'">
                - 문구1: {{ selectedOrder.name3_1 }}
              </div>
              <div v-if="name3Type === 'two' || name3Type === 'three'">
                - 문구2: {{selectedOrder. name3_2 }}
              </div>
              <div v-if="name3Type === 'three'">
                - 문구3: {{ selectedOrder.name3_3 }}
              </div>
           </div>
          </div>
          <div v-else>
            위패 종류: {{ selectedOrder.selectedTabletType }}<br>
            <div class="text-align-center">
              <img v-if="imageUrl" :src="imageUrl" alt="Uploaded Image" class="selectedOrder_Image_Container" style="width: 145px; height: auto;"/>
              <div v-if="imageUrl">
                <!-- 사진 다운로드 버튼 -->
                <button class="download-button" @click="downloadContainer">사진 다운로드</button>
              </div>
            </div>
          </div>
        </div>
        <hr>
        발주자명: {{ selectedOrder.leaderName }}<br>
        전화번호: {{ selectedOrder.leaderPhone }}<br>
        소속: {{ selectedOrder.leaderDepartment }}<br>
        <hr>
        특이사항: {{ selectedOrder.note }}<br>
        <hr>
      </div>
    </div>
  </div>
</template>

<script>
import html2canvas from 'html2canvas';
import { getFirestore, collection, query, orderBy, getDocs } from 'firebase/firestore';
import { getStorage, ref, getDownloadURL } from "firebase/storage";

export default {
  data() {
    return {
      orders: [],           // 게시물 목록
      selectedOrder: null,  // 선택된 게시물 세부 정보
      imageUrl: null,
    };
  },
  mounted() {
    // 처음 화면이 바뀌었을 때 최상단으로 스크롤
    window.scrollTo(0, 0);
  },
  async created() {
    // Firestore 초기화
    const firestore = getFirestore();
    const ordersCollection = collection(firestore, 'orders');

    // currentDate와 leaderName을 기준으로 쿼리 작성
    const q = query(ordersCollection);
    // const q = query(ordersCollection, orderBy('currentDate'), orderBy('leaderName'));

    try {
      // 쿼리 실행 및 데이터 가져오기
      const querySnapshot = await getDocs(q);
      const orders = [];
      querySnapshot.forEach((doc) => {
        // 각 문서에서 데이터를 가져와서 orders 배열에 추가
        orders.push(doc.data());
      });

      // orders 배열 업데이트
      this.orders = orders;
      console.log('데이터 가져오기 성공:', orders);
    } catch (error) {
      console.error('데이터 가져오기 오류:', error);
    }
  },
  methods: {
    async showOrderDetails(order) {

      // 클릭한 게시물의 세부 정보 표시
      this.selectedOrder = order;

      if(this.selectedOrder.selectedTabletType.endsWith('(사진)')) {
        try {
          // Firebase Storage에서 이미지 다운로드
          const storage = getStorage();
          // 이미지 경로 설정
          const imageRef = ref(storage, this.selectedOrder.imageUrl);
          const imageUrl = await getDownloadURL(imageRef);

          // 이미지 URL을 Vue 데이터에 할당하여 표시
          this.imageUrl = imageUrl;
          console.log('이미지 다운로드 성공:', imageUrl);
        } catch (error) {
          console.error('이미지 다운로드 오류:', error);
        }
      }
    },
    async downloadContainer() {
      try {
        // 이미지를 <canvas>에 그림
        const img = new Image();
        img.crossOrigin = 'anonymous'; // 크로스 도메인 요청 허용
        img.src = this.imageUrl;

        img.onload = async () => {
          const canvas = document.createElement('canvas');
          canvas.width = img.width;
          canvas.height = img.height;
          const ctx = canvas.getContext('2d');
          ctx.drawImage(img, 0, 0, img.width, img.height);

          // 이미지 데이터를 URL로 변환
          const imageDataURL = canvas.toDataURL('image/png');

          // 이미지 다운로드 링크 생성
          const link = document.createElement('a');
          link.href = imageDataURL;
          link.download = this.selectedOrder.name1 + '.png'; // 다운로드될 이미지 파일의 이름 설정
          link.target = '_blank'; // 새 창에서 열리도록 설정
          link.click(); // 클릭 이벤트 실행
        };
      } catch (error) {
        console.error('이미지 다운로드 오류:', error);
      }
    },
  },
};
</script>

<style>

</style>

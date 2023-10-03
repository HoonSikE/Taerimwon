package com.example.taerimwon.utils.constant

/**위패 사진**/
const val TABLETIMAGEURI = "tabletImageUri"
/**유저 ID**/
const val UID = "uid"
const val USERTEL = "userTel"
const val AUTHENTICATED = "authenticated"

/**발주자 정보**/
const val LEADERNAME = "leaderName"
const val LEADERTEL = "leaderTel"
const val LEADERDEPARTMENT = "leaderDepartment"
const val SAVEINFO = "saveInfo"

/**주문 정보**/
// 상주 정보
const val ClIENTNAME = "clientName"
const val CLIENTTEL = "clientTel"
// 발주 장소
const val SELECTEDLOCATION = "selectedLocation"
// 화장장
const val CREMATIONAREA = "cremationArea"
const val CREMATIONTIME = "cremationTime"
// 장례식장
const val FUNERALNAME = "funeralName"
const val FUNERALNUMBER= "funeralNumber"
const val FUNERALTIME = "funeralTime"
// 장지
const val BURIALNAME = "burialName"
const val BURIALTIME = "burialTime"
// 각인종류 (일반, 기독교, 천주교, ...)
const val ENGRAVETYPE = "engraveType"
const val ENGRAVETYPEPOSITION = "engraveTypePosition"
// 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
const val ENGRAVETYPE2 = "engraveType2"
const val ENGRAVETYPE2POSITION = "engraveType2Position"
// 고인 성함
const val NAME1 = "name1"
// 직분, 세례명, 법명
const val NAME2 = "name2"
// 생년월일
const val DATE1 = "date1"
const val DATE1TYPE = "date1Type"
// 사망월일
const val DATE2 = "date2"
const val DATE2TYPE = "date2Type"
// 종교
const val RELIGION = "religion"
// 선택된 유골 종류
const val SELECTEDURNTYPE = "selectedUrnType"
// 합골 추가 정보
// 각인종류 (일반, 기독교, 천주교, ...)
const val BONEENGRAVETYPE = "boneEngraveType"
const val BONEENGRAVETYPEPOSITION = "boneEngraveTypePosition"
// 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
const val BONEENGRAVETYPE2 = "boneEngraveType2"
const val BONEENGRAVETYPE2POSITION = "boneEngraveType2Position"
const val BONESEX = "boneSex"
const val BONENAME1 = "boneName1"
const val BONENAME2 = "boneName2"
const val BONEDATE1 = "boneDate1"
const val BONEDATE1TYPE = "boneDate1Type"
const val BONEDATE2 = "boneDate2"
const val BONEDATE2TYPE = "boneDate2Type"
const val BONERELIGION = "boneReligion"
// 위패 보기
const val SHOWROUTERVIEW = "showRouterView"
// 위패 종류 (일반, 본관, 문구)
const val TABLETTYPE = "tabletType"
const val TABLETTYPEPOSITION = "tabletTypePosition"
// 위패 내용
const val NAME3 = "name3"
// 문구
const val NAME3TYPE = "name3Type"
const val NAME3_1 = "name3_1"
const val NAME3_2 = "name3_2"
const val NAME3_3 = "name3_3"
// 선택된 위패 종류
const val SELECTEDTABLETTYPE = "selectedTabletType"
// 특이사항
const val NOTE = "note"
// 사진 저장
// 선택된 파일을 저장할 변수
const val SELECTEDFILE = "selectedFile"
// 이미지 URL을 저장할 변수
const val IMAGEURL = "imageUrl"

object FireStoreCollection{
    val USER = "User"
    val BLACKLIST = "BlackList"
}

object FireStoreDocumentField {
    val DATE = "date"
    val USER_ID = "user_id"
}

object FirebaseStorageConstants {
    val ROOT_DIRECTORY = "app"
    val NOTE_IMAGES = "note"
}

object SharedPrefConstants {
    val LOCAL_SHARED_PREF = "local_shared_pref"
    val USER_SESSION = "user_session"
}
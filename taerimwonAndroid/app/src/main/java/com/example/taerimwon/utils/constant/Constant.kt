package com.example.taerimwon.utils.constant

/**위패 사진**/
const val TABLETIMAGEURI = "tabletImageUri"
const val BONETABLETIMAGEURI = "boneTabletImageUri"
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
const val STARTAREA = "startArea"
// 화장장
const val CREMATIONAREA = "cremationArea"
const val CREMATIONAREA2 = "cremationArea2"
const val CREMATIONTIME = "cremationTime"
// 장례식장
const val FUNERALNAME = "funeralName"
const val FUNERALNUMBER= "funeralNumber"
const val FUNERALTIME = "funeralTime"
// 장지
const val BURIALNAME = "burialName"
const val BURIALTIME = "burialTime"
/**유골함 정보**/
// 각인종류 (일반, 기독교, 천주교, ...)
const val ENGRAVETYPE = "engraveType"
const val ENGRAVETYPEPOSITION = "engraveTypePosition"
// 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
const val ENGRAVETYPE2 = "engraveType2"
const val CHECKCATHOLIC = "checkCatholic"
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
// 선택된 유골 종류
const val SELECTEDURNTYPE = "selectedUrnType"
const val SELECTEDURNNAME = "selectedUrnName"
/**유골함 추가 정보*/
const val SELECTEDURNTYPE2 = "selectedUrnType2"
const val SELECTEDURNNAME2 = "selectedUrnName2"
/**합골 추가 정보**/
// 각인종류 (일반, 기독교, 천주교, ...)
const val BONEENGRAVETYPE = "boneEngraveType"
const val BONEENGRAVETYPEPOSITION = "boneEngraveTypePosition"
// 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
const val BONEENGRAVETYPE2 = "boneEngraveType2"
const val CHECKCATHOLIC2 = "checkCatholic2"
const val BONEENGRAVETYPE2POSITION = "boneEngraveType2Position"
const val SEX = "sex"
const val BONESEX = "boneSex"
const val BONENAME1 = "boneName1"
const val BONENAME2 = "boneName2"
const val BONEDATE1 = "boneDate1"
const val BONEDATE1TYPE = "boneDate1Type"
const val BONEDATE2 = "boneDate2"
const val BONEDATE2TYPE = "boneDate2Type"
const val RELIGION = "religion"
const val BONERELIGION = "boneReligion"
/**위패 정보**/
// 위패 종교
const val TABLETRELIGION = "tabletReligion"
// 위패 종류 (일반, 본관, 문구)
const val TABLETTYPE = "tabletType"
const val TABLETTYPEPOSITION = "tabletTypePosition"
// 위패 내용
const val NAME3 = "name3"
const val TABLETNAME3 = "tabletName3"
// 선택된 위패 종류
const val SELECTEDTABLETTYPE = "selectedTabletType"
const val SELECTEDTABLETNAME = "selectedTabletName"
const val SELECTEDTABLETNAME2 = "selectedTabletName2"
/**위패 추가 정보**/
const val TABLETSEX = "tabletSex"
const val BONETABLETSEX = "boneTabletSex"
// 위패 종교
const val BONETABLETRELIGION = "boneTabletReligion"
// 위패 종류 (일반, 본관, 문구)
const val BONETABLETTYPE = "boneTabletType"
const val BONETABLETTYPEPOSITION = "boneTabletTypePosition"
// 위패 내용
const val BONENAME3 = "boneName3"
const val BONETABLETNAME3 = "boneTabletName3"
// 선택된 위패 종류
const val BONESELECTEDTABLETTYPE = "boneSelectedTabletType"

const val TABLETEXAMPLE = "tabletExample"

/**추가주문**/
const val SELECTEDADDNAME1 = "selectedAddName1"
const val SELECTEDADDNAME2 = "selectedAddName2"
const val SELECTEDADDNAME3 = "selectedAddName3"
const val SELECTEDADDNAME4 = "selectedAddName4"
const val SELECTEDADDNAME5 = "selectedAddName5"

/**평장**/
// 선택된 평장 종류
const val SELECTEDPYEONGJANGTYPE = "selectedPyeongjangType"
const val SELECTEDPYEONGJANGNAME = "selectedPyeongjangName"
// 선택된 평장 종류2
const val SELECTEDPYEONGJANGTYPE2 = "selectedPyeongjangType2"
const val SELECTEDPYEONGJANGNAME2 = "selectedPyeongjangName2"

const val PYEONGJANGSEX = "pyeongjangSex"
const val PYEONGJANGSEX2 = "pyeongjangSex2"


/**특이사항**/
const val NOTE = "note"
/**사진 저장**/
// 선택된 파일을 저장할 변수
const val SELECTEDFILE = "selectedFile"
// 이미지 URL을 저장할 변수
const val IMAGEURL = "imageUrl"
// 선택된 파일을 저장할 변수
const val BONESELECTEDFILE = "boneSelectedFile"
// 이미지 URL을 저장할 변수
const val BONEIMAGEURL = "boneImageUrl"

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
package com.example.taerimwon.utils.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.taerimwon.utils.constant.*

class PreferenceUtil(context: Context) {
    private val user: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    var uid : String?
        get() = user.getString(UID, "")
        set(value){
            user.edit().putString(UID, value).apply()
        }
    var userTel : String?
        get() = user.getString(USERTEL, "")
        set(value){
            user.edit().putString(USERTEL, value).apply()
        }
    var authenticated : Boolean
        get() = user.getBoolean(AUTHENTICATED, false)
        set(value){
            user.edit().putBoolean(AUTHENTICATED, value).apply()
        }

    private val manager: SharedPreferences = context.getSharedPreferences("manager", Context.MODE_PRIVATE)
    // 발주자 정보
    var leaderName : String?
        get() = manager.getString(LEADERNAME, "")
        set(value){
            manager.edit().putString(LEADERNAME, value).apply()
        }
    var leaderTel : String?
        get() = manager.getString(LEADERTEL, "")
        set(value){
            manager.edit().putString(LEADERTEL, value).apply()
        }
    var leaderDepartment : String?
        get() = manager.getString(LEADERDEPARTMENT, "")
        set(value){
            manager.edit().putString(LEADERDEPARTMENT, value).apply()
        }
    var saveInfo : Boolean
        get() = manager.getBoolean(SAVEINFO, false)
        set(value){
            manager.edit().putBoolean(SAVEINFO, value).apply()
        }
//    private val urn: SharedPreferences = context.getSharedPreferences("urn", Context.MODE_PRIVATE)
//    private val tablet: SharedPreferences = context.getSharedPreferences("tablet", Context.MODE_PRIVATE)
    val order: SharedPreferences = context.getSharedPreferences("order", Context.MODE_PRIVATE)

    // 상주 정보
    var clientName : String?
        get() = order.getString(ClIENTNAME, "")
        set(value){
            order.edit().putString(ClIENTNAME, value).apply()
        }
    var clientTel : String?
        get() = order.getString(CLIENTTEL, "")
        set(value){
            order.edit().putString(CLIENTTEL, value).apply()
        }
    // 발주 장소
    var selectedLocation : String?
        get() = order.getString(SELECTEDLOCATION, "화장장")
        set(value){
            order.edit().putString(SELECTEDLOCATION, value).apply()
        }
    // 화장장
    var cremationArea : String?
        get() = order.getString(CREMATIONAREA, "서울/경기/인천")
        set(value){
            order.edit().putString(CREMATIONAREA, value).apply()
        }
    var cremationTime : String?
        get() = order.getString(CREMATIONTIME, "")
        set(value){
            order.edit().putString(CREMATIONTIME, value).apply()
        }
    // 장례식장
    var funeralName : String?
        get() = order.getString(FUNERALNAME, "")
        set(value){
            order.edit().putString(FUNERALNAME, value).apply()
        }
    var funeralNumber : String?
        get() = order.getString(FUNERALNUMBER, "")
        set(value){
            order.edit().putString(FUNERALNUMBER, value).apply()
        }
    var funeralTime : String?
        get() = order.getString(FUNERALTIME, "")
        set(value){
            order.edit().putString(FUNERALTIME, value).apply()
        }
    // 장지
    var burialName : String?
        get() = order.getString(BURIALNAME, "")
        set(value){
            order.edit().putString(BURIALNAME, value).apply()
        }
    var burialTime : String?
        get() = order.getString(BURIALTIME, "")
        set(value){
            order.edit().putString(BURIALTIME, value).apply()
        }
    // 각인종류 (일반, 기독교, 천주교, ...)
    var engraveType : String?
        get() = order.getString(ENGRAVETYPE, "일반")
        set(value){
            order.edit().putString(ENGRAVETYPE, value).apply()
        }
    var engraveTypePosition : Int
        get() = order.getInt(ENGRAVETYPEPOSITION, 0)
        set(value){
            order.edit().putInt(ENGRAVETYPEPOSITION, value).apply()
        }
    // 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
    var engraveType2 : String?
        get() = order.getString(ENGRAVETYPE2, "기본")
        set(value){
            order.edit().putString(ENGRAVETYPE2, value).apply()
        }
    var engraveType2Position : Int
        get() = order.getInt(ENGRAVETYPE2POSITION, 0)
        set(value){
            order.edit().putInt(ENGRAVETYPE2POSITION, value).apply()
        }
    // 고인 성함
    var name1 : String?
        get() = order.getString(NAME1, "")
        set(value){
            order.edit().putString(NAME1, value).apply()
        }
    // 직분, 세례명, 법명
    var name2 : String?
        get() = order.getString(NAME2, "")
        set(value){
            order.edit().putString(NAME2, value).apply()
        }
    // 생년월일
    var date1 : String?
        get() = order.getString(DATE1, "")
        set(value){
            order.edit().putString(DATE1, value).apply()
        }
    var date1Type : String?
        get() = order.getString(DATE1TYPE, "양력")
        set(value){
            order.edit().putString(DATE1TYPE, value).apply()
        }
    // 사망월일
    var date2 : String?
        get() = order.getString(DATE2, "")
        set(value){
            order.edit().putString(DATE2, value).apply()
        }
    var date2Type : String?
        get() = order.getString(DATE2TYPE, "양력")
        set(value){
            order.edit().putString(DATE2TYPE, value).apply()
        }
    // 종교
    var religion : String?
        get() = order.getString(RELIGION, "무교")
        set(value){
            order.edit().putString(RELIGION, value).apply()
        }
    // 선택된 유골 종류
    var selectedUrnType : String?
        get() = order.getString(SELECTEDURNTYPE, "선택안함")
        set(value){
            order.edit().putString(SELECTEDURNTYPE, value).apply()
        }
    // 합골 추가 정보
    // 각인종류 (일반, 기독교, 천주교, ...)
    var boneEngraveType : String?
        get() = order.getString(BONEENGRAVETYPE, "일반")
        set(value){
            order.edit().putString(BONEENGRAVETYPE, value).apply()
        }
    var boneEngraveTypePosition : Int
        get() = order.getInt(BONEENGRAVETYPEPOSITION, 0)
        set(value){
            order.edit().putInt(BONEENGRAVETYPEPOSITION, value).apply()
        }
    // 각인 상세 종류 (일반, 형제, 직분, 기독, ...)
    var boneEngraveType2 : String?
        get() = order.getString(BONEENGRAVETYPE2, "기본")
        set(value){
            order.edit().putString(BONEENGRAVETYPE2, value).apply()
        }
    var boneEngraveType2Position : Int
        get() = order.getInt(BONEENGRAVETYPE2POSITION, 0)
        set(value){
            order.edit().putInt(BONEENGRAVETYPE2POSITION, value).apply()
        }
    var boneSex : String?
        get() = order.getString(BONESEX, "남성")
        set(value){
            order.edit().putString(BONESEX, value).apply()
        }
    var boneName1 : String?
        get() = order.getString(BONENAME1, "")
        set(value){
            order.edit().putString(BONENAME1, value).apply()
        }
    var boneName2 : String?
        get() = order.getString(BONENAME2, "")
        set(value){
            order.edit().putString(BONENAME2, value).apply()
        }
    var boneDate1 : String?
        get() = order.getString(BONEDATE1, "")
        set(value){
            order.edit().putString(BONEDATE1, value).apply()
        }
    var boneDate1Type : String?
        get() = order.getString(BONEDATE1TYPE, "양력")
        set(value){
            order.edit().putString(BONEDATE1TYPE, value).apply()
        }
    var boneDate2 : String?
        get() = order.getString(BONEDATE2, "")
        set(value){
            order.edit().putString(BONEDATE2, value).apply()
        }
    var boneDate2Type : String?
        get() = order.getString(BONEDATE2TYPE, "양력")
        set(value){
            order.edit().putString(BONEDATE2TYPE, value).apply()
        }
    var boneReligion : String?
        get() = order.getString(BONERELIGION, "무교")
        set(value){
            order.edit().putString(BONERELIGION, value).apply()
        }
    // 위패 보기
    var showRouterView : String?
        get() = order.getString(SHOWROUTERVIEW, "")
        set(value){
            order.edit().putString(SHOWROUTERVIEW, value).apply()
        }
    // 위패 종류 (일반, 본관, 문구)
    var tabletType : String?
        get() = order.getString(TABLETTYPE, "일반")
        set(value){
            order.edit().putString(TABLETTYPE, value).apply()
        }
    var tabletTypePosition : Int
        get() = order.getInt(TABLETTYPEPOSITION, 0)
        set(value){
            order.edit().putInt(TABLETTYPEPOSITION, value).apply()
        }
    // 위패 내용
    var name3 : String?
        get() = order.getString(NAME3, "")
        set(value){
            order.edit().putString(NAME3, value).apply()
        }
//    var name3Type : String?
//        get() = order.getString(NAME3TYPE, "")
//        set(value){
//            order.edit().putString(NAME3TYPE, value).apply()
//        }
//    var name3_1 : String?
//        get() = order.getString(NAME3_1, "")
//        set(value){
//            order.edit().putString(NAME3_1, value).apply()
//        }
//    var name3_2 : String?
//        get() = order.getString(NAME3_2, "")
//        set(value){
//            order.edit().putString(NAME3_2, value).apply()
//        }
//    var name3_3 : String?
//        get() = order.getString(NAME3_3, "")
//        set(value){
//            order.edit().putString(NAME3_3, value).apply()
//        }
    // 선택된 위패 종류
    var selectedTabletType : String?
        get() = order.getString(SELECTEDTABLETTYPE, "선택안함")
        set(value){
            order.edit().putString(SELECTEDTABLETTYPE, value).apply()
        }
    // 특이사항
    var note : String?
        get() = order.getString(NOTE, "")
        set(value){
            order.edit().putString(NOTE, value).apply()
        }
    // 사진 저장
    // 선택된 파일을 저장할 변수
    var selectedFile : String?
        get() = order.getString(SELECTEDFILE, "")
        set(value){
            order.edit().putString(SELECTEDFILE, value).apply()
        }
    // 이미지 URL을 저장할 변수
    var imageUrl : String?
        get() = order.getString(IMAGEURL, "")
        set(value){
            order.edit().putString(IMAGEURL, value).apply()
        }

    fun resetPreferencesUser() {
        // user SharedPreferences 초기화
        uid = ""
        userTel = ""
        authenticated = false
    }
    fun resetPreferencesLeader() {
        // manager SharedPreferences 초기화
        leaderName = ""
        leaderTel = ""
        leaderDepartment = ""
        saveInfo = false
    }
    fun resetPreferences() {
        // order SharedPreferences 초기화
        clientName = ""
        clientTel = ""
        selectedLocation = "화장장"
        cremationArea = "서울시립승화원"
        cremationTime = ""
        funeralName = ""
        funeralNumber = ""
        funeralTime = ""
        burialName = ""
        burialTime = ""
        engraveType = "일반"
        engraveTypePosition = 0
        engraveType2 = "기본"
        engraveType2Position = 0
        name1 = ""
        name2 = ""
        date1 = ""
        date1Type = "양력"
        date2 = ""
        date2Type = "양력"
        religion = ""
        selectedUrnType = "선택안함"
        boneEngraveType = ""
        boneEngraveTypePosition = 0
        boneEngraveType2 = ""
        boneEngraveType2Position = 0
        boneSex = ""
        boneName1 = ""
        boneName2 = ""
        boneDate1 = ""
        boneDate1Type = ""
        boneDate2 = ""
        boneDate2Type = ""
        boneReligion = ""
        showRouterView = ""
        tabletType = ""
        tabletTypePosition = 0
        name3 = ""
//        name3Type = ""
//        name3_1 = ""
//        name3_2 = ""
//        name3_3 = ""
        selectedTabletType = "선택안함"
        note = ""
        selectedFile = ""
        imageUrl = ""
    }
}
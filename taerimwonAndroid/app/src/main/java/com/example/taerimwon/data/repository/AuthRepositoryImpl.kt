package com.example.taerimwon.data.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.taerimwon.data.dto.user.User
import com.example.taerimwon.data.dto.user.black_list.BlackList
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.utils.constant.FireStoreCollection
import com.example.taerimwon.utils.constant.UiState
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.util.concurrent.TimeUnit

class AuthRepositoryImpl (
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val appPreferences: SharedPreferences,
    val gson: Gson
) : AuthRepository {

    override fun getBlackList(result: (UiState<List<BlackList>>) -> Unit) {
        database.collection(FireStoreCollection.BLACKLIST)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val blackListItems = mutableListOf<BlackList>()

                for (document in querySnapshot.documents) {
                    val blackList = document.toObject(BlackList::class.java)
                    blackList?.let {
                        blackListItems.add(it)
                    }
                }

                if (blackListItems.isNotEmpty()) {
                    result.invoke(UiState.Success(blackListItems))
                    println("blackList: " + blackListItems)

                    for (blackList in blackListItems) {
                        if (blackList.tel.replace("+82", "")
                                .equals(ApplicationClass.prefs.userTel)
                        ) {
                            ApplicationClass.prefs.resetPreferencesUser();
                        }
                    }
                } else {
                    println("blackList: " + "No documents found")
                    Log.d("getBlackList", "No documents found")
                }

            }
            .addOnFailureListener {
                println("blackList: " + "Fail get document")
                Log.d("getBlackList", "Fail get document")
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun phoneAuth(tel: String, activity: FragmentActivity, result: (String) -> Unit){
        auth.setLanguageCode("ko-KR")
//        auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(true)

        println("phoneNumber : " + tel)
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+82" + tel.substring(1))
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                // 번호인증 혹은 기타 다른 인증(구글로그인, 이메일로그인 등) 끝난 상태
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    println("onVerificationCompleted")
                }

                // 번호인증 실패 상태
                override fun onVerificationFailed(e: FirebaseException) {
                    println("onVerificationFailed")
                    if (e is FirebaseAuthInvalidCredentialsException) {
                        // Invalid request
                        println("onVerificationFailed: Invalid request")
                    } else if (e is FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                        println("onVerificationFailed: The SMS quota for the project has been exceeded")
                    } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                        // reCAPTCHA verification attempted with null Activity
                        println("onVerificationFailed: reCAPTCHA verification attempted with null Activity")
                    }else {
                        println("onVerificationFailed: " + e)
                    }

                    result.invoke("fail")
                }

                // 전화번호는 확인 되었으나 인증코드를 입력해야 하는 상태
                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    println("phoneAuth:")
                    result.invoke(verificationId)
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    override fun ckeckPhoneAuth(user: User, verificationId: String, code: String, result: (UiState<String>) -> Unit){
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        println("ckeckPhoneAuth : " + verificationId + " " + code)

        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
//                    auth.signOut()
                    println("ckeckPhoneAuth isSuccessful")

                    // 유저 전화번호 추가
                    val document = database.collection(FireStoreCollection.USER).document(user.uid)

                    document
                        .set(user)
                        .addOnSuccessListener {
                            result.invoke(
                                UiState.Success(user.userTel)
                            )
                            println("user has been added successfully")
                            Log.d("userInfo", "user has been added successfully")
                        }
                        .addOnFailureListener {
                            result.invoke(
                                UiState.Failure(
                                    it.localizedMessage
                                )
                            )
                            println("user has been added fail")
                            Log.d("adduser", "user has been added fail")
                        }

                    result.invoke(UiState.Success("인증 성공했습니다."))
                } else {
                    println("ckeckPhoneAuth Fail")
                    result.invoke(UiState.Failure("인증 실패했습니다."))
                }
            }
    }
}
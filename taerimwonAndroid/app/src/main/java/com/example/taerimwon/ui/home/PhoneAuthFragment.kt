package com.example.taerimwon.ui.home

import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.data.dto.user.User
import com.example.taerimwon.databinding.FragmentPhoneAuthBinding
import com.example.taerimwon.utils.constant.UiState
import com.example.taerimwon.utils.view.toast
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.utils.constant.enabled
import com.example.taerimwon.utils.input.PhoneNumberTextWatcher

@AndroidEntryPoint
class PhoneAuthFragment : BaseFragment<FragmentPhoneAuthBinding>(R.layout.fragment_phone_auth) {
    val authViewModel: AuthViewModel by viewModels()
    lateinit var user: User
    var verificationId : String = ""

    override fun init() {
        initData()
        setOnClickListeners()
        addTextChangedListener()
        observer()
    }

    private fun initData() {
        authViewModel.getBlackList()
    }
    private fun setOnClickListeners() {
        binding.buttonTelAuth.setOnClickListener{
            val editTextTel = binding.editTextTel.text.toString()
            val telPattern = """^[0-9]{3}-[0-9]{4}-[0-9]{4}$""".toRegex()
            if (!editTextTel.matches(telPattern)) {
                toast("전화번호를 010-1234-5678 형태로 올바르게 입력해주세요.")
            }else{
                var activity = requireActivity()
                var userTel = binding.editTextTel.text.toString().replace("-", "")

                authViewModel.phoneAuth (
                    tel = userTel,
                    activity = activity
                )

                // 유저 정보 추가
                user = User(
                    userTel,
                    userTel,
                    "getNowCalendar().toString()",
                    "getNowCalendar().toString()",
                    true
                )

//                val dialog = PhoneAuthDialogFragment()
//                // 화면 밖 터치시 종료되지 않게 하기
//                dialog.isCancelable = false
//                dialog.setOnOKClickedListener { content ->
//                    if(verificationId != "") {
//                        authViewModel.ckeckPhoneAuth(
//                            user = user,
//                            verificationId = verificationId,
//                            code = content
//                        )
//                    }
//                }
//                dialog.show(childFragmentManager, "complete phone auth")
            }
        }
        binding.buttonOrderFragment.setOnClickListener{
            if(verificationId != "") {
                authViewModel.ckeckPhoneAuth(
                    user = user,
                    verificationId = verificationId,
                    code = binding.editTextTelAuth.text.toString()
                )
            }
        }
    }
    private fun observer() {
        authViewModel.getBlackList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // 데이터 로딩 중인 경우 처리
                }
                is UiState.Success -> {
                    val blackListItems = state.data // 실제 데이터를 얻기 위해 data 속성을 사용
                    if(ApplicationClass.prefs.authenticated)
                        findNavController().navigate(R.id.action_phoneAuthFragment_to_orderFragment)
                    binding.textTel.visibility = View.VISIBLE
                    binding.editTextTel.visibility = View.VISIBLE
                    binding.imageTel.visibility = View.VISIBLE
                    binding.buttonTelAuth.visibility = View.VISIBLE
                    binding.buttonOrderFragment.visibility = View.VISIBLE
                }
                is UiState.Failure -> {
                    val errorMessage = state.error // 오류 메시지를 얻기 위해 error 속성을 사용
                    // 오류 처리
                }
            }
        }
        authViewModel.phoneAuth.observe(viewLifecycleOwner) { state ->
            verificationId = state
            if(verificationId != "fail"){
                toast("인증번호가 전송되었습니다.\n60초 이내에 입력해주세요.")
                binding.textTelAuth.visibility = View.VISIBLE
                binding.editTextTelAuth.visibility = View.VISIBLE
                binding.buttonOrderFragment.isEnabled = true
            }else{
                toast("인증번호 전송에 실패했습니다.\n관리자에게 문의해주세요.")
            }
        }
        authViewModel.ckeckPhoneAuth.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
//                    binding.progressBarJoinLoading.show()
                }
                is UiState.Failure -> {
//                    binding.progressBarJoinLoading.hide()
                    state.error?.let { toast(it) }
                }
                is UiState.Success -> {
//                    binding.progressBarJoinLoading.hide()
                    binding.editTextTel.isEnabled = false
                    binding.buttonTelAuth.isEnabled = false
                    binding.editTextTelAuth.isEnabled = false
                    ApplicationClass.prefs.uid = user.uid
                    ApplicationClass.prefs.userTel = user.userTel
                    ApplicationClass.prefs.authenticated = true
                    toast(state.data)

                    authViewModel.getBlackList()
                }
            }
        }
    }
    private fun addTextChangedListener() {
        val editTextTel = binding.editTextTel
        editTextTel.addTextChangedListener(PhoneNumberTextWatcher(editTextTel))
    }
}
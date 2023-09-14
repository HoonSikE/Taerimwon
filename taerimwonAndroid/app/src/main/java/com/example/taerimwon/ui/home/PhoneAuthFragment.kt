package com.example.taerimwon.ui.home

import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.data.dto.user.User
import com.example.taerimwon.databinding.FragmentPhoneAuthBinding
import com.example.taerimwon.utils.constant.UiState
import com.example.taerimwon.utils.view.toast
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class PhoneAuthFragment : BaseFragment<FragmentPhoneAuthBinding>(R.layout.fragment_phone_auth) {
    val authViewModel: AuthViewModel by viewModels()
    lateinit var user: User
    var verificationId : String = ""

    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
        authViewModel.getBlackList()
    }
    private fun setOnClickListeners() {
        binding.buttonTelAuth.setOnClickListener{
            var activity = requireActivity()

            authViewModel.getBlackList()

            authViewModel.phoneAuth (
                tel = "+82" + binding.editTextTel.text.toString(),
                activity = activity
            )
            toast("인증번호가 전송되었습니다. 60초 이내에 입력해주세요.")
//            binding.buttonTelAuth.isEnabled = false

            // 유저 정보 추가
            user = User(
                "dd",
                "+82" + binding.editTextTel.text.toString(),
                "dd",
                true
            )

            val dialog = PhoneAuthDialogFragment()
            // 화면 밖 터치시 종료되지 않게 하기
            dialog.isCancelable = false
            dialog.setOnOKClickedListener { content ->
                if(verificationId != "") {
                    authViewModel.ckeckPhoneAuth(
                        user = user,
                        verificationId = verificationId,
                        code = content
                    )
                }
            }
            dialog.show(childFragmentManager, "complete phone auth")
        }
        binding.buttonOrderFragment.setOnClickListener{
            findNavController().navigate(R.id.action_phoneAuthFragment_to_orderFragment)
        }
    }
    private fun observer() {
        authViewModel.phoneAuth.observe(viewLifecycleOwner) { state ->
            verificationId = state
        }
        authViewModel.ckeckPhoneAuth.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Loading -> {
//                    binding.progressBarJoinLoading.show()
                }
                is UiState.Failure -> {
//                    binding.progressBarJoinLoading.hide()
                    state.error?.let { toast(it) }
                }
                is UiState.Success -> {
//                    binding.progressBarJoinLoading.hide()
                    toast(state.data)
                }
            }
        }
    }
}
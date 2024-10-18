package com.example.taerimwon.ui.home

import android.content.pm.PackageManager
import android.os.CountDownTimer
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
import com.example.taerimwon.utils.input.PhoneNumberTextWatcher
import java.util.*

@AndroidEntryPoint
class PhoneAuthFragment : BaseFragment<FragmentPhoneAuthBinding>(R.layout.fragment_phone_auth) {
    private val REQUEST_CODE_SMS_PERMISSION = 102 // 권한 요청 코드
    val authViewModel: AuthViewModel by viewModels()
    lateinit var user: User
    var verificationId : String = ""

    // 인증 시간 제한 타이머
    private var timer: CountDownTimer? = null

    override fun init() {
        initData()
        setOnClickListeners()
        addTextChangedListener()
        observer()
    }

    private fun initData() {
        // 전화번호 인증 안 쓸때 사용
        println("ApplicationClass.prefs.authenticated" + ApplicationClass.prefs.authenticated)
        println("ApplicationClass.prefs.userTel" + ApplicationClass.prefs.userTel)
//        findNavController().navigate(R.id.action_phoneAuthFragment_to_homeFragment)

        authViewModel.getBlackList()

        // 타이머
        val totalTime : Long = 1 * 60 * 1000 // 1분을 밀리초로 변환
        timer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 남은 시간을 분과 초로 나누어 표시
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                binding.textTimer.text = String.format("%02d:%02d", minutes, seconds)
                binding.buttonTelAuth.isEnabled = false
                binding.buttonTelAuth.setBackgroundResource(R.drawable.button_auth2)
            }

            override fun onFinish() {
                binding.textTimer.text = "00:00"
                binding.buttonTelAuth.isEnabled = true
                binding.buttonTelAuth.setBackgroundResource(R.drawable.button_auth)
            }
        }
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

//                val permission = Manifest.permission.SEND_SMS
//                if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), REQUEST_CODE_SMS_PERMISSION)
//                } else {
//                    // 권한이 이미 허용되어 있는 경우 SMS를 보낼 수 있습니다.
//                    val smsManager = SmsManager.getDefault()
//                    val phoneNumber = "+821045097485" // 대상 전화번호
//                    val verificationCode = generateVerificationCode() // 랜덤한 인증 코드 생성
//                    smsManager.sendTextMessage(phoneNumber, null, "Your verification code is: $verificationCode", null, null)
//                }
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
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_SMS_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // SMS 권한이 허용된 경우
                } else {
                    // 사용자가 권한을 거부한 경우
                    // 적절한 처리를 수행하세요.
                }
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
                    // 이미 인증한 경우 home으로 이동
                    if(ApplicationClass.prefs.authenticated) {
                        timer?.cancel()
                        findNavController().navigate(R.id.action_phoneAuthFragment_to_homeFragment)
                    }
                    // 아직 인증을 하지 않은 경우 인증 UI 출력
                    binding.textTel.visibility = View.VISIBLE
                    binding.editTextTel.visibility = View.VISIBLE
//                    binding.imageTel.visibility = View.VISIBLE
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
               binding.buttonTelAuth.text = "재전송"
                binding.textTelAuth.visibility = View.VISIBLE
                binding.editTextTelAuth.visibility = View.VISIBLE
                binding.textTimer.visibility = View.VISIBLE
//                binding.imageTelAuth.visibility = View.VISIBLE
                binding.buttonOrderFragment.isEnabled = true

                timer?.start()
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
    fun generateVerificationCode(): String {
        val length = 6 // 인증 코드 길이 (원하는 길이로 수정 가능)

        val random = Random(System.currentTimeMillis())
        val code = StringBuilder()

        for (i in 0 until length) {
            val digit = random.nextInt(10) // 0부터 9 사이의 랜덤 숫자 생성
            code.append(digit)
        }

        return code.toString()
    }
}
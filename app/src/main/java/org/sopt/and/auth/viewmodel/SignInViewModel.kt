package org.sopt.and.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    var id: String = ""
    var password: String = ""
    var errorMessage: String? = null

    fun isValidInput(): Boolean {
        return validateInput(id, password)
    }


    // 입력값 검증 함수 (내부에서만 사용)
    private fun validateInput(id: String, password: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
                errorMessage = "유효한 이메일을 입력하세요."
                false
            }
            password.length < 8 -> {
                errorMessage = "비밀번호는 8자 이상이어야 합니다."
                false
            }
            else -> true
        }
    }
}

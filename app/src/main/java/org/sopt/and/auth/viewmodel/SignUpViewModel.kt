package org.sopt.and.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    var id: String = ""
    var password: String = ""
    var errorMessage: String? = null

    fun signUp(onSuccess: () -> Unit) {
        if (validateInput(id, password)) {
            viewModelScope.launch {
                onSuccess()
            }
        }
    }

    private fun validateInput(id: String, password: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
                errorMessage = "유효한 이메일을 입력하세요."
                false
            }
            !PASSWORD_REGEX.matches(password) -> {
                errorMessage = "비밀번호는 8~20자 이내로 대소문자, 숫자, 특수문자 조합이어야 합니다."
                false
            }
            else -> true
        }
    }

    companion object {
        private val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,20}\$")
    }
}

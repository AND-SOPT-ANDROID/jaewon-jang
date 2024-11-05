package org.sopt.and.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    var id: String = "" // 사용자가 입력한 아이디
    var password: String = "" // 사용자가 입력한 비밀번호
    var errorMessage: String? = null // 에러 메시지 저장

    fun signIn(onSuccess: () -> Unit) {
        if (validateInput(id, password)) {
            viewModelScope.launch {
                // 로그인 처리 로직
                onSuccess() // 로그인 성공 시 호출
            }
        }
    }

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

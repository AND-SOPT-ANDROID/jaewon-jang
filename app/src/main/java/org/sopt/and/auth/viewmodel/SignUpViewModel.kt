package org.sopt.and.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun updateId(newId: String) {
        _id.value = newId
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun signUp(onSuccess: () -> Unit) {
        if (validateInput(_id.value ?: "", _password.value ?: "")) {
            viewModelScope.launch {
                onSuccess()
            }
        }
    }

    private fun validateInput(id: String, password: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
                _errorMessage.value = "유효한 이메일을 입력하세요."
                false
            }
            !PASSWORD_REGEX.matches(password) -> {
                _errorMessage.value = "비밀번호는 8~20자 이내로 대소문자, 숫자, 특수문자 조합이어야 합니다."
                false
            }
            else -> true
        }
    }

    companion object {
        private val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,20}\$")
    }
}
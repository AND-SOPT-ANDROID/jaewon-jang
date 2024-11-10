package org.sopt.and.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SignInViewModel : ViewModel() {
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

    fun isValidInput(): Boolean {
        return validateInput(_id.value ?: "", _password.value ?: "")
    }

    private fun validateInput(id: String, password: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
                _errorMessage.value = "유효한 이메일을 입력하세요."
                false
            }
            password.length < 8 -> {
                _errorMessage.value = "비밀번호는 8자 이상이어야 합니다."
                false
            }
            else -> true
        }
    }
}

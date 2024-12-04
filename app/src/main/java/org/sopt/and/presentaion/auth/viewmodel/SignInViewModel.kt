package org.sopt.and.presentaion.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.data.repository.AuthRepository
import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> get() = _token

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun signIn() {
        val username = _username.value ?: ""
        val password = _password.value ?: ""

        if (username.isEmpty() || password.isEmpty()) {
            _errorMessage.value = "ID와 비밀번호를 입력하세요."
            return
        }

        val request = LoginRequestDto(username, password)
        authRepository.login(request).enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(call: Call<LoginResponseDto>, response: Response<LoginResponseDto>) {
                if (response.isSuccessful) {
                    _token.value = response.body()?.result?.token
                    _errorMessage.value = "로그인 성공"
                } else {
                    _errorMessage.value = "로그인 실패: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                _errorMessage.value = "로그인 실패: ${t.message}"
            }
        })
    }
}

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
        _username.value = newUsername.trim()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword.trim()
    }

    fun signIn() {
        val username = _username.value ?: ""
        val password = _password.value ?: ""

        println("SignInViewModel - 입력값: username=$username, password=$password")

        if (username.isBlank() || password.isBlank()) {
            _errorMessage.value = "ID와 비밀번호를 입력하세요."
            return
        }

        val request = LoginRequestDto(username, password)
        authRepository.login(request).enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(
                call: Call<LoginResponseDto>,
                response: Response<LoginResponseDto>
            ) {
                println("SignInViewModel - 서버 응답 코드: ${response.code()}, 메시지: ${response.message()}")

                if (response.isSuccessful) {
                    val token = response.body()?.result?.token
                    if (token != null) {
                        println("SignInViewModel - 토큰 수신: $token") // 토큰 로그
                        _token.value = token
                        _errorMessage.value = "로그인 성공"
                    } else {
                        println("SignInViewModel - 실패: 서버에서 토큰을 반환하지 않았습니다.")
                        _errorMessage.value = "로그인 실패: 서버에서 토큰을 반환하지 않았습니다."
                    }
                } else {
                    _errorMessage.value = "로그인 실패: ${response.code()} - ${response.message()}"
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                println("SignInViewModel - 네트워크 오류: ${t.message}")
                _errorMessage.value = "로그인 실패: ${t.message}"
            }
        })
    }
}

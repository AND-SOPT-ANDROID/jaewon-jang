package org.sopt.and.presentaion.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.data.api.ServicePool
import org.sopt.and.data.dto.SignUpRequestDto
import org.sopt.and.data.dto.SignUpResponseDto
import org.sopt.and.data.repository.AuthRepository
import org.sopt.and.data.repository.AuthRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl(ServicePool.apiService)
) : ViewModel() {    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _hobby = MutableLiveData<String>()
    val hobby: LiveData<String> get() = _hobby

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _userNumber = MutableLiveData<Int>()
    val userNumber: LiveData<Int> get() = _userNumber

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateHobby(newHobby: String) {
        _hobby.value = newHobby
    }

    fun signUp() {
        val username = _username.value ?: ""
        val password = _password.value ?: ""
        val hobby = _hobby.value ?: ""

        if (username.length > 8 || password.length > 8 || hobby.length > 8) {
            _message.value = "각 입력값은 8자를 넘을 수 없습니다."
            return
        }

        val request = SignUpRequestDto(username, password, hobby)
        authRepository.register(request).enqueue(object : Callback<SignUpResponseDto> {
            override fun onResponse(call: Call<SignUpResponseDto>, response: Response<SignUpResponseDto>) {
                if (response.isSuccessful) {
                    _message.value = "회원가입 성공"
                    _userNumber.value = response.body()?.result?.no
                } else {
                    _message.value = "회원가입 실패: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<SignUpResponseDto>, t: Throwable) {
                _message.value = "회원가입 실패: ${t.message}"
            }
        })
    }
}

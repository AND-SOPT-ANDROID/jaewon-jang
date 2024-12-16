package org.sopt.and.presentation.auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import org.sopt.and.data.repository.AuthRepository
import org.sopt.and.presentation.auth.contract.SignInIntent
import org.sopt.and.presentation.auth.contract.SignInState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(
    private val authRepository: AuthRepository,
    private val context: Context
) : ViewModel() {


    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> get() = _state

    private val _intentChannel = Channel<SignInIntent>(Channel.UNLIMITED)
    val intents = _intentChannel.receiveAsFlow()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                when (intent) {
                    is SignInIntent.UpdateUsername -> {
                        _state.update { it.copy(username = intent.username) }
                    }
                    is SignInIntent.UpdatePassword -> {
                        _state.update { it.copy(password = intent.password) }
                    }
                    is SignInIntent.SignIn -> {
                        signIn()
                    }
                }
            }
        }
    }

    private fun signIn() {
        val username = _state.value.username.trim()
        val password = _state.value.password.trim()

        if (username.isEmpty() || password.isEmpty()) {
            _state.update { it.copy(errorMessage = "ID와 비밀번호를 입력하세요.") }
            return
        }

        _state.update { it.copy(isLoading = true) }

        val request = LoginRequestDto(username, password)
        authRepository.login(request).enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(call: Call<LoginResponseDto>, response: Response<LoginResponseDto>) {
                if (response.isSuccessful) {
                    val token = response.body()?.result?.token
                    if (token != null) {
                        _state.update {
                            it.copy(
                                token = token,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "로그인 실패: 서버에서 토큰을 반환하지 않았습니다."
                            )
                        }
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "로그인 실패: ${response.code()} - ${response.message()}"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "네트워크 오류: ${t.localizedMessage ?: "알 수 없는 오류"}"
                    )
                }
            }
        })
    }

    fun sendIntent(intent: SignInIntent) {
        viewModelScope.launch {
            _intentChannel.send(intent)
        }
    }
}

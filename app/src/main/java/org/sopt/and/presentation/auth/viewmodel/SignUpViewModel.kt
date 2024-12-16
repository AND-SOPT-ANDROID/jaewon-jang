package org.sopt.and.presentation.auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.sopt.and.data.dto.SignUpRequestDto
import org.sopt.and.data.repository.AuthRepository
import org.sopt.and.presentation.auth.contract.SignUpIntent
import org.sopt.and.presentation.auth.contract.SignUpState

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> get() = _state

    private val _intentChannel = Channel<SignUpIntent>(Channel.UNLIMITED)
    private val intents = _intentChannel.receiveAsFlow()


    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                when (intent) {
                    is SignUpIntent.UpdateUsername -> _state.update { it.copy(username = intent.username) }
                    is SignUpIntent.UpdatePassword -> _state.update { it.copy(password = intent.password) }
                    is SignUpIntent.UpdateHobby -> _state.update { it.copy(hobby = intent.hobby) }
                    is SignUpIntent.SignUp -> signUp()
                }
            }
        }
    }

    private fun signUp() {
        val username = _state.value.username
        val password = _state.value.password
        val hobby = _state.value.hobby

        if (username.isBlank() || password.isBlank() || hobby.isBlank()) {
            _state.update { it.copy(errorMessage = "모든 필드를 입력하세요.") }
            return
        }

        if (username.length > 8 || password.length > 8 || hobby.length > 8) {
            _state.update { it.copy(errorMessage = "입력값은 8자를 초과할 수 없습니다.") }
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.register(SignUpRequestDto(username, password, hobby)).execute()
                if (response.isSuccessful) {
                    _state.update {
                        it.copy(
                            successMessage = "회원가입 성공",
                            errorMessage = null
                        )
                    }
                } else {
                    _state.update { it.copy(errorMessage = "회원가입 실패: ${response.code()}") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = "네트워크 오류: ${e.message}") }
            }
        }
    }
    fun clearSuccessMessage() {
        _state.update { it.copy(successMessage = null) }
    }

    fun sendIntent(intent: SignUpIntent) {
        viewModelScope.launch { _intentChannel.send(intent) }
    }
}



package org.sopt.and.presentation.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.sopt.and.data.dto.ApiResponse
import org.sopt.and.data.dto.HobbyDto
import org.sopt.and.data.repository.AuthRepository
import org.sopt.and.presentation.main.contract.MypageIntent
import org.sopt.and.presentation.main.contract.MypageState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageViewModel(
    private val authRepository: AuthRepository,
    private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(MypageState())
    val state: StateFlow<MypageState> get() = _state

    private val _intentChannel = Channel<MypageIntent>(Channel.UNLIMITED)
    val intents = _intentChannel.receiveAsFlow()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                when (intent) {
                    is MypageIntent.FetchHobby -> fetchHobby()
                }
            }
        }
    }

    private fun fetchHobby() {
        val token = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("token", null)

        if (token.isNullOrEmpty()) {
            _state.update { it.copy(errorMessage = "토큰이 없습니다. 다시 로그인하세요.") }
            return
        }

        authRepository.getMyHobby(token).enqueue(object : Callback<ApiResponse<HobbyDto>> {
            override fun onResponse(
                call: Call<ApiResponse<HobbyDto>>,
                response: Response<ApiResponse<HobbyDto>>
            ) {
                if (response.isSuccessful) {
                    val hobby = response.body()?.result?.hobby
                    _state.update { it.copy(hobby = hobby, errorMessage = null) }
                } else {
                    _state.update { it.copy(errorMessage = "취미 조회 실패: ${response.code()}") }
                }
            }

            override fun onFailure(call: Call<ApiResponse<HobbyDto>>, t: Throwable) {
                _state.update {
                    it.copy(errorMessage = "네트워크 오류: ${t.localizedMessage ?: "알 수 없는 오류"}")
                }
            }
        })
    }

    fun sendIntent(intent: MypageIntent) {
        viewModelScope.launch {
            _intentChannel.send(intent)
        }
    }

    fun clearErrorMessage() {
        _state.update { it.copy(errorMessage = null) }
    }
}

package org.sopt.and.presentaion.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.data.api.ServicePool
import org.sopt.and.data.dto.ApiResponse
import org.sopt.and.data.dto.HobbyDto
import org.sopt.and.data.repository.AuthRepository
import org.sopt.and.data.repository.AuthRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl(ServicePool.apiService)
) : ViewModel() {
    private val _hobby = MutableLiveData<String?>()
    val hobby: LiveData<String?> get() = _hobby

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun fetchMyHobby(token: String) {
        authRepository.getMyHobby(token).enqueue(object : Callback<ApiResponse<HobbyDto>> {
            override fun onResponse(
                call: Call<ApiResponse<HobbyDto>>,
                response: Response<ApiResponse<HobbyDto>>
            ) {
                if (response.isSuccessful) {
                    _hobby.value = response.body()?.result?.hobby
                } else {
                    _errorMessage.value = when (response.code()) {
                        401 -> "토큰이 없습니다. 다시 로그인하세요."
                        403 -> "유효하지 않은 토큰입니다."
                        else -> "취미 조회 실패: ${response.code()}"
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<HobbyDto>>, t: Throwable) {
                _errorMessage.value = "취미 조회 실패: ${t.message}"
            }
        })
    }
}


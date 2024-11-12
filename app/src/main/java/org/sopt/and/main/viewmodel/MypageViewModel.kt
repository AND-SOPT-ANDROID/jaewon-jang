package org.sopt.and.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.data.api.ServicePool
import org.sopt.and.data.dto.HobbyResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageViewModel : ViewModel() {
    private val apiService = ServicePool.apiService

    private val _hobby = MutableLiveData<String?>()
    val hobby: LiveData<String?> get() = _hobby

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun fetchMyHobby(token: String) {
        apiService.getMyHobby(token).enqueue(object : Callback<HobbyResponseDto> {
            override fun onResponse(call: Call<HobbyResponseDto>, response: Response<HobbyResponseDto>) {
                if (response.isSuccessful) {
                    _hobby.value = response.body()?.result?.hobby
                } else {
                    _errorMessage.value = "취미 조회 실패: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<HobbyResponseDto>, t: Throwable) {
                _errorMessage.value = "취미 조회 실패: ${t.message}"
            }
        })
    }
}


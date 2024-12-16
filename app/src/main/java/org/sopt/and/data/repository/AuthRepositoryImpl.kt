package org.sopt.and.data.repository

import android.util.Log
import org.sopt.and.data.api.ApiService
import org.sopt.and.data.dto.ApiResponse
import org.sopt.and.data.dto.HobbyDto
import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import org.sopt.and.data.dto.SignUpRequestDto
import org.sopt.and.data.dto.SignUpResponseDto
import retrofit2.Call

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    companion object {
        private const val TAG = "AuthRepositoryImpl"
    }

    override fun login(request: LoginRequestDto): Call<LoginResponseDto> {
        Log.d(TAG, "Login request: username=${request.username}, password=${request.password}")
        return apiService.loginUser(request)
    }

    override fun register(request: SignUpRequestDto): Call<SignUpResponseDto> {
        Log.d(TAG, "SignUp request: username=${request.username}, password=${request.password}, hobby=${request.hobby}")
        return apiService.registerUser(request)
    }

    override fun getMyHobby(token: String): Call<ApiResponse<HobbyDto>> {
        Log.d(TAG, "Fetching hobby with token: $token")
        return apiService.getMyHobby(token)
    }
}
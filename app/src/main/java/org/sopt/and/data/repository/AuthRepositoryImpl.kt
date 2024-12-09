package org.sopt.and.data.repository

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
    override fun login(request: LoginRequestDto): Call<LoginResponseDto> {
        println("AuthRepositoryImpl - 요청 데이터: username=${request.username}, password=${request.password}")
        return apiService.loginUser(request)
    }

    override fun register(request: SignUpRequestDto): Call<SignUpResponseDto> {
        println("SignUpRequestDto: username=${request.username}, password=${request.password}, hobby=${request.hobby}")
        return apiService.registerUser(request)
    }


    override fun getMyHobby(token: String): Call<ApiResponse<HobbyDto>> {
        return apiService.getMyHobby(token)
    }
}
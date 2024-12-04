package org.sopt.and.data.repository

import org.sopt.and.data.dto.ApiResponse
import org.sopt.and.data.dto.HobbyDto
import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import org.sopt.and.data.dto.SignUpRequestDto
import org.sopt.and.data.dto.SignUpResponseDto
import retrofit2.Call

interface AuthRepository {
    fun login(request: LoginRequestDto): Call<LoginResponseDto>
    fun register(request: SignUpRequestDto): Call<SignUpResponseDto>
    fun getMyHobby(token: String): Call<ApiResponse<HobbyDto>>
}

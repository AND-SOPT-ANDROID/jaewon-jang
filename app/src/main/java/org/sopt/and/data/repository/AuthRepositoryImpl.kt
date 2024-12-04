package org.sopt.and.data.repository

import org.sopt.and.data.api.ApiService
import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import retrofit2.Call

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    override fun login(request: LoginRequestDto): Call<LoginResponseDto> {
        return apiService.loginUser(request)
    }
}
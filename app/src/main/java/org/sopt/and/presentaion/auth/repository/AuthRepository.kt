package org.sopt.and.presentaion.auth.repository

import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import retrofit2.Call

interface AuthRepository {
    fun login(request: LoginRequestDto): Call<LoginResponseDto>
}
package org.sopt.and.data.api

import org.sopt.and.data.dto.ApiResponse
import org.sopt.and.data.dto.HobbyDto
import org.sopt.and.data.dto.LoginRequestDto
import org.sopt.and.data.dto.LoginResponseDto
import org.sopt.and.data.dto.SignUpRequestDto
import org.sopt.and.data.dto.SignUpResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/user")
    fun registerUser(@Body userRequest: SignUpRequestDto): Call<SignUpResponseDto>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequestDto): Call<LoginResponseDto>

    @GET("/user/my-hobby")
    fun getMyHobby(@Header("token") token: String): Call<ApiResponse<HobbyDto>>
}



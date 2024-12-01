package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("result") val result: TokenDto
)

@Serializable
data class TokenDto(
    @SerialName("token") val token: String
)
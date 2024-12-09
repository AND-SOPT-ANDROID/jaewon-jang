package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("hobby") val hobby: String
)
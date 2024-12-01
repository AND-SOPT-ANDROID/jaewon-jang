package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("result") val result: SignUpResultDto
)

@Serializable
data class SignUpResultDto(
    @SerialName("no") val no: Int
)
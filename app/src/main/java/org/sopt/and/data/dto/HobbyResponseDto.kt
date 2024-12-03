package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HobbyResponseDto(
    @SerialName("result") val result: HobbyDto
)


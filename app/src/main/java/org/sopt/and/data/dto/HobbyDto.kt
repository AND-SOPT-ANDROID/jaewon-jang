package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HobbyDto(
    @SerialName("hobby") val hobby: String
)



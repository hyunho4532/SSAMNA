package com.asetec.domain.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (

    @SerialName("google_id")
    val googleId: String = "",

    @SerialName("email")
    val email: String = "",

    @SerialName("name")
    val name: String = "",
)
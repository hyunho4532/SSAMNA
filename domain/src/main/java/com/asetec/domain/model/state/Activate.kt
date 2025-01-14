package com.asetec.domain.model.state

import kotlinx.serialization.Serializable

@Serializable
data class Activate(
    val index: String = "",
    val name: String = "",
    val description: String = ""
)
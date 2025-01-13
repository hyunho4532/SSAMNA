package com.asetec.domain.model.location

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    var latitude: Double,
    var longitude: Double
)
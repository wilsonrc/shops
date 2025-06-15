package com.example.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShopDto(
    val id: String,
    val name: String,
    val description: String,
    val picture: String? = null,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    @SerialName("google_maps_link") val mapsUrl: String,
    val website: String
)
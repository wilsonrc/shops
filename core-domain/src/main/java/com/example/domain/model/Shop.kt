package com.example.domain.model

/**
 * Pure Kotlin representation of a sake shop.
 * Kept free of Android dependencies so it can be reused on any platform.
 */
data class Shop(
    val id: String,
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val mapsUrl: String,
    val website: String
)
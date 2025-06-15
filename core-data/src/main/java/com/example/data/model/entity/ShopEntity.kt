package com.example.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shops")
data class ShopEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val lat: Double,
    val lon: Double,
    val mapsUrl: String,
    val website: String
)
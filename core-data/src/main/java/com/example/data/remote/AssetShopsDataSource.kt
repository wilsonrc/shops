package com.example.data.remote

import com.example.data.model.dto.ShopDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetShopsDataSource @Inject constructor(
    private val reader: AssetJsonReader
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun load(): List<ShopDto> =
        json.decodeFromString(reader.read("shops.json"))
}
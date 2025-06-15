package com.example.data.remote

import com.example.data.model.dto.ShopDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetShopsDataSource @Inject constructor(
    private val reader: AssetJsonReader,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun load(): List<ShopDto> = withContext(dispatcher){
        json.decodeFromString(reader.read("shops.json"))
    }

}
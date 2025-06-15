package com.example.data.remote

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class AssetJsonReader(private val context: Context) {

    suspend fun read(filename: String): String = withContext(Dispatchers.IO) {
        context.assets.open(filename).use { stream ->
            InputStreamReader(stream).readText()
        }
    }
}
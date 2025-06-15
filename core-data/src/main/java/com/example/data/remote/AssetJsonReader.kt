package com.example.data.remote

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class AssetJsonReader(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun read(filename: String): String = withContext(dispatcher) {
        context.assets.open(filename).use { stream ->
            InputStreamReader(stream).readText()
        }
    }
}
package com.example.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.data.remote.AssetJsonReader
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AssetJsonReaderTest {
    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun read_returnsNonEmptyContent() = runTest(dispatcher) {
        val ctx = InstrumentationRegistry.getInstrumentation().context
        val reader = AssetJsonReader(ctx, dispatcher)
        val json = reader.read("shops.json")
        assertTrue(json.isNotBlank())
    }
}
package com.example.data

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.data.local.FakeShopDao
import com.example.data.remote.AssetJsonReader
import com.example.data.remote.AssetShopsDataSource
import com.example.data.repository.ShopsRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopsRepositoryImplTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun refresh_populatesDao_and_getShop_returnsItem() = runTest(dispatcher) {
        val ctx = InstrumentationRegistry.getInstrumentation().context
        val reader = AssetJsonReader(ctx, dispatcher)
        val remote = AssetShopsDataSource(reader)
        val dao = FakeShopDao()
        val repo = ShopsRepositoryImpl(dao, remote, dispatcher)

        repo.refresh()

        val all = repo.observeShops().first()
        assertTrue(all.isNotEmpty())

        val first = all.first()
        val byId = repo.getShop(first.id)
        assertEquals(first, byId)
    }
}
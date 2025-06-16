package com.example.feature_shops_list

import com.example.domain.model.Shop
import com.example.domain.repository.ShopsRepository
import com.example.domain.usecase.ObserveShopsUseCase
import com.example.domain.usecase.RefreshShopsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class ShopsListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Before fun setMain() = kotlinx.coroutines.Dispatchers.setMain(dispatcher)
    @After  fun resetMain() = kotlinx.coroutines.Dispatchers.resetMain()

    private fun sample() = Shop(
        id = "1",
        name = "Test Bar",
        description = "desc",
        picture = null,
        rating = 4.5,
        address = "addr",
        latitude = 0.0,
        longitude = 0.0,
        mapsUrl = "",
        website = ""
    )

    @Test
    fun `emits Loading then Ready with data`() = runTest {
        val repo = FakeShopsRepository(listOf(sample()))
        val vm = ShopsListViewModel(
            ObserveShopsUseCase(repo),
            RefreshShopsUseCase(repo)
        )

        val emissions = mutableListOf<ShopsListUiState>()
        val job = launch { vm.state.take(2).toList(emissions) }
        job.join()

        assertEquals(ShopsListUiState.Loading, emissions[0])
        val ready = emissions[1] as ShopsListUiState.Ready
        assertEquals(listOf(sample()), ready.shops)
    }

    @Test
    fun `upstream failure surfaces as Error state`() = runTest {
        val faultyRepo = object : ShopsRepository {
            override fun observeShops() = kotlinx.coroutines.flow.flow<List<Shop>> {
                throw RuntimeException("boom")
            }
            override suspend fun getShop(id: String) = null
            override suspend fun refresh() { /* no‑op */ }
        }

        val vm = ShopsListViewModel(
            ObserveShopsUseCase(faultyRepo),
            RefreshShopsUseCase(faultyRepo)
        )

        val emissions = mutableListOf<ShopsListUiState>()
        val job = launch { vm.state.take(2).toList(emissions) }   // Loading + Error
        job.join()

        assertTrue(emissions[1] is ShopsListUiState.Error)
    }
}
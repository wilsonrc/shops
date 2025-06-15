package com.example.domain

import com.example.domain.model.Shop
import com.example.domain.usecase.GetShopUseCase
import com.example.domain.usecase.ObserveShopsUseCase
import com.example.domain.usecase.RefreshShopsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ShopsUseCasesTest {

    private val sample = Shop(
        id = "1",
        name = "Test Sake Bar",
        description = "Sample description",
        picture = null,
        rating = 4.2,
        address = "Some Street 123",
        latitude = 0.0,
        longitude = 0.0,
        mapsUrl = "",
        website = ""
    )

    private val repo = FakeShopsRepository(listOf(sample))

    @Test
    fun `GetShopUseCase returns the expected shop`() = runBlocking {
        val useCase = GetShopUseCase(repo)
        val result = useCase("1")
        assertEquals(sample, result)
    }

    @Test
    fun `ObserveShopsUseCase emits current list`() = runBlocking {
        val useCase = ObserveShopsUseCase(repo)
        val list = useCase().first()
        assertEquals(listOf(sample), list)
    }

    @Test
    fun `RefreshShopsUseCase delegates to repository`() = runBlocking {
        val useCase = RefreshShopsUseCase(repo)
        useCase()          // invoke
        assertEquals(1, repo.refreshCalls)
    }
}
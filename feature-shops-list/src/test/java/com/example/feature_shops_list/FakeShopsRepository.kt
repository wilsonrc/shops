package com.example.feature_shops_list

import com.example.domain.model.Shop
import com.example.domain.repository.ShopsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeShopsRepository(initial: List<Shop> = emptyList()) : ShopsRepository {
    private val backing = MutableStateFlow(initial)
    var refreshCalls = 0
        private set

    override fun observeShops(): Flow<List<Shop>> = backing.asStateFlow()

    override suspend fun getShop(id: String): Shop? =
        backing.value.firstOrNull { it.id == id }

    override suspend fun refresh() {
        refreshCalls++
    }

    fun setShops(items: List<Shop>) {
        backing.value = items
    }
}
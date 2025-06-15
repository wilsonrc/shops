package com.example.data

import com.example.data.local.ShopDao
import com.example.data.model.entity.ShopEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeShopDao(initial: List<ShopEntity> = emptyList()) : ShopDao {

    private val backing = MutableStateFlow(initial)

    override fun observeAll(): Flow<List<ShopEntity>> = backing.asStateFlow()

    override suspend fun getById(id: String): ShopEntity? =
        backing.value.firstOrNull { it.id == id }

    override suspend fun insertAll(items: List<ShopEntity>) {
        backing.value = items
    }
}
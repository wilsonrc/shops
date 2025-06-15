package com.example.data.repository

import com.example.data.local.ShopDao
import com.example.data.model.mapper.ShopMapper
import com.example.data.remote.AssetShopsDataSource
import com.example.domain.model.Shop
import com.example.domain.repository.ShopsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopsRepositoryImpl @Inject constructor(
    private val dao: ShopDao,
    private val remote: AssetShopsDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ShopsRepository {

    override fun observeShops(): Flow<List<Shop>> =
        dao.observeAll().map { list -> list.map(ShopMapper::entityToDomain) }

    override suspend fun getShop(id: String): Shop? =
        dao.getById(id)?.let(ShopMapper::entityToDomain)

    override suspend fun refresh() = withContext(dispatcher) {
        val dtos = remote.load()
        val entities = dtos.map(ShopMapper::dtoToEntity)
        dao.insertAll(entities)
    }
}
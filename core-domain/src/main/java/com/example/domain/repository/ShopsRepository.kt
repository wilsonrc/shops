package com.example.domain.repository

import com.example.domain.model.Shop
import kotlinx.coroutines.flow.Flow

/**
 * Single abstraction point for all shop‑related data.
 * Implemented in the data layer and consumed by ViewModels / UseCases.
 */
interface ShopsRepository {

    /** Continuously observe the list of shops, emitting updates on change. */
    fun observeShops(): Flow<List<Shop>>

    /** Retrieve a single shop by its stable [id]. */
    suspend fun getShop(id: String): Shop?

    /** Force a refresh from the upstream (assets, network, etc.). */
    suspend fun refresh()
}
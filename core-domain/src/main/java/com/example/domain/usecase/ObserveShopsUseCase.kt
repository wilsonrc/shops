package com.example.domain.usecase

import com.example.domain.model.Shop
import com.example.domain.repository.ShopsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveShopsUseCase @Inject constructor(
    private val repository: ShopsRepository
) {
    operator fun invoke(): Flow<List<Shop>> = repository.observeShops()
}
package com.example.domain.usecase

import com.example.domain.model.Shop
import com.example.domain.repository.ShopsRepository
import javax.inject.Inject

class GetShopUseCase @Inject constructor(
    private val repository: ShopsRepository
) {
    suspend operator fun invoke(id: String): Shop? = repository.getShop(id)
}
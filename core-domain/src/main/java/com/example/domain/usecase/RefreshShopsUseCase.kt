package com.example.domain.usecase

import com.example.domain.repository.ShopsRepository
import javax.inject.Inject

class RefreshShopsUseCase @Inject constructor(
    private val repository: ShopsRepository
) {
    suspend operator fun invoke() = repository.refresh()
}
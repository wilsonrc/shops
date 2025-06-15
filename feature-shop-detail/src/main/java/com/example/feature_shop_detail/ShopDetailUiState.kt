package com.example.feature_shop_detail

import com.example.domain.model.Shop

sealed interface ShopDetailUiState {
    object Loading : ShopDetailUiState
    data class Ready(val shop: Shop) : ShopDetailUiState
    data class Error(val message: String) : ShopDetailUiState
}
package com.example.feature_shops_list

import com.example.domain.model.Shop

sealed interface ShopsListUiState {
    object Loading : ShopsListUiState
    data class Ready(val shops: List<Shop>) : ShopsListUiState
    data class Error(val message: String) : ShopsListUiState
}
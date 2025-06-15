package com.example.feature_shop_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetShopUseCase
import com.example.feature_shop_detail.navigation.DetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopDetailViewModel @Inject constructor(
    private val getShop: GetShopUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<ShopDetailUiState>(ShopDetailUiState.Loading)
    val state: StateFlow<ShopDetailUiState> = _state

    init {
        val id: String? = savedStateHandle[DetailRoute.ARG_ID]
        if (id == null) {
            _state.value = ShopDetailUiState.Error("Missing id")
        } else {
            viewModelScope.launch {
                val shop = getShop(id)
                _state.value = shop?.let { ShopDetailUiState.Ready(it) }
                    ?: ShopDetailUiState.Error("Not found")
            }
        }
    }
}
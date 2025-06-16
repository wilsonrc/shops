package com.example.feature_shops_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ObserveShopsUseCase
import com.example.domain.usecase.RefreshShopsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopsListViewModel @Inject constructor(
    observeShops: ObserveShopsUseCase,
    private val refreshShops: RefreshShopsUseCase
) : ViewModel() {

    val state: StateFlow<ShopsListUiState> =
        observeShops()
            .map<_, ShopsListUiState> { ShopsListUiState.Ready(it) }
            .onStart {
                emit(ShopsListUiState.Loading)
                refresh()
            }
            .catch { emit(ShopsListUiState.Error(it.message ?: "error")) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                ShopsListUiState.Loading
            )

    fun refresh() = viewModelScope.launch { refreshShops() }
}
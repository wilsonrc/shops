package com.example.feature_shops_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core_ui.components.ErrorFullScreen
import com.example.core_ui.components.RatingStars
import com.example.core_ui.components.RemoteImage
import com.example.feature_shop_detail.navigation.DetailRoute

@Composable
fun ShopsListScreen(
    navController: NavController,
    viewModel: ShopsListViewModel = hiltViewModel()
) {
    when (val ui = viewModel.state.collectAsState().value) {
        is ShopsListUiState.Loading -> CircularProgressIndicator()
        is ShopsListUiState.Error   -> ErrorFullScreen(ui.message, { viewModel.refresh()})
        is ShopsListUiState.Ready   -> LazyColumn {
            items(ui.shops) { shop ->
                ListItem(
                    headlineContent    = { Text(shop.name) },
                    supportingContent  = { Text(shop.address) },
                    leadingContent     = { RemoteImage(shop.picture, Modifier.size(56.dp)) },
                    trailingContent    = { RatingStars(shop.rating) },
                    modifier           = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(DetailRoute.build(shop.id)) }
                )
                Divider()
            }
        }
    }
}
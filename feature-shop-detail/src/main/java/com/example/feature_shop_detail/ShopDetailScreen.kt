package com.example.feature_shop_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.components.ErrorFullScreen
import com.example.core_ui.components.RatingStars
import com.example.core_ui.components.RemoteImage

@Composable
fun ShopDetailScreen(
    viewModel: ShopDetailViewModel = hiltViewModel()
) {
    val ui = viewModel.state.collectAsState().value
    val ctx = LocalContext.current

    when (ui) {
        is ShopDetailUiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is ShopDetailUiState.Error -> ErrorFullScreen(ui.message, {})

        is ShopDetailUiState.Ready -> with(ui.shop) {
            val scroll = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RemoteImage(
                        url = picture,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(text = name, style = MaterialTheme.typography.headlineSmall)

                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingStars(rating)
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = String.format("%.1f", rating),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(description, style = MaterialTheme.typography.bodyLarge)

                Spacer(Modifier.height(24.dp))

                OutlinedButton(
                    onClick = {
                        val geo = Uri.parse("geo:$latitude,$longitude?q=${Uri.encode(address)}")
                        ctx.startActivity(Intent(Intent.ACTION_VIEW, geo))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(address) }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(website))) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Visit website") }
            }
        }
    }
}
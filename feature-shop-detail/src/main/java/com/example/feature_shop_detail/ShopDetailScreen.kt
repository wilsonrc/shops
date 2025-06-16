package com.example.feature_shop_detail

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.components.ErrorFullScreen
import com.example.core_ui.components.RatingStars
import com.example.core_ui.components.RemoteImage
import com.example.core_ui.theme.SakeTheme
import com.example.domain.model.Shop

@Composable
fun ShopDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopDetailViewModel = hiltViewModel()
) {
    val ui by viewModel.state.collectAsState()
    val ctx = LocalContext.current

    when (ui) {
        is ShopDetailUiState.Loading -> LoadingIndicator(modifier)
        is ShopDetailUiState.Error   -> ErrorFullScreen(
            (ui as ShopDetailUiState.Error).message,
            onRetry = {},
            modifier = modifier
        )
        is ShopDetailUiState.Ready   -> {
            val shop = (ui as ShopDetailUiState.Ready).shop
            ShopDetailContent(
                shop = shop,
                onNavigateToMap = { lat, lon, address ->
                    val geo = Uri.parse("geo:$lat,$lon?q=${Uri.encode(address)}")
                    ctx.startActivity(Intent(Intent.ACTION_VIEW, geo))
                },
                onOpenWebsite = { url ->
                    ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                modifier = modifier
            )
        }
    }
}

@Composable
private fun ShopDetailContent(
    shop: Shop,
    onNavigateToMap: (Double, Double, String) -> Unit,
    onOpenWebsite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {
        ShopHeader(shop.picture)
        Spacer(Modifier.height(16.dp))
        ShopTitle(shop.name, shop.rating)
        Spacer(Modifier.height(12.dp))
        Text(shop.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(24.dp))
        ShopActions(
            address   = shop.address,
            latitude  = shop.latitude,
            longitude = shop.longitude,
            website   = shop.website,
            onNavigateToMap = onNavigateToMap,
            onOpenWebsite   = onOpenWebsite
        )
    }
}

@Composable
private fun ShopHeader(picture: String?, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        RemoteImage(
            url          = picture,
            modifier     = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun ShopTitle(name: String, rating: Double, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(name, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingStars(rating)
            Spacer(Modifier.width(6.dp))
            Text(String.format("%.1f", rating), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun ShopActions(
    address: String,
    latitude: Double,
    longitude: Double,
    website: String,
    onNavigateToMap: (Double, Double, String) -> Unit,
    onOpenWebsite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick  = { onNavigateToMap(latitude, longitude, address) },
            modifier = Modifier.fillMaxWidth()
        ) { Text(address) }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick  = { onOpenWebsite(website) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Visit website") }
    }
}

@Composable
private fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
}

/* Sample data for previews */
private val sampleShop = Shop(
    id          = "preview",
    name        = "信州スシサカバ 寿しなの",
    description = "Sushi bar with a variety of sake options.",
    picture     = null,
    rating      = 4.3,
    address     = "〒380-0824 長野県長野市南長野南石堂町1421",
    latitude    = 36.644257,
    longitude   = 138.18668,
    mapsUrl     = "",
    website     = "https://www.sushinano.com/"
)

@Preview(
    name = "ShopDetail • Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ShopDetailPreviewLight() {
    SakeTheme(darkTheme = false) {
        ShopDetailContent(
            shop            = sampleShop,
            onNavigateToMap = { _, _, _ -> },
            onOpenWebsite   = {},
        )
    }
}

@Preview(
    name = "ShopDetail • Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ShopDetailPreviewDark() {
    SakeTheme(darkTheme = true) {
        ShopDetailContent(
            shop            = sampleShop,
            onNavigateToMap = { _, _, _ -> },
            onOpenWebsite   = {},
        )
    }
}
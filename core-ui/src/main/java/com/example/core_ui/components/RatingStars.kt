package com.example.core_ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.core_ui.R

@Composable
fun RatingStars(
    rating: Double,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        val full = rating.toInt().coerceIn(0, 5)
        for (i in 1..5) {
            val tint = if (i <= full)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = tint
            )
        }
    }
}
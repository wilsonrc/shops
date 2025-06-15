package com.example.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun SakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val scheme = if (darkTheme)
        darkColorScheme(primary = md_theme_dark_primary)
    else
        lightColorScheme(primary = md_theme_light_primary)

    MaterialTheme(colorScheme = scheme, typography = AppTypography, content = content)
}
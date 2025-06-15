package com.example.core_ui

import com.example.core_ui.theme.md_theme_dark_primary
import com.example.core_ui.theme.md_theme_light_primary
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ThemeTest {

    @Test
    fun `primary colors for light and dark themes are different`() {
        assertNotEquals(md_theme_light_primary, md_theme_dark_primary)
    }
}
package com.example.feature_shop_detail

import com.example.feature_shop_detail.navigation.DetailRoute
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailRouteTest {

    @Test
    fun `build() concatenates id to base path`() {
        val id = "42"
        val expected = "shops/detail/42"
        assertEquals(expected, DetailRoute.build(id))
    }
}
package com.example.feature_shops_list

import com.example.feature_shops_list.navigation.ListRoute
import org.junit.Assert.assertEquals
import org.junit.Test

class ListRouteTest {

    @Test
    fun `route constant equals expected path`() {
        assertEquals("shops/list", ListRoute.route)
    }
}
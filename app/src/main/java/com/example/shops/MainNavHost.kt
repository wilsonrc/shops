package com.example.shops

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_shops_list.navigation.ListRoute
import com.example.feature_shops_list.ShopsListScreen
import com.example.feature_shop_detail.navigation.DetailRoute
import com.example.feature_shop_detail.ShopDetailScreen

@Composable
fun MainNavHost() {
    val nav = rememberNavController()
    NavHost(nav, startDestination = ListRoute.route) {
        composable(ListRoute.route) { ShopsListScreen(nav) }
        composable("${DetailRoute.route}/{${DetailRoute.ARG_ID}}") { ShopDetailScreen() }
    }
}
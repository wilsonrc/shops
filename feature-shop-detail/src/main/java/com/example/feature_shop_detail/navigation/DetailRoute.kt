package com.example.feature_shop_detail.navigation

object DetailRoute {
    const val route = "shops/detail"
    const val ARG_ID = "id"
    fun build(id: String) = "$route/$id"
}
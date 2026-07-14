package com.example.BE.domain.route.presentation.dto.response

data class RouteSpotItemResponse(
    val spotId: Long,
    val contentId: Long,
    val contentTitle: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val kakaoPlaceId: String,
    val imageUrl: String,
    val sceneDescription: String,
    val mapLink: String,
    val selectedOrder: Int,
)

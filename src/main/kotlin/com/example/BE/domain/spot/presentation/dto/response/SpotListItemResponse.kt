package com.example.BE.domain.spot.presentation.dto.response

data class SpotListItemResponse(
    val spotId: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val kakaoPlaceId: String,
    val imageUrl: String,
    val sceneDescription: String,
    val mapLink: String,
)

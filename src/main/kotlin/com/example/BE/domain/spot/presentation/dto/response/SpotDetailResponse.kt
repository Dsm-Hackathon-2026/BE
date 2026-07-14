package com.example.BE.domain.spot.presentation.dto.response

data class SpotDetailResponse(
    val spotId: Long,
    val contentId: Long,
    val contentTitle: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val kakaoPlaceId: String,
    val imageUrl: String,
    val description: String,
    val mapLink: String,
    val totalVerifications: Int,
)

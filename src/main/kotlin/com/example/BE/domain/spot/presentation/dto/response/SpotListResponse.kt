package com.example.BE.domain.spot.presentation.dto.response

data class SpotListResponse(
    val content: List<SpotListItemResponse>,
    val totalElements: Int,
)

package com.example.BE.domain.route.presentation.dto.response

data class RouteSpotListResponse(
    val content: List<RouteSpotItemResponse>,
    val totalElements: Int,
)

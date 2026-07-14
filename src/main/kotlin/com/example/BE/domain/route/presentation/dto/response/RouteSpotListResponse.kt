package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class RouteSpotListResponse(
    val content: List<RouteSpotItemResponse>,

    @field:Schema(example = "3")
    val totalElements: Int,
)

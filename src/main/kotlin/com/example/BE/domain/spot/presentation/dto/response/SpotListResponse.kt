package com.example.BE.domain.spot.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class SpotListResponse(
    val content: List<SpotListItemResponse>,

    @field:Schema(example = "5")
    val totalElements: Int,
)

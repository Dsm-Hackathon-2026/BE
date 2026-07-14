package com.example.BE.domain.route.presentation.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AiRecommendationRequest(
    @field:Schema(example = "서울특별시 강서구 화곡로 168")
    val startAddress: String,

    @field:Schema(example = "서울특별시 강서구 공항대로 260")
    val destAddress: String,

    @field:Schema(example = "13:00")
    val startTime: String,
)

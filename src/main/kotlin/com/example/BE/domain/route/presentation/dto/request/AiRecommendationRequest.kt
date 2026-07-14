package com.example.BE.domain.route.presentation.dto.request

import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AiRecommendationRequest(
    val startAddress: String,
    val destAddress: String,
    val startTime: String,
)

package com.example.BE.domain.route.presentation.dto.response

import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AiRecommendationMetaResponse(
    val startPlace: String,
    val destination: String,
)

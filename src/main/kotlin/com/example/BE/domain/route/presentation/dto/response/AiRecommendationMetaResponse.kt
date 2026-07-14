package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AiRecommendationMetaResponse(
    @field:Schema(example = "화곡역 5호선")
    val startPlace: String,

    @field:Schema(example = "이대서울병원")
    val destination: String,
)

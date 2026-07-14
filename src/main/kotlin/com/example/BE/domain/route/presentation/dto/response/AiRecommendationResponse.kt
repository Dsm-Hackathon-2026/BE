package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AiRecommendationResponse(
    @field:Schema(example = "200")
    val status: Int,

    val meta: AiRecommendationMetaResponse,

    @field:Schema(example = "병원 방문객을 위한 든든한 한정식 한 끼와 아늑한 카페 휴식 코스")
    val courseConcept: String,

    val timeline: List<TimelineItemResponse>,
)

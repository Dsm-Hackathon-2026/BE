package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AiRecommendationMetaResponse(
    @field:Schema(example = "화곡역 5호선")
    val startPlace: String? = null,

    @field:Schema(example = "서울 강서구 화곡로 지하 168")
    val startAddress: String? = null,

    @field:Schema(example = "이대서울병원")
    val destination: String? = null,

    @field:Schema(example = "[\"이대서울병원\", \"누리꿈스퀘어\"]")
    val destinations: List<String>? = null,
)

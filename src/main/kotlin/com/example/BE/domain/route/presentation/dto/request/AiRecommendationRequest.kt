package com.example.BE.domain.route.presentation.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class AiRecommendationRequest(
    @field:Schema(example = "서울특별시 강서구 화곡로 168")
    val startAddress: String,

    @field:Schema(example = "서울특별시 강서구 공항대로 260")
    val destAddress: String,

    @field:Schema(example = "13:00")
    val startTime: String,
)

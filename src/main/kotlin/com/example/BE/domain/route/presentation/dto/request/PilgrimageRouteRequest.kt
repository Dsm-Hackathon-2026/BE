package com.example.BE.domain.route.presentation.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class PilgrimageRouteRequest(
    @field:Schema(example = "서울특별시 중구 세종대로 110")
    val startAddress: String,

    @field:Schema(example = "13:00")
    val startTime: String,

    @field:Schema(example = "[1, 2, 3]")
    val spotIds: List<Long>,
)

package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class RouteRemoveResponse(
    @field:Schema(example = "1")
    val spotId: Long,

    @field:Schema(example = "true")
    val removed: Boolean,
)

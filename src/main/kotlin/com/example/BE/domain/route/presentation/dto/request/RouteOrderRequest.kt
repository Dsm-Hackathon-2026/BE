package com.example.BE.domain.route.presentation.dto.request

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

data class RouteOrderRequest(
    @field:ArraySchema(schema = Schema(example = "1"))
    val spotIds: List<Long>,
)

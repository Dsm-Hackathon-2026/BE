package com.example.BE.domain.content.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class ContentSliceResponse(
    val content: List<ContentCardResponse>,

    @field:Schema(example = "5")
    val limit: Int,

    @field:Schema(example = "5")
    val totalElements: Int,

    @field:Schema(example = "true")
    val last: Boolean,
)

package com.example.BE.domain.content.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class ContentCardResponse(
    @field:Schema(example = "1")
    val contentId: Long,

    @field:Schema(example = "선재 업고 튀어")
    val title: String,

    @field:Schema(example = "DRAMA")
    val contentType: String,

    @field:Schema(example = "https://example.com/content/thumbnail.jpg")
    val thumbnailUrl: String,

    @field:Schema(example = "5")
    val spotCount: Int,
)

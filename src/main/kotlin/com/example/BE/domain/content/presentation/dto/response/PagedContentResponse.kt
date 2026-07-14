package com.example.BE.domain.content.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class PagedContentResponse(
    val content: List<ContentCardResponse>,

    @field:Schema(example = "0")
    val page: Int,

    @field:Schema(example = "10")
    val size: Int,

    @field:Schema(example = "24")
    val totalElements: Int,

    @field:Schema(example = "false")
    val last: Boolean,
)

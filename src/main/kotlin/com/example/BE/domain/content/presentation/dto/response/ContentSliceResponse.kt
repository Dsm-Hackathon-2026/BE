package com.example.BE.domain.content.presentation.dto.response

data class ContentSliceResponse(
    val content: List<ContentCardResponse>,
    val limit: Int,
    val totalElements: Int,
    val last: Boolean,
)

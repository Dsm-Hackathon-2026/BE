package com.example.BE.domain.content.presentation.dto.response

data class ContentCardResponse(
    val contentId: Long,
    val title: String,
    val contentType: String,
    val thumbnailUrl: String,
    val spotCount: Int,
)

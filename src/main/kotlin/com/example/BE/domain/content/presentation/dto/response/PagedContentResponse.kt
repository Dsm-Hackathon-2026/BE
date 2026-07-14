package com.example.BE.domain.content.presentation.dto.response

data class PagedContentResponse(
    val content: List<ContentCardResponse>,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val last: Boolean,
)

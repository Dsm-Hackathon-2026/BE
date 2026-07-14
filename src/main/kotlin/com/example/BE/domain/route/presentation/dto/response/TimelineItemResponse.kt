package com.example.BE.domain.route.presentation.dto.response

data class TimelineItemResponse(
    val time: String,
    val place: String,
    val activity: String,
    val address: String? = null,
)

package com.example.BE.domain.verification.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class VerificationHistoryResponse(
    val content: List<VerificationHistoryItemResponse>,

    @field:Schema(example = "0")
    val page: Int,

    @field:Schema(example = "10")
    val size: Int,

    @field:Schema(example = "12")
    val totalElements: Int,

    @field:Schema(example = "2")
    val totalPages: Int,

    @field:Schema(example = "false")
    val last: Boolean,
)

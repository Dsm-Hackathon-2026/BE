package com.example.BE.domain.verification.presentation.dto.response

data class VerificationHistoryResponse(
    val content: List<VerificationHistoryItemResponse>,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val last: Boolean,
)

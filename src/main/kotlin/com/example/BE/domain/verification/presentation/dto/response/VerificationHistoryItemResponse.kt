package com.example.BE.domain.verification.presentation.dto.response

data class VerificationHistoryItemResponse(
    val verificationId: Long,
    val spotId: Long,
    val spotName: String,
    val contentTitle: String,
    val imageUrl: String,
    val verifiedAt: String,
)

package com.example.BE.domain.verification.presentation.dto.response

data class VerificationResultResponse(
    val verificationId: Long,
    val spotId: Long,
    val spotName: String,
    val contentTitle: String,
    val imageUrl: String,
    val distanceMeters: Double,
    val status: String,
    val verifiedAt: String,
    val badgeName: String?,
)

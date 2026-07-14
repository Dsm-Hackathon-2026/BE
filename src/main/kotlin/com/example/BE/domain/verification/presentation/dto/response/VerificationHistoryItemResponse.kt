package com.example.BE.domain.verification.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class VerificationHistoryItemResponse(
    @field:Schema(example = "1")
    val verificationId: Long,

    @field:Schema(example = "1")
    val spotId: Long,

    @field:Schema(example = "강릉 연진 해변")
    val spotName: String,

    @field:Schema(example = "선재 업고 튀어")
    val contentTitle: String,

    @field:Schema(example = "https://sdsr-bucket.s3.ap-northeast-2.amazonaws.com/verifications/sample.jpg")
    val imageUrl: String,

    @field:Schema(example = "2026-07-14T10:30:00")
    val verifiedAt: String,
)

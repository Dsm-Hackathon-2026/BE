package com.example.BE.domain.verification.presentation.dto.response

import com.example.BE.domain.verification.domain.VerificationStatus
import io.swagger.v3.oas.annotations.media.Schema

data class VerificationResultResponse(
    @field:Schema(example = "1")
    val verificationId: Long,

    @field:Schema(example = "1")
    val spotId: Long,

    @field:Schema(example = "강릉 연진 해변")
    val spotName: String,

    @field:Schema(example = "선재 업고 튀어")
    val contentTitle: String,

    @field:Schema(example = "https://example.com/spot/scene.jpg")
    val sceneImageUrl: String,

    @field:Schema(example = "https://sdsr-bucket.s3.ap-northeast-2.amazonaws.com/verifications/sample.jpg")
    val verificationImageUrl: String,

    @field:Schema(example = "SUCCESS")
    val status: VerificationStatus,

    @field:Schema(example = "2026-07-14T10:30:00Z")
    val verifiedAt: String,
)

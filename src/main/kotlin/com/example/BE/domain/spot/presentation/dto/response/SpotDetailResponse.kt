package com.example.BE.domain.spot.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class SpotDetailResponse(
    @field:Schema(example = "1")
    val spotId: Long,

    @field:Schema(example = "1")
    val contentId: Long,

    @field:Schema(example = "선재 업고 튀어")
    val contentTitle: String,

    @field:Schema(example = "강릉 연진 해변")
    val name: String,

    @field:Schema(example = "37.752175")
    val latitude: Double,

    @field:Schema(example = "128.876057")
    val longitude: Double,

    @field:Schema(example = "강원특별자치도 강릉시 주문진읍 해안로 1609")
    val address: String,

    @field:Schema(example = "https://example.com/spot/image.jpg")
    val imageUrl: String,

    @field:Schema(example = "https://example.com/verification/image.jpg")
    val verificationImageUrl: String? = null,
)

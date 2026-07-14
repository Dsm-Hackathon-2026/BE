package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class TimelineItemResponse(
    @field:Schema(example = "13:00 ~ 13:12")
    val time: String,

    @field:Schema(example = "화곡역 5호선 ➡️ 이대서울병원 이동")
    val place: String,

    @field:Schema(example = "카카오 길찾기 기준 실제 이동 시간 약 12분 소요")
    val activity: String,

    @field:Schema(example = "서울특별시 강서구 공항대로 260")
    val address: String? = null,
)

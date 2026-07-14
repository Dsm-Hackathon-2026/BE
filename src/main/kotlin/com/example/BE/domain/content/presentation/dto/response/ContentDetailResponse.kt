package com.example.BE.domain.content.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class ContentDetailResponse(
    @field:Schema(example = "1")
    val contentId: Long,

    @field:Schema(example = "선재 업고 튀어")
    val title: String,

    @field:Schema(example = "DRAMA")
    val contentType: String,

    @field:Schema(example = "https://example.com/content/thumbnail.jpg")
    val thumbnailUrl: String,

    @field:Schema(example = "과거로 돌아간 주인공이 최애를 살리기 위해 고군분투하는 로맨스 드라마입니다.")
    val description: String,

    @field:Schema(example = "2024")
    val releaseYear: Int,

    @field:Schema(example = "한국")
    val country: String,
)

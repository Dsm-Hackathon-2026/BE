package com.example.BE.domain.route.presentation.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class RouteSpotItemResponse(
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

    @field:Schema(example = "123456789")
    val kakaoPlaceId: String,

    @field:Schema(example = "https://example.com/spot/image.jpg")
    val imageUrl: String,

    @field:Schema(example = "주인공들이 바닷가를 걷는 장면이 촬영된 장소입니다.")
    val sceneDescription: String,

    @field:Schema(example = "https://map.kakao.com/link/map/강릉 연진 해변,37.752175,128.876057")
    val mapLink: String,

    @field:Schema(example = "1")
    val selectedOrder: Int,
)

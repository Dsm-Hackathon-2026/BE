package com.example.BE.domain.spot.presentation

import com.example.BE.domain.spot.presentation.dto.response.SpotDetailResponse
import com.example.BE.domain.spot.presentation.dto.response.SpotListResponse
import com.example.BE.domain.spot.service.SpotService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/spots")
@Tag(name = "spots", description = "촬영지 API")
class SpotController(
    private val spotService: SpotService,
) {
    @GetMapping("/{contentId}")
    @Operation(
        summary = "촬영지 목록 조회",
        description = "콘텐츠 ID에 해당하는 촬영지 목록을 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "촬영지 목록 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getSpots(@PathVariable contentId: Long): SpotListResponse {
        return spotService.spotsByContent(contentId)
    }

    @GetMapping("/{contentId}/{spotId}")
    @Operation(
        summary = "촬영지 상세 조회",
        description = "콘텐츠 ID와 촬영지 ID로 촬영지 상세 정보를 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "촬영지 상세 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getSpotDetail(
        @PathVariable contentId: Long,
        @PathVariable spotId: Long,
    ): SpotDetailResponse {
        return spotService.spotDetail(contentId, spotId)
    }
}

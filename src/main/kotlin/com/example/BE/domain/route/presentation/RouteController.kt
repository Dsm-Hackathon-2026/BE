package com.example.BE.domain.route.presentation

import com.example.BE.domain.route.presentation.dto.request.AiRecommendationRequest
import com.example.BE.domain.route.presentation.dto.request.RouteOrderRequest
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationResponse
import com.example.BE.domain.route.presentation.dto.response.RouteAddResponse
import com.example.BE.domain.route.presentation.dto.response.RouteRemoveResponse
import com.example.BE.domain.route.presentation.dto.response.RouteSpotListResponse
import com.example.BE.domain.route.service.RouteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/routes")
@Tag(name = "routes", description = "방문 코스 API")
class RouteController(
    private val routeService: RouteService,
) {
    @PostMapping("/{spotId}")
    @Operation(
        summary = "방문할 촬영지 등록",
        description = "방문 예정 코스에 촬영지를 등록합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "방문할 촬영지 등록 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun addRouteSpot(@PathVariable spotId: Long): RouteAddResponse {
        return routeService.addRouteSpot(spotId)
    }

    @DeleteMapping("/{spotId}")
    @Operation(
        summary = "방문할 촬영지 선택 해제",
        description = "방문 예정 코스에서 선택한 촬영지를 제거합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "방문할 촬영지 선택 해제 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun removeRouteSpot(@PathVariable spotId: Long): RouteRemoveResponse {
        return routeService.removeRouteSpot(spotId)
    }

    @GetMapping("/spots")
    @Operation(
        summary = "등록한 촬영지 목록 조회",
        description = "방문 예정 코스에 등록한 촬영지 목록과 지도 표시에 필요한 좌표, 카카오 장소 ID, 지도 링크를 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "등록한 촬영지 목록 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getRouteSpots(): RouteSpotListResponse {
        return routeService.routeSpots()
    }

    @PutMapping("/order")
    @Operation(
        summary = "루트 순서 변경",
        description = "앱에서 드래그로 변경한 촬영지 방문 순서를 저장합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "루트 순서 변경 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun reorderRouteSpots(@RequestBody request: RouteOrderRequest): RouteSpotListResponse {
        return routeService.reorderRouteSpots(request)
    }

    @PostMapping("/recommended")
    @Operation(
        summary = "AI 추천 요청",
        description = "출발지, 목적지, 출발 시간을 기반으로 AI 맞춤 타임라인 코스를 추천합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "AI 추천 요청 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun recommendRoute(@RequestBody request: AiRecommendationRequest): AiRecommendationResponse {
        return routeService.recommendRoute(request)
    }
}

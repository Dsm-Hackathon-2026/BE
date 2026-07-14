package com.example.BE.domain.route.presentation

import com.example.BE.domain.route.presentation.dto.request.AiRecommendationRequest
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationResponse
import com.example.BE.domain.route.service.RouteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
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

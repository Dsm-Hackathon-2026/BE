package com.example.BE.domain.route.service

import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import com.example.BE.domain.route.presentation.dto.request.AiRecommendationRequest
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientException
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@Service
class RouteService(
    @Value("\${ai.base-url:http://localhost:8000}")
    private val aiBaseUrl: String,

    @Value("\${ai.recommend-path:/generate-plan}")
    private val recommendPath: String,
) {
    fun recommendRoute(request: AiRecommendationRequest): AiRecommendationResponse {
        val restClient = RestClient.builder()
            .baseUrl(aiBaseUrl)
            .build()

        return try {
            restClient.post()
                .uri(recommendPath)
                .body(request.toAiRequest())
                .retrieve()
                .body(AiRecommendationResponse::class.java)
                ?: throw SdsrException(ErrorCode.AI_SERVER_UNAVAILABLE)
        } catch (exception: RestClientException) {
            throw SdsrException(ErrorCode.AI_SERVER_UNAVAILABLE)
        }
    }

    private fun AiRecommendationRequest.toAiRequest(): AiPlannerRequest =
        AiPlannerRequest(
            startAddress = startAddress,
            destAddress = destAddress,
            startTime = startTime,
        )
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
private data class AiPlannerRequest(
    val startAddress: String,
    val destAddress: String,
    val startTime: String,
)

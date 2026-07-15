package com.example.BE.domain.route.service

import com.example.BE.common.exception.AiServerException
import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import com.example.BE.domain.route.presentation.dto.request.AiRecommendationRequest
import com.example.BE.domain.route.presentation.dto.request.PilgrimageRouteRequest
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationResponse
import com.example.BE.domain.spot.facade.SpotFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientException
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.annotation.JsonNaming

@Service
class RouteService(
    private val spotFacade: SpotFacade,

    @Value("\${ai.base-url}")
    private val aiBaseUrl: String,

    @Value("\${ai.recommend-path}")
    private val recommendPath: String,

    @Value("\${ai.pilgrimage-path}")
    private val pilgrimagePath: String,
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
        } catch (exception: HttpStatusCodeException) {
            throw AiServerException(
                statusCode = exception.statusCode,
                responseBody = exception.responseBodyAsString,
            )
        } catch (exception: RestClientException) {
            throw SdsrException(ErrorCode.AI_SERVER_UNAVAILABLE)
        }
    }

    fun recommendPilgrimageRoute(request: PilgrimageRouteRequest): AiRecommendationResponse {
        if (request.spotIds.distinct().size < MIN_PILGRIMAGE_SPOT_COUNT) {
            throw SdsrException(ErrorCode.INVALID_REQUEST)
        }

        val restClient = RestClient.builder()
            .baseUrl(aiBaseUrl)
            .build()

        return try {
            restClient.post()
                .uri(pilgrimagePath)
                .body(request.toAiRequest())
                .retrieve()
                .body(AiRecommendationResponse::class.java)
                ?: throw SdsrException(ErrorCode.AI_SERVER_UNAVAILABLE)
        } catch (exception: HttpStatusCodeException) {
            throw AiServerException(
                statusCode = exception.statusCode,
                responseBody = exception.responseBodyAsString,
            )
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

    private fun PilgrimageRouteRequest.toAiRequest(): AiPilgrimageRequest =
        AiPilgrimageRequest(
            startAddress = startAddress,
            destAddresses = spotIds.distinct().map { spotId ->
                spotFacade.getSpot(spotId).address
            },
            startTime = startTime,
        )

    companion object {
        private const val MIN_PILGRIMAGE_SPOT_COUNT = 2
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
private data class AiPlannerRequest(
    val startAddress: String,
    val destAddress: String,
    val startTime: String,
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
private data class AiPilgrimageRequest(
    val startAddress: String,
    val destAddresses: List<String>,
    val startTime: String,
)

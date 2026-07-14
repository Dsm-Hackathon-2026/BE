package com.example.BE.domain.route.service

import com.example.BE.domain.route.facade.RouteSpotFacade
import com.example.BE.domain.route.presentation.dto.request.AiRecommendationRequest
import com.example.BE.domain.route.presentation.dto.request.RouteOrderRequest
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationMetaResponse
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationResponse
import com.example.BE.domain.route.presentation.dto.response.RouteAddResponse
import com.example.BE.domain.route.presentation.dto.response.RouteRemoveResponse
import com.example.BE.domain.route.presentation.dto.response.RouteSpotItemResponse
import com.example.BE.domain.route.presentation.dto.response.RouteSpotListResponse
import com.example.BE.domain.route.presentation.dto.response.TimelineItemResponse
import com.example.BE.domain.spot.domain.Spot
import com.example.BE.domain.spot.facade.SpotFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RouteService(
    private val spotFacade: SpotFacade,
    private val routeSpotFacade: RouteSpotFacade,
) {
    @Transactional
    fun addRouteSpot(spotId: Long): RouteAddResponse {
        val spot = spotFacade.getSpot(spotId)

        if (!routeSpotFacade.existsRouteSpot(spotId)) {
            val nextOrder = routeSpotFacade.findAllRouteSpots().size + 1
            routeSpotFacade.saveRouteSpot(spot = spot, selectedOrder = nextOrder)
        }

        return RouteAddResponse(spotId = spotId, added = true)
    }

    @Transactional
    fun removeRouteSpot(spotId: Long): RouteRemoveResponse {
        val routeSpot = routeSpotFacade.findRouteSpot(spotId)
        val removed = routeSpot != null
        if (routeSpot != null) {
            routeSpotFacade.deleteRouteSpot(routeSpot)
            reorderPersistedRouteSpots()
        }

        return RouteRemoveResponse(spotId = spotId, removed = removed)
    }

    @Transactional
    fun reorderRouteSpots(request: RouteOrderRequest): RouteSpotListResponse {
        routeSpotFacade.deleteAllRouteSpots()
        request.spotIds.forEachIndexed { index, spotId ->
            val spot = spotFacade.getSpot(spotId)
            routeSpotFacade.saveRouteSpot(spot = spot, selectedOrder = index + 1)
        }

        return routeSpots()
    }

    fun routeSpots(): RouteSpotListResponse {
        val items = routeSpotFacade.findAllRouteSpots()
            .map { it.spot.toRouteItem(it.selectedOrder) }

        return RouteSpotListResponse(content = items, totalElements = items.size)
    }

    fun recommendRoute(request: AiRecommendationRequest): AiRecommendationResponse =
        AiRecommendationResponse(
            status = 200,
            meta = AiRecommendationMetaResponse(
                startPlace = "화곡역 5호선",
                destination = "이대서울병원",
            ),
            courseConcept = "병원 방문객을 위한 든든한 한정식 한 끼와 아늑한 카페 휴식, 필요한 물품까지 챙기는 편안한 마곡 힐링 코스",
            timeline = listOf(
                TimelineItemResponse(
                    time = "${request.startTime} ~ 13:12",
                    place = "🚗 화곡역 5호선 ➡️ 이대서울병원 이동",
                    activity = "카카오 길찾기 기준 실제 이동 시간 약 12분 소요",
                ),
                TimelineItemResponse(
                    time = "13:12 ~ 15:12",
                    place = "이대서울병원",
                    activity = "메인 일정 수행 (업무/진료/관광 등)",
                ),
                TimelineItemResponse(
                    time = "15:16 ~ 16:16",
                    place = "🛍️ JY (슈퍼마켓)",
                    activity = "이동(4분) 후 소상공인 소품샵/옷가게 구경 및 가치 소비",
                    address = "서울특별시 강서구 공항대로 227",
                ),
                TimelineItemResponse(
                    time = "16:16 ~ 17:16",
                    place = "🍽️ 마곡사위식당 (백반/한정식)",
                    address = "서울특별시 강서구 공항대로 227",
                    activity = "이동(0분) 후 지역 맛집에서 맛있는 식사",
                ),
                TimelineItemResponse(
                    time = "17:27 ~ 18:57",
                    place = "☕ 빽다방강서 (카페)",
                    address = "서울특별시 강서구 화곡로 318-1",
                    activity = "이동(11분) 후 감성 로컬 카페에서 디저트 및 티타임",
                ),
            ),
        )

    private fun reorderPersistedRouteSpots() {
        routeSpotFacade.findAllRouteSpots()
            .forEachIndexed { index, routeSpot ->
                routeSpot.selectedOrder = index + 1
                routeSpotFacade.save(routeSpot)
            }
    }

    private fun Spot.toRouteItem(selectedOrder: Int): RouteSpotItemResponse =
        RouteSpotItemResponse(
            spotId = id ?: 0,
            contentId = content.id ?: 0,
            contentTitle = content.title,
            name = name,
            latitude = latitude,
            longitude = longitude,
            kakaoPlaceId = kakaoPlaceId,
            imageUrl = imageUrl,
            sceneDescription = sceneDescription,
            mapLink = "https://map.kakao.com/link/map/$name,$latitude,$longitude",
            selectedOrder = selectedOrder,
        )
}

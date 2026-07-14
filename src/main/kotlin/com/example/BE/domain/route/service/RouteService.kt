package com.example.BE.domain.route.service

import com.example.BE.domain.route.presentation.dto.request.AiRecommendationRequest
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationMetaResponse
import com.example.BE.domain.route.presentation.dto.response.AiRecommendationResponse
import com.example.BE.domain.route.presentation.dto.response.TimelineItemResponse
import org.springframework.stereotype.Service

@Service
class RouteService {
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
}

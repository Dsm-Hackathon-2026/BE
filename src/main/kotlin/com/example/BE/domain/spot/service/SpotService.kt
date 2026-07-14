package com.example.BE.domain.spot.service

import com.example.BE.domain.spot.domain.Spot
import com.example.BE.domain.spot.facade.SpotFacade
import com.example.BE.domain.spot.presentation.dto.response.SpotDetailResponse
import com.example.BE.domain.spot.presentation.dto.response.SpotListItemResponse
import com.example.BE.domain.spot.presentation.dto.response.SpotListResponse
import org.springframework.stereotype.Service

@Service
class SpotService(
    private val spotFacade: SpotFacade,
) {
    fun spotsByContent(contentId: Long): SpotListResponse {
        val items = spotFacade.findSpotsByContent(contentId)
            .map { it.toListItem() }

        return SpotListResponse(content = items, totalElements = items.size)
    }

    fun spotDetail(contentId: Long, spotId: Long): SpotDetailResponse {
        val spot = spotFacade.getSpotByContent(contentId, spotId)

        return SpotDetailResponse(
            spotId = spot.id ?: 0,
            contentId = spot.content.id ?: 0,
            contentTitle = spot.content.title,
            name = spot.name,
            latitude = spot.latitude,
            longitude = spot.longitude,
            address = spot.address,
            imageUrl = spot.imageUrl,
        )
    }

    private fun Spot.toListItem(): SpotListItemResponse {
        val verificationImageUrl = spotFacade.findSuccessVerificationImageUrl(id ?: 0)

        return SpotListItemResponse(
            spotId = id ?: 0,
            name = name,
            latitude = latitude,
            longitude = longitude,
            address = address,
            imageUrl = imageUrl,
            verified = verificationImageUrl != null,
        )
    }
}

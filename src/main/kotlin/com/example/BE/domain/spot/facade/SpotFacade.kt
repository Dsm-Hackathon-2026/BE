package com.example.BE.domain.spot.facade

import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import com.example.BE.domain.spot.domain.Spot
import com.example.BE.domain.spot.domain.repository.SpotRepository
import com.example.BE.domain.verification.domain.repository.VerificationRepository
import org.springframework.stereotype.Component

@Component
class SpotFacade(
    private val spotRepository: SpotRepository,
    private val verificationRepository: VerificationRepository,
) {
    fun getSpot(spotId: Long): Spot =
        spotRepository.findById(spotId).orElse(null)
            ?: throw SdsrException(ErrorCode.SPOT_NOT_FOUND)

    fun findSpotsByContent(contentId: Long): List<Spot> =
        spotRepository.findAllByContent_Id(contentId)

    fun getSpotByContent(contentId: Long, spotId: Long): Spot =
        spotRepository.findByIdAndContent_Id(spotId, contentId)
            ?: throw SdsrException(ErrorCode.SPOT_NOT_FOUND)

    fun countVerifications(spotId: Long): Int =
        verificationRepository.countBySpot_Id(spotId).toInt()
}

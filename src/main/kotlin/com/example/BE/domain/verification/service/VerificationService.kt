package com.example.BE.domain.verification.service

import com.example.BE.common.s3.S3Service
import com.example.BE.domain.spot.facade.SpotFacade
import com.example.BE.domain.verification.domain.Verification
import com.example.BE.domain.verification.domain.VerificationStatus
import com.example.BE.domain.verification.facade.VerificationFacade
import com.example.BE.domain.verification.presentation.dto.response.VerificationHistoryItemResponse
import com.example.BE.domain.verification.presentation.dto.response.VerificationHistoryResponse
import com.example.BE.domain.verification.presentation.dto.response.VerificationResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Service
class VerificationService(
    private val spotFacade: SpotFacade,
    private val verificationFacade: VerificationFacade,
    private val s3Service: S3Service,
) {
    @Transactional
    fun verifyVisit(
        spotId: Long,
        userLatitude: Double,
        userLongitude: Double,
        image: MultipartFile,
    ): VerificationResultResponse {
        val spot = spotFacade.getSpot(spotId)
        val distanceMeters = distanceMeters(
            userLatitude,
            userLongitude,
            spot.latitude,
            spot.longitude,
        )
        val imageUrl = s3Service.uploadVerificationImage(image)
        val status = if (distanceMeters <= 100.0) {
            VerificationStatus.SUCCESS
        } else {
            VerificationStatus.FAIL
        }
        val verifiedAt = OffsetDateTime.now(ZoneOffset.UTC).withNano(0)
        val verification = verificationFacade.saveVerification(
            spot = spot,
            imageUrl = imageUrl,
            status = status,
            verifiedAt = verifiedAt.toLocalDateTime(),
        )

        return VerificationResultResponse(
            verificationId = verification.id ?: 0,
            spotId = spotId,
            spotName = spot.name,
            contentTitle = spot.content.title,
            imageUrl = imageUrl,
            distanceMeters = "%.1f".format(distanceMeters).toDouble(),
            status = status.name,
            verifiedAt = verifiedAt.toString(),
            badgeName = if (status == VerificationStatus.SUCCESS) "방문 완료 배지" else null,
        )
    }

    @Transactional(readOnly = true)
    fun verificationHistories(page: Int, size: Int): VerificationHistoryResponse {
        val verifications = verificationFacade.findVerificationHistories(page, size)

        return VerificationHistoryResponse(
            content = verifications.content.map { it.toHistoryItem() },
            page = page,
            size = size,
            totalElements = verifications.totalElements.toInt(),
            totalPages = verifications.totalPages,
            last = verifications.isLast,
        )
    }

    private fun Verification.toHistoryItem(): VerificationHistoryItemResponse =
        VerificationHistoryItemResponse(
            verificationId = id ?: 0,
            spotId = spot.id ?: 0,
            spotName = spot.name,
            contentTitle = spot.content.title,
            imageUrl = imageUrl,
            verifiedAt = verifiedAt.toString(),
        )

    private fun distanceMeters(fromLat: Double, fromLng: Double, toLat: Double, toLng: Double): Double {
        val earthRadiusMeters = 6371000.0
        val dLat = Math.toRadians(toLat - fromLat)
        val dLng = Math.toRadians(toLng - fromLng)
        val a = kotlin.math.sin(dLat / 2) * kotlin.math.sin(dLat / 2) +
            kotlin.math.cos(Math.toRadians(fromLat)) *
            kotlin.math.cos(Math.toRadians(toLat)) *
            kotlin.math.sin(dLng / 2) *
            kotlin.math.sin(dLng / 2)
        return earthRadiusMeters * 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
    }
}

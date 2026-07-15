package com.example.BE.domain.verification.service

import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import com.example.BE.common.s3.S3Service
import com.example.BE.domain.spot.facade.SpotFacade
import com.example.BE.domain.verification.domain.VerificationStatus
import com.example.BE.domain.verification.facade.VerificationFacade
import com.example.BE.domain.verification.presentation.dto.response.VerificationResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.OffsetDateTime
import java.time.ZoneOffset
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Service
class VerificationService(
    private val spotFacade: SpotFacade,
    private val verificationFacade: VerificationFacade,
    private val s3Service: S3Service,
) {
    @Transactional
    fun verifyVisit(
        spotId: Long,
        image: MultipartFile,
        latitude: Double,
        longitude: Double,
    ): VerificationResultResponse {
        validateCoordinate(latitude = latitude, longitude = longitude)

        val spot = spotFacade.getSpot(spotId)
        val distanceMeters = calculateDistanceMeters(
            startLatitude = latitude,
            startLongitude = longitude,
            endLatitude = spot.latitude,
            endLongitude = spot.longitude,
        )

        if (distanceMeters > VERIFICATION_RADIUS_METERS) {
            val verifiedAt = OffsetDateTime.now(ZoneOffset.UTC).withNano(0)
            return VerificationResultResponse(
                verificationId = 0,
                spotId = spotId,
                spotName = spot.name,
                contentTitle = spot.content.title,
                sceneImageUrl = spot.imageUrl,
                verificationImageUrl = null,
                status = VerificationStatus.FAIL,
                verifiedAt = verifiedAt.toString(),
            )
        }

        val imageUrl = s3Service.uploadVerificationImage(image)
        val status = VerificationStatus.SUCCESS
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
            sceneImageUrl = spot.imageUrl,
            verificationImageUrl = imageUrl,
            status = status,
            verifiedAt = verifiedAt.toString(),
        )
    }

    private fun validateCoordinate(
        latitude: Double,
        longitude: Double,
    ) {
        val validLatitude = latitude.isFinite() && latitude in -90.0..90.0
        val validLongitude = longitude.isFinite() && longitude in -180.0..180.0

        if (!validLatitude || !validLongitude) {
            throw SdsrException(ErrorCode.INVALID_REQUEST)
        }
    }

    private fun calculateDistanceMeters(
        startLatitude: Double,
        startLongitude: Double,
        endLatitude: Double,
        endLongitude: Double,
    ): Double {
        val startLatRadians = Math.toRadians(startLatitude)
        val endLatRadians = Math.toRadians(endLatitude)
        val latitudeDiff = Math.toRadians(endLatitude - startLatitude)
        val longitudeDiff = Math.toRadians(endLongitude - startLongitude)

        val haversine = sin(latitudeDiff / 2).pow(2) +
            cos(startLatRadians) * cos(endLatRadians) * sin(longitudeDiff / 2).pow(2)
        val angularDistance = 2 * atan2(sqrt(haversine), sqrt(1 - haversine))

        return EARTH_RADIUS_METERS * angularDistance
    }

    companion object {
        private const val EARTH_RADIUS_METERS = 6_371_000.0
        private const val VERIFICATION_RADIUS_METERS = 200.0
    }
}

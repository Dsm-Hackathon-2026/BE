package com.example.BE.domain.verification.facade

import com.example.BE.domain.spot.domain.Spot
import com.example.BE.domain.verification.domain.Verification
import com.example.BE.domain.verification.domain.VerificationStatus
import com.example.BE.domain.verification.domain.repository.VerificationRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class VerificationFacade(
    private val verificationRepository: VerificationRepository,
) {
    fun saveVerification(
        spot: Spot,
        imageUrl: String,
        status: VerificationStatus,
        verifiedAt: LocalDateTime,
    ): Verification =
        verificationRepository.save(
            Verification(
                spot = spot,
                imageUrl = imageUrl,
                status = status,
                verifiedAt = verifiedAt,
            ),
        )
}

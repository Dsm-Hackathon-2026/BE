package com.example.BE.domain.verification.domain.repository

import com.example.BE.domain.verification.domain.Verification
import com.example.BE.domain.verification.domain.VerificationStatus
import org.springframework.data.jpa.repository.JpaRepository

interface VerificationRepository : JpaRepository<Verification, Long> {
    fun findFirstBySpot_IdAndStatusOrderByVerifiedAtDesc(
        spotId: Long,
        status: VerificationStatus,
    ): Verification?
}

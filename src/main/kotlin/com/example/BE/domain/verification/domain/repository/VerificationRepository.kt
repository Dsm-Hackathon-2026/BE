package com.example.BE.domain.verification.domain.repository

import com.example.BE.domain.verification.domain.Verification
import org.springframework.data.jpa.repository.JpaRepository

interface VerificationRepository : JpaRepository<Verification, Long> {
    fun findAllBySpot_Id(spotId: Long): List<Verification>

    fun countBySpot_Id(spotId: Long): Long
}

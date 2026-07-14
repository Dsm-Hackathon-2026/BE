package com.example.BE.domain.verification.domain

import com.example.BE.domain.spot.domain.Spot
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_verification")
class Verification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    var spot: Spot,

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    var imageUrl: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: VerificationStatus,

    @Column(name = "verified_at", nullable = false)
    var verifiedAt: LocalDateTime,
)

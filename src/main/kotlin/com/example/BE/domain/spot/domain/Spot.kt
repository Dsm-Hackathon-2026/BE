package com.example.BE.domain.spot.domain

import com.example.BE.domain.content.domain.Content
import com.example.BE.domain.verification.domain.Verification
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_spot")
class Spot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    var content: Content,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "latitude", nullable = false)
    var latitude: Double,

    @Column(name = "longitude", nullable = false)
    var longitude: Double,

    @Column(name = "kakao_place_id", nullable = false)
    var kakaoPlaceId: String,

    @Column(name = "address", nullable = false)
    var address: String = "",

    @Column(name = "image_url", nullable = false)
    var imageUrl: String,

    @Column(name = "scene_description", nullable = false)
    var sceneDescription: String,
) {
    @OneToMany(mappedBy = "spot")
    var verifications: MutableList<Verification> = mutableListOf()
        protected set
}

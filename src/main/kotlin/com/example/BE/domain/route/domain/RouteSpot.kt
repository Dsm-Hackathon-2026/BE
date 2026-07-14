package com.example.BE.domain.route.domain

import com.example.BE.domain.spot.domain.Spot
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_route_spots")
class RouteSpot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_spot_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    var spot: Spot,

    @Column(name = "selected_order", nullable = false)
    var selectedOrder: Int,
)

package com.example.BE.domain.route.domain.repository

import com.example.BE.domain.route.domain.RouteSpot
import org.springframework.data.jpa.repository.JpaRepository

interface RouteSpotRepository : JpaRepository<RouteSpot, Long> {
    fun existsBySpot_Id(spotId: Long): Boolean

    fun findBySpot_Id(spotId: Long): RouteSpot?

    fun findAllByOrderBySelectedOrderAsc(): List<RouteSpot>
}

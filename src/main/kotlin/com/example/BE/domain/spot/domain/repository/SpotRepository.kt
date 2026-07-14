package com.example.BE.domain.spot.domain.repository

import com.example.BE.domain.spot.domain.Spot
import org.springframework.data.jpa.repository.JpaRepository

interface SpotRepository : JpaRepository<Spot, Long> {
    fun findAllByContent_Id(contentId: Long): List<Spot>

    fun findByIdAndContent_Id(spotId: Long, contentId: Long): Spot?

    fun countByContent_Id(contentId: Long): Int
}

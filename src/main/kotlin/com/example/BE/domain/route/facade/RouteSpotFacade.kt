package com.example.BE.domain.route.facade

import com.example.BE.domain.route.domain.RouteSpot
import com.example.BE.domain.route.domain.repository.RouteSpotRepository
import com.example.BE.domain.spot.domain.Spot
import org.springframework.stereotype.Component

@Component
class RouteSpotFacade(
    private val routeSpotRepository: RouteSpotRepository,
) {
    fun existsRouteSpot(spotId: Long): Boolean = routeSpotRepository.existsBySpot_Id(spotId)

    fun findRouteSpot(spotId: Long): RouteSpot? = routeSpotRepository.findBySpot_Id(spotId)

    fun findAllRouteSpots(): List<RouteSpot> = routeSpotRepository.findAllByOrderBySelectedOrderAsc()

    fun saveRouteSpot(spot: Spot, selectedOrder: Int): RouteSpot =
        routeSpotRepository.save(RouteSpot(spot = spot, selectedOrder = selectedOrder))

    fun deleteRouteSpot(routeSpot: RouteSpot) { routeSpotRepository.delete(routeSpot) }

    fun deleteAllRouteSpots() { routeSpotRepository.deleteAll() }

    fun save(routeSpot: RouteSpot): RouteSpot = routeSpotRepository.save(routeSpot)
}

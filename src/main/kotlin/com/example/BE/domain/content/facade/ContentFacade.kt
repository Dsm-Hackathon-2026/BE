package com.example.BE.domain.content.facade

import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import com.example.BE.domain.content.domain.Content
import com.example.BE.domain.content.domain.ContentType
import com.example.BE.domain.content.domain.repository.ContentRepository
import com.example.BE.domain.spot.domain.repository.SpotRepository
import org.springframework.stereotype.Component

@Component
class ContentFacade(
    private val contentRepository: ContentRepository,
    private val spotRepository: SpotRepository,
) {
    fun findPopularContents(contentType: ContentType, limit: Int): List<Content> =
        contentRepository.findAllByContentTypeOrderByViewCountDesc(contentType).take(limit)

    fun findRecommendedContents(contentType: ContentType, limit: Int): List<Content> =
        contentRepository.findAllByContentType(contentType).take(limit)

    fun findMostVisitedContents(contentType: ContentType, limit: Int): List<Content> =
        contentRepository.findAllByContentType(contentType)
            .sortedByDescending { content -> countSpots(content.id ?: 0) }
            .take(limit)

    fun getContent(contentId: Long): Content =
        contentRepository.findById(contentId).orElse(null) ?: throw SdsrException(ErrorCode.CONTENT_NOT_FOUND)

    fun searchContents(keyword: String): List<Content> =
        contentRepository.findAllByTitleContainingIgnoreCase(keyword)

    fun countSpots(contentId: Long): Int =
        spotRepository.countByContent_Id(contentId)
}

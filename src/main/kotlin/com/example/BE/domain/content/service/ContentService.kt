package com.example.BE.domain.content.service

import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import com.example.BE.domain.content.domain.Content
import com.example.BE.domain.content.domain.ContentType
import com.example.BE.domain.content.facade.ContentFacade
import com.example.BE.domain.content.presentation.dto.response.ContentCardResponse
import com.example.BE.domain.content.presentation.dto.response.ContentSummaryResponse
import com.example.BE.domain.content.presentation.dto.response.PagedContentResponse
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class ContentService(
    private val contentFacade: ContentFacade,
) {
    fun popularContents(contentType: String, limit: Int): List<ContentSummaryResponse> =
        contentFacade.findPopularContents(contentType.toContentType(), limit)
            .map { it.toSummary() }

    fun recommendedContents(contentType: String, limit: Int): List<ContentCardResponse> =
        contentFacade.findRecommendedContents(contentType.toContentType(), limit)
            .map { it.toCard() }

    fun mostVisitedContents(contentType: String, limit: Int): List<ContentCardResponse> =
        contentFacade.findMostVisitedContents(contentType.toContentType(), limit)
            .map { it.toCard() }

    fun contentDetail(contentId: Long): ContentCardResponse =
        contentFacade.getContent(contentId).toCard()

    fun searchContents(keyword: String, page: Int, size: Int): PagedContentResponse {
        val matched = contentFacade.searchContents(keyword)
            .map { it.toCard() }
        val start = page * size

        return PagedContentResponse(
            content = matched.drop(start).take(size),
            page = page,
            size = size,
            totalElements = matched.size,
            last = start + size >= matched.size,
        )
    }

    private fun Content.toSummary(): ContentSummaryResponse =
        ContentSummaryResponse(
            contentId = id ?: 0,
            title = title,
            contentType = contentType.name,
            thumbnailUrl = thumbnailUrl,
            spotCount = contentFacade.countSpots(id ?: 0),
            viewCount = viewCount,
        )

    private fun Content.toCard(): ContentCardResponse =
        ContentCardResponse(
            contentId = id ?: 0,
            title = title,
            contentType = contentType.name,
            thumbnailUrl = thumbnailUrl,
            spotCount = contentFacade.countSpots(id ?: 0),
        )

    private fun String.toContentType(): ContentType =
        runCatching { ContentType.valueOf(uppercase(Locale.US)) }
            .getOrElse { throw SdsrException(ErrorCode.INVALID_CONTENT_TYPE) }
}

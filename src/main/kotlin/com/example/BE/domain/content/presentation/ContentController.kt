package com.example.BE.domain.content.presentation

import com.example.BE.domain.content.service.ContentService
import com.example.BE.domain.content.presentation.dto.response.ContentSliceResponse
import com.example.BE.domain.content.presentation.dto.response.ContentDetailResponse
import com.example.BE.domain.content.presentation.dto.response.ContentSummaryResponse
import com.example.BE.domain.content.presentation.dto.response.PagedContentResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contents")
@Tag(name = "contents", description = "콘텐츠 API")
class ContentController(
    private val contentService: ContentService,
) {
    @GetMapping("/{contentType}/popular")
    @Operation(
        summary = "인기 콘텐츠 조회",
        description = "콘텐츠 유형별 인기 콘텐츠를 조회합니다. 예: DRAMA, MOVIE, ANIMATION",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "인기 콘텐츠 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getPopularContents(
        @PathVariable contentType: String,
        @RequestParam(defaultValue = "5") limit: Int,
    ): List<ContentSummaryResponse> {
        return contentService.popularContents(contentType, limit)
    }

    @GetMapping("/{contentType}/recommended")
    @Operation(
        summary = "추천 콘텐츠 조회",
        description = "콘텐츠 유형별 추천 콘텐츠 목록을 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "추천 콘텐츠 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getRecommendedContents(
        @PathVariable contentType: String,
        @RequestParam(defaultValue = "5") limit: Int,
    ): ContentSliceResponse {
        val contents = contentService.recommendedContents(contentType, limit)

        return ContentSliceResponse(contents, limit, contents.size, true)
    }

    @GetMapping("/{contentType}/most-visited")
    @Operation(
        summary = "최다 방문지 콘텐츠 조회",
        description = "촬영지 방문 수가 많은 콘텐츠 목록을 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "최다 방문지 콘텐츠 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getMostVisitedContents(
        @PathVariable contentType: String,
        @RequestParam(defaultValue = "5") limit: Int,
    ): ContentSliceResponse {
        val contents = contentService.mostVisitedContents(contentType, limit)

        return ContentSliceResponse(contents, limit, contents.size, true)
    }

    @GetMapping("/{contentId}")
    @Operation(
        summary = "콘텐츠 상세 조회",
        description = "콘텐츠 ID로 콘텐츠 상세 정보를 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "콘텐츠 상세 조회 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun getContentDetail(@PathVariable contentId: Long): ContentDetailResponse {
        return contentService.contentDetail(contentId)
    }

    @GetMapping("/search")
    @Operation(
        summary = "콘텐츠 검색",
        description = "키워드로 콘텐츠를 검색하고 페이지네이션 결과를 조회합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "콘텐츠 검색 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun searchContents(
        @RequestParam keyword: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): PagedContentResponse {
        return contentService.searchContents(keyword, page, size)
    }
}

package com.example.BE.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {
    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "콘텐츠를 찾을 수 없습니다."),
    INVALID_CONTENT_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 콘텐츠 유형입니다."),
    SPOT_NOT_FOUND(HttpStatus.NOT_FOUND, "촬영지를 찾을 수 없습니다."),
    EMPTY_IMAGE(HttpStatus.BAD_REQUEST, "인증 사진이 비어 있습니다."),
    S3_BUCKET_NOT_CONFIGURED(HttpStatus.INTERNAL_SERVER_ERROR, "S3 버킷 설정이 필요합니다."),
    AI_SERVER_UNAVAILABLE(HttpStatus.BAD_GATEWAY, "AI 추천 서버와 통신할 수 없습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
}

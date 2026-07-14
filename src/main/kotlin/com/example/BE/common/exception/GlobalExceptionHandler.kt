package com.example.BE.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(SdsrException::class)
    fun handleSdsrException(exception: SdsrException): ResponseEntity<ErrorResponse> {
        val errorCode = exception.errorCode

        return ResponseEntity
            .status(errorCode.status)
            .body(
                ErrorResponse(
                    status = errorCode.status.value(),
                    message = errorCode.message,
                ),
            )
    }

    @ExceptionHandler(
        MissingServletRequestParameterException::class,
        MultipartException::class,
        IllegalArgumentException::class,
    )
    fun handleBadRequest(exception: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = exception.message ?: ErrorCode.INVALID_REQUEST.message,
                ),
            )

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxUploadSizeExceeded(): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = "업로드 가능한 파일 크기를 초과했습니다.",
                ),
            )

    @ExceptionHandler(Exception::class)
    fun handleException(): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    status = ErrorCode.INTERNAL_SERVER_ERROR.status.value(),
                    message = ErrorCode.INTERNAL_SERVER_ERROR.message,
                ),
            )
}

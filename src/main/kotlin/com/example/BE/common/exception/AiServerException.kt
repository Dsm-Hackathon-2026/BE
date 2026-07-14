package com.example.BE.common.exception

import org.springframework.http.HttpStatusCode

class AiServerException(
    val statusCode: HttpStatusCode,
    val responseBody: String,
) : RuntimeException(responseBody)

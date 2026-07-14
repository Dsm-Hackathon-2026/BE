package com.example.BE.common.exception

class SdsrException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message)

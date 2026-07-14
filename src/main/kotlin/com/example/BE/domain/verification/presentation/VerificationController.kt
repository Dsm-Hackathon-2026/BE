package com.example.BE.domain.verification.presentation

import com.example.BE.domain.verification.presentation.dto.response.VerificationResultResponse
import com.example.BE.domain.verification.service.VerificationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/verifications")
@Tag(name = "verifications", description = "방문 인증 API")
class VerificationController(
    private val verificationService: VerificationService,
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(
        summary = "방문 인증",
        description = "인증 사진 파일을 제출합니다.",
    )
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "방문 인증 성공"),
    )
    @ResponseStatus(HttpStatus.OK)
    fun verifyVisit(
        @RequestParam spotId: Long,
        @RequestParam image: MultipartFile,
    ): VerificationResultResponse {
        return verificationService.verifyVisit(
            spotId = spotId,
            image = image,
        )
    }
}

package com.example.BE.common.s3

import com.example.BE.common.exception.ErrorCode
import com.example.BE.common.exception.SdsrException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.UUID

@Service
class S3Service(
    private val s3Client: S3Client,
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String,
    @Value("\${cloud.aws.region.static}")
    private val region: String,
) {
    fun uploadVerificationImage(file: MultipartFile): String {
        if (bucket.isBlank()) throw SdsrException(ErrorCode.S3_BUCKET_NOT_CONFIGURED)
        if (file.isEmpty) throw SdsrException(ErrorCode.EMPTY_IMAGE)

        val key = "verifications/${UUID.randomUUID()}${file.extension()}"
        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(file.contentType ?: "application/octet-stream")
            .contentLength(file.size)
            .build()

        file.inputStream.use { inputStream ->
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, file.size))
        }

        return objectUrl(key)
    }

    private fun objectUrl(key: String): String =
        "https://$bucket.s3.$region.amazonaws.com/$key"

    private fun MultipartFile.extension(): String {
        val filename = originalFilename ?: return ""
        val extension = filename.substringAfterLast('.', missingDelimiterValue = "")
        return if (extension.isBlank()) "" else ".$extension"
    }
}

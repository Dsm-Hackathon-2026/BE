package com.example.BE.common.config.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import io.swagger.v3.oas.models.tags.Tag
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("성덕순례 API")
                    .description("성덕순례 서비스 백엔드 API 문서")
                    .version("1.0.0"),
            )
            .servers(
                listOf(
                    Server()
                        .url("http://localhost:8080")
                        .description("local"),
                    Server()
                        .url("http://43.203.30.24:8080")
                        .description("ec2"),
                ),
            )

    @Bean
    fun sortTags(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi ->
            val tagDescriptions = mapOf(
                "contents" to "콘텐츠 API",
                "spots" to "촬영지 API",
                "routes" to "방문 코스 API",
                "verifications" to "방문 인증 API",
            )

            val sortedTags = tagOrder.map { name ->
                Tag().name(name).description(tagDescriptions[name] ?: "")
            }

            openApi.tags(sortedTags)
        }
    }

    private companion object {
        val tagOrder = listOf(
            "contents",
            "spots",
            "routes",
            "verifications",
        )
    }
}

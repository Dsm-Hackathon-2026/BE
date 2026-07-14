package com.example.BE.domain.content.domain.repository

import com.example.BE.domain.content.domain.Content
import com.example.BE.domain.content.domain.ContentType
import org.springframework.data.jpa.repository.JpaRepository

interface ContentRepository : JpaRepository<Content, Long> {
    fun findAllByContentTypeOrderByViewCountDesc(contentType: ContentType): List<Content>

    fun findAllByContentType(contentType: ContentType): List<Content>

    fun findAllByTitleContainingIgnoreCase(keyword: String): List<Content>
}

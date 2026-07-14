package com.example.BE.domain.content.domain

import com.example.BE.domain.spot.domain.Spot
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_content")
class Content(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    var id: Long? = null,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content_type", nullable = false)
    @Enumerated(EnumType.STRING)
    var contentType: ContentType,

    @Column(name = "thumbnail_url", nullable = false, columnDefinition = "TEXT")
    var thumbnailUrl: String,

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "release_year", nullable = false)
    var releaseYear: Int = 0,

    @Column(name = "country", nullable = false)
    var country: String = "",

    @Column(name = "view_count", nullable = false)
    var viewCount: Int = 0,
) {
    @OneToMany(mappedBy = "content")
    var spots: MutableList<Spot> = mutableListOf()
        protected set
}

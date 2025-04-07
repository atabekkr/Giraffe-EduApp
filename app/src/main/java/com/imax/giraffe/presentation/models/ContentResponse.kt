package com.imax.giraffe.presentation.models

data class ContentResponse(
    val content: List<ContentItem>
)

data class ContentItem(
    val text: String,
    val image: String? = null
)
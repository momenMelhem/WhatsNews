package com.example.whatsnews.domain.model

data class TechArticle(
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val techSource: TechSource,
    val title: String,
    val url: String
)
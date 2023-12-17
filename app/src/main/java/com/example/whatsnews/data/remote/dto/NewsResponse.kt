package com.example.whatsnews.data.remote.dto

import com.example.whatsnews.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
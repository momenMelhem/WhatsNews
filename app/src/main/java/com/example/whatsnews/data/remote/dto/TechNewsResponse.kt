package com.example.whatsnews.data.remote.dto

import com.example.whatsnews.domain.model.TechArticle

data class TechNewsResponse(
    val techArticles: List<TechArticle>,
    val totalArticles: Int
)
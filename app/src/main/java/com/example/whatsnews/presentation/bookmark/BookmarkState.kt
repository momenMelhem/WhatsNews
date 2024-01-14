package com.example.whatsnews.presentation.bookmark

import com.example.whatsnews.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)

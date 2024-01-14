package com.example.whatsnews.domain.usecases

import com.example.whatsnews.data.local.NewsDao
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {
     operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}
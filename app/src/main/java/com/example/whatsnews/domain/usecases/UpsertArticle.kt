package com.example.whatsnews.domain.usecases

import com.example.whatsnews.data.local.NewsDao
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article:Article){
        newsRepository.upsertArticle(article)
    }
}
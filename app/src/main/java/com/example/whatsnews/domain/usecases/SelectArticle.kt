package com.example.whatsnews.domain.usecases

import com.example.whatsnews.data.local.NewsDao
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String):Article?{
      return newsRepository.selectArticle(url)
    }
}
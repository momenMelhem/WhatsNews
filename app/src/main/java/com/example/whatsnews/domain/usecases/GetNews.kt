package com.example.whatsnews.domain.usecases

import androidx.paging.PagingData
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews (
    private val newsRepository : NewsRepository
){
    operator fun invoke(sources:List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources=sources)
    }

}
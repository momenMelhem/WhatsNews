package com.example.whatsnews.domain.usecases

import androidx.paging.PagingData
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository,

) {
    operator fun invoke(searchQuery:String,sources:List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery=searchQuery,sources=sources)
    }
}
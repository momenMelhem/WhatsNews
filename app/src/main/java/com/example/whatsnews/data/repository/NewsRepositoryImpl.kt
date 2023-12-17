package com.example.whatsnews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.whatsnews.data.remote.NewsPagingSource
import com.example.whatsnews.data.remote.SearchNewsPagingSource
import com.example.whatsnews.data.remote.dto.NewsApi
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi:NewsApi
) : NewsRepository{
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
      return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi=newsApi,
                    sources=sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery=searchQuery,
                    newsApi=newsApi,
                    sources=sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}
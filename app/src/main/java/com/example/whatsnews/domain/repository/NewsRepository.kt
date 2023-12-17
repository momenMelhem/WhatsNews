package com.example.whatsnews.domain.repository

import androidx.paging.PagingData
import com.example.whatsnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    //Paging Data is a wrapper class
    fun getNews(sources:List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery:String,sources:List<String>): Flow<PagingData<Article>>

}
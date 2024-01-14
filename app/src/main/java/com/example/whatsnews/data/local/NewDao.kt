package com.example.whatsnews.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.whatsnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Upsert
    suspend fun upsert(article: Article)
    @Delete
    suspend fun delete(article: Article)
    @Query("Select * From Article")
    fun getArticles(): Flow<List<Article>>

    @Query("Select * From Article where url =:url")
    suspend fun getArticle(url:String):Article?
}
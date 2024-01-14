package com.example.whatsnews.di

import android.app.Application
import androidx.room.Room
import com.example.whatsnews.data.local.NewsDao
import com.example.whatsnews.data.local.NewsDataBase
import com.example.whatsnews.data.local.NewsTypeConverter
import com.example.whatsnews.data.remote.dto.NewsApi
import com.example.whatsnews.data.repository.NewsRepositoryImpl
import com.example.whatsnews.domain.repository.NewsRepository
import com.example.whatsnews.domain.usecases.DeleteArticle
import com.example.whatsnews.domain.usecases.GetNews
import com.example.whatsnews.domain.usecases.NewsUseCases
import com.example.whatsnews.domain.usecases.SearchNews
import com.example.whatsnews.domain.usecases.SelectArticle
import com.example.whatsnews.domain.usecases.SelectArticles
import com.example.whatsnews.domain.usecases.UpsertArticle
import com.example.whatsnews.util.Constants.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository = NewsRepositoryImpl(newsApi,newsDao)
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,

    ): NewsDataBase {
       return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDataBase
    ): NewsDao = newsDatabase.newsDao
}

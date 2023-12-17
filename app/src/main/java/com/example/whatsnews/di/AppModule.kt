package com.example.whatsnews.di

import android.app.Application
import android.app.LocaleManager
import com.example.whatsnews.data.remote.dto.NewsApi
import com.example.whatsnews.data.repository.NewsRepositoryImpl
import com.example.whatsnews.domain.repository.NewsRepository
import com.example.whatsnews.domain.usecases.GetNews
import com.example.whatsnews.domain.usecases.NewsUseCases
import com.example.whatsnews.domain.usecases.SearchNews
import com.example.whatsnews.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
        newsApi: NewsApi
    ):NewsRepository= NewsRepositoryImpl(newsApi)
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}
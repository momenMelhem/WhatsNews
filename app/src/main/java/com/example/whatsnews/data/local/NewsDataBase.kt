package com.example.whatsnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.whatsnews.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters (NewsTypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract val newsDao : NewsDao
}
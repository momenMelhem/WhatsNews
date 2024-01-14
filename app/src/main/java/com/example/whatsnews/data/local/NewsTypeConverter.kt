package com.example.whatsnews.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.whatsnews.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source:Source) : String {
        return "${source.id},${source.name}"
    }
    @TypeConverter
    fun stringToSource(source:String): Source{
        return source.split(",").let {sourceList ->
            Source(sourceList[0],sourceList[1])

        }
    }
}
package com.example.whatsnews.data.remote.dto

import com.example.whatsnews.util.Constants
import com.example.whatsnews.util.Constants.TECH_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface TechNewsApi {
    @GET ("technology")
    suspend fun getTechNews(
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey:String = TECH_API_KEY
    ):TechNewsResponse
}
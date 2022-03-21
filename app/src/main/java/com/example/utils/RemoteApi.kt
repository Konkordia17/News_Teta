package com.example.utils

import com.example.data.model.ArticlesEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("/v2/top-headlines/")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): ArticlesEntity
}
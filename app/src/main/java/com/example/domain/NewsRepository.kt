package com.example.domain

import com.example.domain.model.News

interface NewsRepository {

    suspend fun getNews(): List<News>
}
package com.example.domain.usecase

import com.example.domain.NewsRepository
import com.example.domain.model.News
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun getNews(): List<News> {
        return repository.getNews()
    }
}
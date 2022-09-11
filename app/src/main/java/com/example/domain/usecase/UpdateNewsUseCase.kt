package com.example.domain.usecase

import com.example.domain.NewsRepository
import com.example.domain.model.News
import javax.inject.Inject

class UpdateNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend fun updateNews(): List<News> {
        return repository.updateNews()
    }
}
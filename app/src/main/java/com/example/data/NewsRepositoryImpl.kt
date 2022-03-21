package com.example.data

import com.example.data.mappers.NewsMapper
import com.example.domain.NewsRepository
import com.example.domain.model.News
import com.example.utils.RemoteApi
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: RemoteApi,
    private val newsMapper: NewsMapper
) : NewsRepository {

    override suspend fun getNews(): List<News> {
        return try {
            val news = api.getNews(COUNTRY, API_KEY)
            news.articles.map { newsMapper.newsEntityToDomain(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    companion object {
        const val COUNTRY = "ru"
        const val API_KEY = "56f431e6107c4fc785711d45f9647a8b"
    }
}
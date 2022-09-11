package com.example.data

import com.example.data.mappers.NewsMapper
import com.example.data.model.db.AppDatabase
import com.example.domain.NewsRepository
import com.example.domain.model.News
import com.example.utils.RemoteApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: RemoteApi,
    private val newsMapper: NewsMapper,
    private val db: AppDatabase
) : NewsRepository {

    override suspend fun getNews(): List<News> {
        delay(3000)
        return getNewsFromDb().ifEmpty {
            val news = getNewsFromApi()
            setNewsToDb(news)
            news
        }
    }

    private suspend fun getNewsFromDb(): List<News> {
        return db.newsDao().getAll().map { newsMapper.newsEntityToDomain(it) }
    }

    private suspend fun getNewsFromApi(): List<News> {
        return api.getNews(COUNTRY, API_KEY).articles.map { newsMapper.newsEntityToDomain(it) }
    }

    private suspend fun setNewsToDb(news: List<News>) {
        db.newsDao().insert(news = news.map { newsMapper.newsToNewsEntity(it) })
    }

    override suspend fun updateNews(): List<News> {
        val news = getNewsFromApi()
        db.newsDao().updateNews(news = news.map { newsMapper.newsToNewsEntity(it) })
        return news
    }

    companion object {
        const val COUNTRY = "ru"
        const val API_KEY = "56f431e6107c4fc785711d45f9647a8b"
    }
}
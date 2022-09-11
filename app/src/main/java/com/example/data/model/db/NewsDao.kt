package com.example.data.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    suspend fun getAll(): List<NewsEntity>

    @Insert
    suspend fun insert(news: List<NewsEntity>?)

    @Update
    suspend fun updateNews(news: List<NewsEntity>?)
}
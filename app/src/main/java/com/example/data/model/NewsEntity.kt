package com.example.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ArticlesEntity(
    val articles: List<NewsEntity>
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,
    @PrimaryKey @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "image")
    val urlToImage: String?
) : Parcelable


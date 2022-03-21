package com.example.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ArticlesEntity(
    val articles: List<NewsEntity>
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NewsEntity(
    val publishedAt: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?
) : Parcelable


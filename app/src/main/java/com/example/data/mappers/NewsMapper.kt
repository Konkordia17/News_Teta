package com.example.data.mappers

import com.example.data.model.NewsEntity
import com.example.domain.model.News
import javax.inject.Inject

class NewsMapper @Inject constructor() {
    fun newsEntityToDomain(entity: NewsEntity): News {
        return News(
            publishedAt = entity.publishedAt.orEmpty(),
            title = entity.title.orEmpty(),
            description = entity.description.orEmpty(),
            urlToImage = entity.urlToImage.orEmpty()
        )
    }
}
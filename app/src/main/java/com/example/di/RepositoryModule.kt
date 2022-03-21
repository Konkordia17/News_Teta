package com.example.di

import com.example.data.NewsRepositoryImpl
import com.example.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}
package com.example.core_data.di

import com.example.core_data.repo.MarvelRepository
import com.example.core_data.repo.MarvelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMarvelRepository(
        marvelRepositoryImpl: MarvelRepositoryImpl
    ): MarvelRepository

}
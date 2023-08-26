package com.example.app_xml.di

import com.example.app_xml.data.repo.MarvelRepository
import com.example.app_xml.data.repo.MarvelRepositoryImpl
import com.example.app_xml.data.source.MarvelRemoteDataSource
import com.example.app_xml.data.source.MarvelRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindMarvelRepository(marvelRepositoryImpl: MarvelRepositoryImpl) : MarvelRepository

    @Binds
    abstract fun bindBookRemoteDataSource(marvelRemoteDataSourceImpl: MarvelRemoteDataSourceImpl) : MarvelRemoteDataSource

}
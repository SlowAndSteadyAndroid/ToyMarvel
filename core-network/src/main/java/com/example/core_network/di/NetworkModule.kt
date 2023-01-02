package com.example.core_network.di

import com.example.core_network.api.MarvelApi
import com.example.core_network.retrofit.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsMarvelNetwork(
        marvelNetwork: RetrofitMarvelNetwork
    ): MarvelApi

    @Binds
    fun bindHttpClient(
        httpClientImpl: HttpClientImpl
    ): HttpClient

    @Binds
    fun bindSerializableConverterFactory(
        serializableConverterFactoryImpl: SerializableConverterFactoryImpl
    ): SerializableConverterFactory
}
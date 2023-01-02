package com.example.core_database.di

import android.content.Context
import androidx.room.Room
import com.example.core_database.room.dao.MarvelDao
import com.example.core_database.room.database.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase =
        Room.databaseBuilder(context, MarvelDatabase::class.java, "marvel_tabel").build()

    @Provides
    @Singleton
    fun provideMarvelDao(@ApplicationContext context: Context): MarvelDao =
        provideMarvelDatabase(context).marvelDao()

}
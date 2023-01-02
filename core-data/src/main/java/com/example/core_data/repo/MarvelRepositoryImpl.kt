package com.example.core_data.repo

import com.example.core_database.room.dao.MarvelDao
import com.example.core_database.room.entity.MarvelEntity
import com.example.core_model.marvel.response.CharacterResponse
import com.example.core_network.api.MarvelApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelApi: MarvelApi,private val marvelDao: MarvelDao) :
    MarvelRepository {

    override fun getAllCharacters(offset: Int): Flow<CharacterResponse> = flow {
        emit(marvelApi.getAllCharacters(offset))
    }

    override val bookmarkList: Flow<List<MarvelEntity>>
        get() = marvelDao.getAllBookmark()
}
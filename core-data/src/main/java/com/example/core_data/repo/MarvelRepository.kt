package com.example.core_data.repo

import com.example.core_database.room.entity.MarvelEntity
import com.example.core_model.marvel.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    val bookmarkList: Flow<List<MarvelEntity>>

    fun getAllCharacters(
        offset: Int
    ): Flow<CharacterResponse>

}
package com.example.core_network.api

import com.example.core_model.marvel.response.CharacterResponse
import com.example.core_network.constant.Constant
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApi {

    /**
     * @see CharacterResponse
     */
    @GET(Constant.URL_MARVEL_CHARACTERS)
    suspend fun getAllCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharacterResponse
}
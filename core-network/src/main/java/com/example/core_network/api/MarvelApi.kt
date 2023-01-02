package com.example.core_network.api

import com.example.core_network.constant.Constant
import com.example.core_network.constant.Constant.LIMIT_SIZE
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApi {

    @GET(Constant.URL_MARVEL_CHARACTERS)
    suspend fun getAllCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = LIMIT_SIZE
    ): CharacterResponse
}

data class CharacterResponse(val data: CharacterData)

data class CharacterData(val results: List<CharacterResult>)


data class CharacterResult(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var thumbnail: Thumbnail = Thumbnail("", ""),
    var comics: Comics = Comics(""),
    var series: Series = Series(""),
    var urls: List<Url> = ArrayList(),
) {

    data class Thumbnail(val path: String, val extension: String)


    data class Url(val type: String, val url: String)


    data class Comics(val available: String)

    data class Series(val available: String)

}
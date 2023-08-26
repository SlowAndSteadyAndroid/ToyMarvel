package com.example.app_xml.data.source

import com.example.app_xml.network.response.MarvelCharatersResponse
import retrofit2.Response

interface MarvelRemoteDataSource {
    suspend fun searchCharacters(
        start: Int, limit: Int
    ): Response<MarvelCharatersResponse>
}
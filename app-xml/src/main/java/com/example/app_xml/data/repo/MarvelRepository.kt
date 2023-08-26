package com.example.app_xml.data.repo

import com.example.app_xml.network.response.MarvelCharatersResponse
import retrofit2.Response

interface MarvelRepository {
    suspend fun searchCharacters(
        start: Int, limit: Int
    ) : Response<MarvelCharatersResponse>
}
package com.example.app_xml.data.source

import com.example.app_xml.network.MarvelApi
import com.example.app_xml.network.response.MarvelCharatersResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRemoteDataSourceImpl @Inject constructor(private val mavelApi: MarvelApi) :
        MarvelRemoteDataSource {

    override suspend fun searchCharacters(start: Int, limit: Int): Response<MarvelCharatersResponse> {
        return mavelApi.getCharacters(start = start, limit=limit)
    }
}
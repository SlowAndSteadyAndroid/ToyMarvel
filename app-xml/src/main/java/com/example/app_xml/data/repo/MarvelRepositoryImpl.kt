package com.example.app_xml.data.repo

import com.example.app_xml.data.repo.MarvelRepository
import com.example.app_xml.data.source.MarvelRemoteDataSource
import com.example.app_xml.network.response.MarvelCharatersResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelRemoteDataSource: MarvelRemoteDataSource) :
    MarvelRepository {

    override suspend fun searchCharacters(start: Int, limit: Int): Response<MarvelCharatersResponse> =
        marvelRemoteDataSource.searchCharacters(start = start, limit=limit)
}
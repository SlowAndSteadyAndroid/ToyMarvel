package com.example.core_network.retrofit

import com.example.core_network.api.CharacterResponse
import com.example.core_network.api.MarvelApi
import com.example.core_network.constant.Constant
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


private interface RetrofitMarvelApi {

}


@Singleton
class RetrofitMarvelNetwork @Inject constructor(
    private val httpClient: HttpClient,
    private val converterFactory: SerializableConverterFactory
) :
    MarvelApi {

    private val networkApi =
        Retrofit.Builder().baseUrl(Constant.URL_BASE_MARVEL)
            .addConverterFactory(converterFactory.provideSerializableConverterFactory())
            .client(httpClient.provideHttpClient()).build()
            .create(RetrofitMarvelApi::class.java)

    override suspend fun getAllCharacters(offset: Int, limit: Int): CharacterResponse {
        TODO("Not yet implemented")
    }
}

@Singleton
class HttpClientImpl : HttpClient {

    override fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", Constant.API_KEY)
                .addQueryParameter("ts", "1")
                .addQueryParameter("hash", Constant.HASH)
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }
        return httpClient.build()
    }
}

interface HttpClient {
    fun provideHttpClient(): OkHttpClient
}


@Singleton
class SerializableConverterFactoryImpl : SerializableConverterFactory {

    override fun provideSerializableConverterFactory(): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
        return json.asConverterFactory("application/json".toMediaTypeOrNull()!!)
    }
}

interface SerializableConverterFactory {
    fun provideSerializableConverterFactory(): Converter.Factory
}
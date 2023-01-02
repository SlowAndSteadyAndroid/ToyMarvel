package com.example.core_model.marvel.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CharacterResponse(val data: CharacterData)

@kotlinx.serialization.Serializable
data class CharacterData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Results<Roles, Urls>>
)

@kotlinx.serialization.Serializable
@SerialName("results")
data class Results<T, S>(
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
    val comics: T,
    val series: T,
    val stories: T,
    val events: T,
    val urls: List<S>
)

@kotlinx.serialization.Serializable
data class Roles(
    val available: Int
)

@kotlinx.serialization.Serializable
data class Urls(
    val type: String,
    val url: String
)

@kotlinx.serialization.Serializable
data class Thumbnail(
    val path: String,
    val extension: String
)

fun Thumbnail.asImageUrl(): String = "$path.$extension"
package com.example.core_model.marvel.model

import com.example.core_model.marvel.response.*

data class CharacterItem(
    val id: Int,
    val name: String,
    val image: String,
    val comics: Int,
    val series: Int,
    val stories: Int,
    val events: Int,
    val urls: Int
)


fun Results<Roles, Urls>.CharacterItem(): CharacterItem =
    CharacterItem(
        id = id,
        name = name,
        image = thumbnail.asImageUrl(),
        comics.available,
        series.available,
        stories.available,
        events.available,
        if (urls.isNotEmpty()) urls.size else 0
    )




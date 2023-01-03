package com.example.core_model.marvel.ext

import com.example.core_model.marvel.entity.MarvelEntity
import com.example.core_model.marvel.model.CharacterItem

fun CharacterItem.asCharacterEntity(): MarvelEntity =
    MarvelEntity(id, name, image, comics, series, stories, events, urls)
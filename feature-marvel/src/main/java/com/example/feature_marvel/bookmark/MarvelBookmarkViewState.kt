package com.example.feature_marvel.bookmark

import com.example.core_model.marvel.model.CharacterItem


sealed interface MarvelBookmarkViewState {
    object Loading : MarvelBookmarkViewState
    data class Success(val items: List<CharacterItem>) : MarvelBookmarkViewState
    object Error : MarvelBookmarkViewState
}
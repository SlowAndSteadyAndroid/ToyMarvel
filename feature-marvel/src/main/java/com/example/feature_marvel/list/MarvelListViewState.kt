package com.example.feature_marvel.list

import com.example.core_model.marvel.model.CharacterItem



data class MarvelListUiViewState(
    val isLoading: Boolean = false,
    val items: List<CharacterItem> = emptyList(),
    val message: String = "",
    val swipeRefresh: Boolean = false
)
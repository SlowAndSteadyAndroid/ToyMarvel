package com.example.feature_marvel.list

import com.example.core_model.marvel.model.CharacterItem



sealed interface MarvelListUiViewState {
    object Loading : MarvelListUiViewState
    data class Success(val items: List<CharacterItem>) : MarvelListUiViewState
    object Error : MarvelListUiViewState
}
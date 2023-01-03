package com.example.feature_marvel.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_common.Result
import com.example.core_common.asResult
import com.example.core_data.repo.MarvelRepository
import com.example.core_model.marvel.entity.MarvelEntity
import com.example.core_model.marvel.model.CharacterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelBookmarkViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    val getBookmarkItemStream: Flow<Result<List<MarvelEntity>>> =
        marvelRepository.bookmarkList.asResult()

    fun deleteBookmark(item: CharacterItem) {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.deleteCharacterItem(item)
        }
    }
}
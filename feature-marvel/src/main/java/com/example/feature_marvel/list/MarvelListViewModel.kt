package com.example.feature_marvel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_common.Result
import com.example.core_common.asResult
import com.example.core_data.repo.MarvelRepository
import com.example.core_database.room.entity.MarvelEntity
import com.example.core_model.marvel.model.CharacterItem
import com.example.core_model.marvel.response.CharacterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MarvelListViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    private val getAllCharactersStream: Flow<Result<CharacterResponse>> =
        marvelRepository.getAllCharacters(0).asResult()


    private val getBookmarkItemStream: Flow<Result<List<MarvelEntity>>> =
        marvelRepository.bookmarkList.asResult()

    val uiState: StateFlow<MarvelListUiViewState> =
        combine(
            getAllCharactersStream,
            getBookmarkItemStream
        ) { getAllCharactersResult, getBookmarkItemResult ->

            if (getAllCharactersResult is Result.Success &&
                getBookmarkItemResult is Result.Success
            ) {

                val getCharacterItem =
                    getAllCharactersResult.data.data.results.map { it.CharacterItem(false) }

                getCharacterItem.forEach { item ->
                    item.isBookmark = getBookmarkItemResult.data.map { it.id }.contains(item.id)
                }

                MarvelListUiViewState.Success(getCharacterItem)
            } else if (getAllCharactersResult is Result.Loading ||
                getBookmarkItemResult is Result.Loading
            ) {
                MarvelListUiViewState.Loading
            } else {
                MarvelListUiViewState.Error
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MarvelListUiViewState.Loading
        )


    fun addBookmark(item: CharacterItem) {
        viewModelScope.launch(IO) {
            marvelRepository.insertCharacterItem(item)
        }
    }

    fun deleteBookmark(item: CharacterItem) {
        viewModelScope.launch(IO) {
            marvelRepository.deleteCharacterItem(item)
        }
    }

}
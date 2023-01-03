package com.example.feature_marvel.list

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_common.Result
import com.example.core_common.asResult
import com.example.core_data.repo.MarvelRepository
import com.example.core_model.marvel.entity.MarvelEntity
import com.example.core_model.marvel.model.CharacterItem
import com.example.feature_marvel.util.MediaUtil.Companion.getBitmapFromUrl
import com.example.feature_marvel.util.MediaUtil.Companion.saveToGallery
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


@HiltViewModel
class MarvelListViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val marvelRepository: MarvelRepository
) :
    ViewModel() {

    private val _state = mutableStateOf(MarvelListUiViewState())
    val state: State<MarvelListUiViewState> get() = _state

    private val characterLinkedHashMap = LinkedHashMap<Int, CharacterItem>()
    private var offset = INIT_OFFSET
    private var isEndPosition = AtomicBoolean(false)


    private val getAllCharactersStream = { offset: Int ->
        marvelRepository.getAllCharacters(offset, LIMIT).asResult()
    }

    private val getBookmarkItemStream: Flow<Result<List<MarvelEntity>>> =
        marvelRepository.bookmarkList.asResult()

    init {
        getCharacters(offset)
    }

    private fun getCharacters(offset: Int) {
        viewModelScope.launch(IO) {
            combine(
                getAllCharactersStream(offset),
                getBookmarkItemStream
            ) { getAllCharactersResult, getBookmarkItemResult ->

                if (getAllCharactersResult is Result.Success &&
                    getBookmarkItemResult is Result.Success
                ) {
                    //Get Items
                    val getCharacterItem =
                        getAllCharactersResult.data.data.results.map { it.CharacterItem(false) }

                    //Convert Bookmark and Cache
                    getCharacterItem.forEach { item ->
                        item.isBookmark =
                            getBookmarkItemResult.data.map { it.id }.contains(item.id)
                        characterLinkedHashMap[item.id] = item
                    }

                    //check end
                    val message =
                        if (getAllCharactersResult.data.data.total <= this@MarvelListViewModel.offset) "마지막 페이지 입니다."
                        else EMPTY_MESSAGE

                    onChangeViewState(MarvelListUiViewState(
                        message = message,
                        items = characterLinkedHashMap.map { it.value }
                    ))
                } else if (getAllCharactersResult is Result.Loading ||
                    getBookmarkItemResult is Result.Loading
                ) {
                    onChangeViewState(
                        MarvelListUiViewState(
                            items = characterLinkedHashMap.map { it.value },
                            isLoading = true
                        )
                    )
                } else {
                    onChangeViewState(MarvelListUiViewState(message = "예기치 못한 에러가 발생하였습니다."))
                }
            }.launchIn(this)
        }
    }

    fun isScrolledToTheEnd(isEnd: Boolean) {
        if (isEnd) {
            nextPage()
        } else {
            setIsEndPosition(false)
        }
    }

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

    fun swipeRefresh() {
        viewModelScope.launch {
            onChangeViewState(MarvelListUiViewState(swipeRefresh = true))
            delay(REFRESH_DELAY_TIME)
            offset = INIT_OFFSET
            characterLinkedHashMap.clear()
            getCharacters(offset)
        }
    }

    fun saveDialogProcess(item: CharacterItem) {
        onChangeViewState(
            MarvelListUiViewState(
                items = characterLinkedHashMap.map { it.value },
                isLoading = true
            )
        )
        with(context) {
            getBitmapFromUrl(item.image) { bitmap ->
                bitmap?.saveToGallery(this)
            }
        }

        onChangeViewState(
            MarvelListUiViewState(
                items = characterLinkedHashMap.map { it.value },
                isLoading = false
            )
        )

    }


    private fun nextPage() {
        if (!isEndPosition.get()) {
            setIsEndPosition(true)
            offset += LIMIT
            getCharacters(offset = offset)
        }
    }

    private fun setIsEndPosition(isEnd: Boolean) {
        isEndPosition.set(isEnd)
    }

    private fun onChangeViewState(viewState: MarvelListUiViewState) {
        viewModelScope.launch {
            _state.value = viewState
        }
    }


    companion object {
        private const val INIT_OFFSET = 0
        private const val LIMIT = 20
        private const val EMPTY_MESSAGE = ""
        private const val REFRESH_DELAY_TIME = 1000L
    }

}
package com.example.app_xml

import android.view.View
import androidx.lifecycle.viewModelScope

import com.example.app_xml.base.BaseViewModel
import com.example.app_xml.data.repo.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository
) : BaseViewModel() {

    private var isStartSearch = AtomicBoolean(false)

    private var offsetCount = DEFAULT_START_COUNT

    val isScrollBottomPosition: Function1<Boolean, Unit> = ::test

    init {
        search(DEFAULT_START_COUNT, DEFAULT_LIMIT)
    }

    private fun test(isBottom: Boolean) {
        if (isBottom && !isStartSearch.get()) {
            search(offsetCount)
        }
    }

    private fun search(start: Int, limit: Int = DEFAULT_LIMIT) {
        viewModelScope.launch(Dispatchers.IO) {
            isStartSearch.set(true)
            onChangedViewState(MainViewState.ShowLoading(View.VISIBLE))
            val response = marvelRepository.searchCharacters(start, limit)
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.data.isLast()) {
                        onChangedViewState(MainViewState.ShowToast("마지막 캐릭터 정보입니다."))
                    } else {
                        offsetCount += it.data.count
                        onChangedViewState(MainViewState.GetData(it.data.results))
                    }
                } ?: kotlin.run {
                    onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
                }
            } else {
                onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
            }

            onChangedViewState(MainViewState.ShowLoading(View.INVISIBLE))
            isStartSearch.set(false)
        }
    }


    fun refresh() {
        offsetCount = DEFAULT_START_COUNT
        viewModelScope.launch(Dispatchers.IO) {
            search(DEFAULT_START_COUNT)
            val response = marvelRepository.searchCharacters(DEFAULT_START_COUNT, DEFAULT_LIMIT)
            if (response.isSuccessful) {
                response.body()?.let {
                    onChangedViewState(MainViewState.Refresh(it.data.results))
                } ?: kotlin.run {
                    onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
                }
            } else {
                onChangedViewState(MainViewState.ShowToast("검색을 실패하였습니다."))
            }
        }
    }


    companion object {
        private const val DEFAULT_START_COUNT = 0
        private const val DEFAULT_LIMIT = 10
    }

}

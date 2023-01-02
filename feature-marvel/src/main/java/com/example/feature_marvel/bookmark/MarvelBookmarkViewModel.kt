package com.example.feature_marvel.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarvelBookmarkViewModel @Inject constructor() : ViewModel() {


    private val _inputState = mutableStateOf("안녕2")
    val inputState: State<String> = _inputState

    fun clickButton(){
        _inputState.value = "버튼클릭2"
    }
}
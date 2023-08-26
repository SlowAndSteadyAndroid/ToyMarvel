package com.example.app_xml

import com.example.app_xml.base.ViewState
import com.example.app_xml.network.response.Result

sealed class MainViewState : ViewState {
    data class ShowToast(val message: String) : MainViewState()
    data class GetData(val list: List<Result>) : MainViewState()
    data class Refresh(val list: List<Result>) : MainViewState()
    data class ShowLoading(val flag: Int) : MainViewState()

}
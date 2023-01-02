package com.example.feature_marvel.list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_marvel.component.MarvelScreen

@Composable
fun MarvelListScreen(viewModel: MarvelListViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val result = state.value) {
            MarvelListUiViewState.Error -> {

            }

            MarvelListUiViewState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .semantics { testTag = "progress" })
            }
            is MarvelListUiViewState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.items) { item ->
                        MarvelScreen(
                            item = item,
                            onItemClick = if (item.isBookmark) viewModel::deleteBookmark else viewModel::addBookmark
                        )
                    }
                }
            }
        }
    }
}
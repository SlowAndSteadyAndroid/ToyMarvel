package com.example.feature_marvel.bookmark

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
import com.example.core_common.Result
import com.example.core_database.room.entity.MarvelEntity
import com.example.core_model.marvel.model.CharacterItem
import com.example.feature_marvel.component.MarvelScreen

@Composable
fun MarvelBookmarkScreen(viewModel: MarvelBookmarkViewModel = hiltViewModel()) {

    val state = viewModel.getBookmarkItemStream.collectAsState(initial = Result.Loading)


    Box(modifier = Modifier.fillMaxSize()) {
        when (val result = state.value) {
            is Result.Error -> {

            }

            is Result.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .semantics { testTag = "progress" })
            }
            is Result.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.data) { item ->
                        MarvelScreen(
                            item = item.asCharacterItem(isBookmark = true),
                            onItemClick = viewModel::deleteBookmark
                        )
                    }
                }
            }
        }
    }
}

fun MarvelEntity.asCharacterItem(isBookmark: Boolean): CharacterItem =
    CharacterItem(id, name, image, comics, series, stories, events, urls, isBookmark)
package com.example.feature_marvel.list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.ext.isScrolledToTheEnd
import com.example.feature_marvel.component.MarvelScreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MarvelListScreen(viewModel: MarvelListViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    val listState = rememberLazyListState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.swipeRefresh)

    SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::swipeRefresh) {

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                items(state.items) { item ->
                    MarvelScreen(
                        item = item,
                        onItemClick = if (item.isBookmark) viewModel::deleteBookmark else viewModel::addBookmark
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            if (state.message.isNotEmpty()) {
                Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    viewModel.isScrolledToTheEnd(listState.isScrolledToTheEnd())
}
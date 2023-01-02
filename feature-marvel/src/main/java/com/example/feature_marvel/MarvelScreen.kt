package com.example.feature_marvel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.feature_marvel.bookmark.MarvelBookmarkScreen
import com.example.feature_marvel.list.MarvelListScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MarvelScreen() {

    val pagerState = rememberPagerState(pageCount = 2)

    val tabs = listOf("List" , "Bookmark" )

    Column(modifier = Modifier.fillMaxSize()) {
        TabToContent(pagerState = pagerState, tabs = tabs)
    }
}


@ExperimentalPagerApi
@Composable
fun TabToContent(pagerState: PagerState, tabs: List<String>) {

    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        text = title,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                }, selected = pagerState.currentPage == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                })
        }
    }

    HorizontalPager(state = pagerState) { page ->
        Column(modifier = Modifier.fillMaxSize()) {
            when(page){
                0-> MarvelListScreen()
                1-> MarvelBookmarkScreen()
            }
        }
    }
}
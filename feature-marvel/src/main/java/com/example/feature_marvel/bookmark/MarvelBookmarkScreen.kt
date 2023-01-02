package com.example.feature_marvel.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MarvelBookmarkScreen(viewModel: MarvelBookmarkViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {

        Button(onClick = { viewModel.clickButton() }) {
            Text(text = viewModel.inputState.value)
        }

        Button(onClick = { viewModel.clickButton() }) {
            Text(text = viewModel.inputState.value)
        }

        Button(onClick = { viewModel.clickButton() }) {
            Text(text = viewModel.inputState.value)
        }
        Button(onClick = { viewModel.clickButton() }) {
            Text(text = viewModel.inputState.value)
        }

    }
}
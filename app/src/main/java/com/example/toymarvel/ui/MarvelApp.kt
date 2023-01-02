package com.example.toymarvel.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.core_ui.theme.ToyMarvelTheme
import com.example.toymarvel.navigation.MarvelNavigation


@Composable
fun MarvelApp() {

    ToyMarvelTheme(darkTheme = false) {

        val navController = rememberNavController()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MarvelNavigation(navHostController = navController)
        }

    }

}
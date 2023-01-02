package com.example.toymarvel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.feature_marvel.navigation.*


@Composable
fun MarvelNavigation(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = MarvelNavigation.route
) {

    NavHost(navController = navHostController, startDestination = startDestination) {
        marvelGraph()
    }
}
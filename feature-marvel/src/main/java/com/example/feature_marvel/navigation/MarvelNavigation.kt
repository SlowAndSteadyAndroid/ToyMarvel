package com.example.feature_marvel.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core_navigation.NavigationDestination
import com.example.feature_marvel.MarvelScreen


object MarvelNavigation : NavigationDestination {
    override val route: String = "marvel_route"
    override val destination: String = "marvel_destination"
}

fun NavGraphBuilder.marvelGraph() {
    composable(route = MarvelNavigation.route) {
        MarvelScreen()
    }
}

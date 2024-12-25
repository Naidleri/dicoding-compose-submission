package com.ned.disneycharacter.ui.navigation

sealed class Screen (val route : String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{characterId}") {
        fun createRoute(characterId: Int) = "detail/$characterId"
    }
    object About : Screen("about")
    object Favorite : Screen("favorite")
}
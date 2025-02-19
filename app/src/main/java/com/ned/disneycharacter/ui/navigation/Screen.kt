package com.ned.disneycharacter.ui.navigation

@Suppress("ConvertObjectToDataObject", "ConvertObjectToDataObject")
sealed class Screen (val route : String) {
    object Home : Screen("home")
    object Detail : Screen("home/{characterId}") {
        fun createRoute(characterId: Int) = "home/$characterId"
    }
    object Profile : Screen("profile")
    object Favorite : Screen("favorite")
}
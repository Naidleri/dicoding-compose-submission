@file:Suppress("DEPRECATION")

package com.ned.favorite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.core.context.loadKoinModules
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.compose.AppTheme
import com.ned.disneycharacter.ui.navigation.NavigationItem
import com.ned.disneycharacter.ui.navigation.Screen


class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            loadKoinModules(favoriteModule)
        } catch (e: Exception) {
            // Handle if module already loaded
        }

        setContent {
            AppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController, context = this)
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        FavoriteNavHost()
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Favorite",
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selected = item.screen == Screen.Favorite,
                onClick = {
                    if (item.screen.route != Screen.Favorite.route) {
                        val intent = Intent(context, Class.forName("com.ned.disneycharacter.MainActivity"))
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        intent.putExtra("navigation_route", item.screen.route)
                        context.startActivity(intent)
                        if (context is Activity) {
                            context.overridePendingTransition(0, 0)
                            context.finish()
                        }
                    }
                }

            )
        }
    }
}

@Composable
fun FavoriteNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "favorite") {
        composable("favorite") {
            FavoriteScreen(
                navController = navController,
                navigateToDetail = { characterId ->
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("app://detail/$characterId")).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    navController.context.startActivity(intent)
                }
            )
        }
    }
}
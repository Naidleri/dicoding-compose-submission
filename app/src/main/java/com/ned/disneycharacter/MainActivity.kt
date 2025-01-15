package com.ned.disneycharacter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.ned.disneycharacter.ui.navigation.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                LaunchedEffect(intent) {

                    intent?.data?.let { uri ->
                        when {
                            uri.host == "detail" -> {
                                val characterId = uri.lastPathSegment?.toIntOrNull() ?: return@let
                                navController.navigate(Screen.Detail.createRoute(characterId)) {
                                    launchSingleTop = true
                                }
                            }
                        }
                    }

                    intent?.getStringExtra("navigation_route")?.let { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                            popUpTo(Screen.Home.route)
                        }
                    }
                }
                DisneyApp(navController = navController)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
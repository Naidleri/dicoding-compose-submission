package com.ned.disneycharacter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.compose.AppTheme
import com.ned.disneycharacter.ui.component.CharacterItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharacterItem(
                        name = "Mickey Mouse",
                        image = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRct2_Zpu245wRsqke5kZaQxB_1Q6J6K0RtpGS2dd8Q2BV05_xtHu7CYhugZ1hgOaGfchyi_Lw4_xpFl1jDP9q9Nm7RGrA88buDztiTxEHD"
                    )
                }
            }
        }
    }
}

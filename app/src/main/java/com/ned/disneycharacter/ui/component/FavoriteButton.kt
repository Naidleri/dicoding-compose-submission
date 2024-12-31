package com.ned.disneycharacter.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme

@Composable
fun FavoriteButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isFavorite: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = if (isFavorite) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = if (isFavorite) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPrev() {
    AppTheme {
        FavoriteButton(
            text = "Tambah Character ke Favorite",
            onClick = {}
        )
    }
}
package com.ned.disneycharacter.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.AppTheme

@Composable
fun CharacterItem (
    name: String,
    image: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
       AsyncImage(
           model = image,
           contentDescription = null,
           modifier = Modifier
               .size(100.dp)
               .clip(RoundedCornerShape(8.dp)),
           contentScale = ContentScale.Crop
       )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun CharacterItemPreview() {
    AppTheme {
        CharacterItem(
            name = "Mickey Mouse",
            image = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRct2_Zpu245wRsqke5kZaQxB_1Q6J6K0RtpGS2dd8Q2BV05_xtHu7CYhugZ1hgOaGfchyi_Lw4_xpFl1jDP9q9Nm7RGrA88buDztiTxEHD"
        )
    }
}
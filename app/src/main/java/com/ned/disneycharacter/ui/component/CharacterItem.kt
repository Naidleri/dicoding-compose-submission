package com.ned.disneycharacter.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ned.disneycharacter.ui.theme.AppTheme
import com.ned.disneycharacter.R

@Composable
fun CharacterItem(
    image: String?,
    name: String,
    isLoading: Boolean = true,
    modifier: Modifier = Modifier,
) {
    ShimmerEffect (
        isLoading = isLoading,
        contentAfterLoading = {
            Card(
                modifier = modifier
                    .padding(8.dp)
                    .width(180.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(image)
                            .crossfade(true)
                            .placeholder(R.drawable.placeholder_image)
                            .error(R.drawable.placeholder_image)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(140.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
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
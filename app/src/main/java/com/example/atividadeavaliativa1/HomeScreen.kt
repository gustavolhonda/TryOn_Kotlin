package com.example.atividadeavaliativa1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Todos", "Promoções", "Lojas")
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFFCEAE5)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = { /* fun */ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    tint = Color(0xFF5C4438)
                )
            }
            Text(
                text = "Alameda dos Miosótis",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = Color(0xFF5C4438),
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
            IconButton(onClick = { /* fun */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color(0xFF5C4438)
                )
            }
        }
        // TABS
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFFFCEAE5),
            contentColor = Color(0xFF5C4438),
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .padding(horizontal = 45.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                    height = 3.dp,
                    color = Color(0xFF8B3A2E)
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = Color(0xFF8B3A2E),
                    unselectedContentColor = Color(0xFF5C4438),
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Destaques",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(12.dp))

            val imagens = listOf(
                "https://satotrader.com.br/site/views/data/noticias/acoes-das-lojas-renner-lren3-comprar-ou-vender-image-1713213671.jpg",
                "https://satotrader.com.br/site/views/data/noticias/acoes-das-lojas-renner-lren3-comprar-ou-vender-image-1713213671.jpg",
                "https://satotrader.com.br/site/views/data/noticias/acoes-das-lojas-renner-lren3-comprar-ou-vender-image-1713213671.jpg"
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(imagens.size) { index ->
                    Card(
                        modifier = Modifier
                            .width(220.dp)
                            .height(120.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        AsyncImage(
                            model = imagens[index],
                            contentDescription = "Imagem $index",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}



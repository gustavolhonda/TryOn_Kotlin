package com.example.atividadeavaliativa1

import android.util.Log // Importar Log
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.atividadeavaliativa1.repository.retrofit.Product
import com.example.atividadeavaliativa1.repository.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment.Companion.TopEnd

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Todos", "Promoções", "Lojas")
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val fetchedProducts = RetrofitInstance.productApi.getProducts()
                products = fetchedProducts
            } catch (e: Exception) {
                Log.e("HomeScreen", "Erro ao buscar produtos", e)
            }
        }
    }

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
                .fillMaxWidth()
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
        ) {
            Text(
                text = "Produtos",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // DESCOMENTAR PARA ANDROID VIEWER
                // items(listOf(
                //     Product(
                //         id = 1,
                //         name = "Vestido Floral",
                //         price = 199.90,
                //         shopName = "Loja da Maria",
                //         shopCategory = "Moda Feminina",
                //         description = "Vestido floral com manga curta",
                //         rating = 4.5,
                //         avaliableSizes = listOf("P", "M", "G"),
                //         image = "https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"
                //     ),
                //     Product(
                //         id = 2,
                //         name = "Camisa Social",
                //         price = 159.90,
                //         shopName = "Loja do João",
                //         shopCategory = "Moda Masculina",
                //         description = "Camisa social azul marinho",
                //         rating = 4.0,
                //         avaliableSizes = listOf("M", "G", "GG"),
                //         image = "https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"
                //     ),
                //     Product(
                //         id = 3,
                //         name = "Calça Jeans",
                //         price = 249.90,
                //         shopName = "Jeans Store",
                //         shopCategory = "Moda Casual",
                //         description = "Calça jeans skinny",
                //         rating = 4.8,
                //         avaliableSizes = listOf("38", "40", "42"),
                //         image = "https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"
                //     ),
                //     Product(
                //         id = 4,
                //         name = "Tênis Casual",
                //         price = 299.90,
                //         shopName = "Shoes Store",
                //         shopCategory = "Calçados",
                //         description = "Tênis casual preto",
                //         rating = 4.2,
                //         avaliableSizes = listOf("38", "39", "40"),
                //         image = "https://m.media-amazon.com/images/I/41Tbr4iFggL._AC_.jpg"
                //     )
                // ))
                items(products)
                { product ->
                    ProductCard(product = product)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
        ) {
            Text(
                text = "Lojas",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            val lojas = listOf(
                Pair("Street Wear", "https://randomuser.me/api/portraits/men/1.jpg"),
                Pair("Street Wear", "https://randomuser.me/api/portraits/men/1.jpg"),
                Pair("Street Wear", "https://randomuser.me/api/portraits/men/1.jpg"),
                Pair("Street Wear", "https://randomuser.me/api/portraits/men/1.jpg")
            )
            val bgUrl = "https://img.freepik.com/fotos-gratis/roupas-infantis_23-2148166232.jpg"

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 0..1) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFFF7F5))
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = lojas[i].second,
                                    contentDescription = "Avatar",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 12.dp)
                                        .clip(RoundedCornerShape(50)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = lojas[i].first,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Loja de roupas",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 8.sp
                                        ),
                                        color = Color.Gray
                                    )
                                }
                                AsyncImage(
                                    model = bgUrl,
                                    contentDescription = "Background",
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(70.dp)
                                        .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 2..3) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFFF7F5))
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = lojas[i].second,
                                    contentDescription = "Avatar",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 12.dp)
                                        .clip(RoundedCornerShape(50)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = lojas[i].first,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Loja de roupas",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 8.sp
                                        ),
                                        color = Color.Gray
                                    )
                                }
                                AsyncImage(
                                    model = bgUrl,
                                    contentDescription = "Background",
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(70.dp)
                                        .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
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

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        onClick = {/* Depois fazer abrir pagina do produto */},
        modifier = modifier
            .width(160.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .align(TopEnd)
                    .padding(8.dp)
                    .size(28.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favoritar",
                    tint = Color(0xFF8B3A2E)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFCEAE5))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "R$ %.2f".format(product.price),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF8B3A2E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = "https://i0.wp.com/assets.b9.com.br/wp-content/uploads/2020/07/B.png?fit=554%2C673&ssl=1",
                        contentDescription = "Loja",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Column {
                        Text(
                            text = product.shopName,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = product.shopCategory,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
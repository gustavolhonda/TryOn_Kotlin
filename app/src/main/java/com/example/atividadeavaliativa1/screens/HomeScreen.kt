package com.example.atividadeavaliativa1.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.atividadeavaliativa1.repository.retrofit.Product
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.atividadeavaliativa1.viewmodels.HomeScreenViewModel
import com.example.atividadeavaliativa1.viewmodels.ProductViewModel
import com.example.atividadeavaliativa1.R
import com.example.atividadeavaliativa1.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel(),
    viewModel: HomeScreenViewModel = viewModel()
) {
    val selectedTabIndex by viewModel::selectedTabIndex
    val tabTitles = viewModel.tabTitles
    val products by viewModel::products
    val isLoading by viewModel::isLoading
    val error by viewModel::error
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Column( 
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
                    contentDescription = stringResource(R.string.home_profile_description),
                    tint = Color(0xFF5C4438)
                )
            }
            Text(
                text = stringResource(R.string.home_tab_address),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = Color(0xFF5C4438),
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
            IconButton(onClick = { /* fun */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.home_search_description),
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
            tabTitles.forEachIndexed { index, _ ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { viewModel.updateSelectedTab(index) },
                    selectedContentColor = Color(0xFF8B3A2E),
                    unselectedContentColor = Color(0xFF5C4438),
                    text = {
                        Text(
                            text = when(index) {
                                0 -> stringResource(R.string.home_tab_all)
                                1 -> stringResource(R.string.home_tab_promotions)
                                2 -> stringResource(R.string.home_tab_stores)
                                else -> ""
                            },
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
                text = stringResource(R.string.home_section_highlights),
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
                text = stringResource(R.string.home_section_products),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else if (error != null) {
                Text(
                    text = stringResource(R.string.home_error_loading_products),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products) { product ->
                        val isFavorite = productViewModel.isFavorite(product.id)
                        ProductCard(
                            product = product,
                            isFavorite = isFavorite,
                            onToggleFavorite = { productViewModel.toggleFavorite(product.id) },
                            navController = navController,
                            productViewModel = productViewModel
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
                text = stringResource(R.string.home_section_stores),
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
                                    contentDescription = stringResource(R.string.home_avatar_description),
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
                                        text = stringResource(R.string.home_store_category),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 8.sp
                                        ),
                                        color = Color.Gray
                                    )
                                }
                                AsyncImage(
                                    model = bgUrl,
                                    contentDescription = stringResource(R.string.home_background_description),
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
                                    contentDescription = stringResource(R.string.home_avatar_description),
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
                                        text = stringResource(R.string.home_store_category),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 8.sp
                                        ),
                                        color = Color.Gray
                                    )
                                }
                                AsyncImage(
                                    model = bgUrl,
                                    contentDescription = stringResource(R.string.home_background_description),
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
    val navController: NavHostController = rememberNavController()
    HomeScreen(navController)
}

@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    navController: NavController,
    productViewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            productViewModel.selectProduct(product)
            navController.navigate(Screen.Product.route)
        },
        modifier = modifier
            .width(160.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(140.dp)) {
            AsyncImage(
                model = product.image[0],
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { onToggleFavorite() },
                modifier = Modifier.align(TopEnd).padding(8.dp).size(28.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.home_favorite_description),
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
                        contentDescription = stringResource(R.string.home_store_description),
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
package com.example.atividadeavaliativa1.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.example.atividadeavaliativa1.viewmodels.ProductViewModel
import com.example.atividadeavaliativa1.R
import com.example.atividadeavaliativa1.repository.retrofit.Product

@SuppressLint("DefaultLocale")
@Composable
fun ProductScreen(
    product: Product,
    productViewModel: ProductViewModel,
    onBackClick: () -> Unit
) {
    val showToast by productViewModel.showToast
    val toastMessage by productViewModel.toastMessage
    val context = LocalContext.current

    LaunchedEffect(showToast) {
        if (showToast) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            productViewModel.resetToast()
        }
    }

    LaunchedEffect(product.id) {
        productViewModel.initializeSelectedImage(product.image.firstOrNull())
        productViewModel.initFavorite(product.id, product.isFavorite ?: false)
    }

    val selectedImage by productViewModel.selectedImage
    val isFavorite = productViewModel.isFavorite(product.id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra superior com botão voltar e favorito
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.product_screen_page_back_button_content_description)
                )
            }

            IconButton(onClick = {
                productViewModel.toggleFavorite(product.id)
            }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite)
                        stringResource(R.string.product_screen_favorite_description)
                    else
                        stringResource(R.string.product_screen_not_favorite_description),
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Imagem principal do produto
        AsyncImage(
            model = selectedImage,
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Lista horizontal de miniaturas para trocar a imagem principal
        val imageList = product.image

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(imageList) { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Imagem do produto",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {  productViewModel.setSelectedImage(imageUrl) }
                        .border(
                            width = if (selectedImage == imageUrl) 2.dp else 0.dp,
                            color = if (selectedImage == imageUrl) Color(0xFF8B3A2E) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Nome e avaliação
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Coluna para Categoria e Título
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp) // Espaçamento entre categoria e título
            ) {
                Text(
                    text = product.shopCategory,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Coluna para a Avaliação (estrela e nota)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription =  stringResource(R.string.product_screen_rating_description),
                    tint = Color(0xFFFFC107) // Amarelo
                )
                Text(
                    text = product.rating.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Descrição resumida (limitar a algumas linhas)
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título Escolha o tamanho
        Text(
            text = stringResource(R.string.product_screen_choose_size),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        val selectedSize by productViewModel.selectedSize

        // Lista de tamanhos em linha
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(product.avaliableSizes) { size ->
                val isSelected = size == selectedSize
                OutlinedButton(
                    onClick = { productViewModel.selectSize(size) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) Color(0xFF8B3A2E) else Color.Transparent,
                        contentColor = if (isSelected) Color.White else Color.Black
                    ),
                    border = if (isSelected) null else ButtonDefaults.outlinedButtonBorder
                ) {
                    Text(text = size)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = String.format(stringResource(R.string.product_screen_price_format), product.price),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Botão adicionar ao carrinho
        Button(
            onClick = {
                if (selectedSize != null) {
                    productViewModel.addToCart(product, selectedSize!!)
                }
            },
            enabled = selectedSize != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A2E))
        ) {
            Text(
                text = stringResource(R.string.product_screen_add_to_cart),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFFFFFFF)
            )
        }
    }
}

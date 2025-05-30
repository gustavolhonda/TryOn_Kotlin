package com.example.atividadeavaliativa1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.atividadeavaliativa1.viewmodels.OrdersScreenViewModel
import com.example.atividadeavaliativa1.R
import com.example.atividadeavaliativa1.room.CartItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    viewModel: OrdersScreenViewModel = viewModel()
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val totalPrice by viewModel::totalPrice

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                    contentDescription = stringResource(R.string.cart_page_title),
                    tint = Color(0xFF5C4438)
                )
            }
            Text(
                text = stringResource(R.string.cart_page_title),
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

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            cartItems.forEach { item ->
                OrderItem(
                    item = item,
                    onIncrease = { viewModel.increaseQuantity(item.id) },
                    onDecrease = { viewModel.decreaseQuantity(item.id) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        CheckoutBottomBar(totalPrice)
    }
}

@Composable
fun OrderItem(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFF8F6))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF3A2D4D)
            )
            Text(
                text = "R$%.2f".format(item.price),
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF3A2D4D)
            )
            Text(
                text = "Tamanho: ${item.size}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF3A2D4D)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onDecrease,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE5D8CF))
                    .height(10.dp)
                    .width(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Diminuir",
                    tint = Color(0xFF8B3A2E)
                )
            }
            Text(
                text = item.quantity.toString(),
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF3A2D4D)
            )
            IconButton(
                onClick = onIncrease,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE5D8CF))
                    .height(10.dp)
                    .width(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Aumentar",
                    tint = Color(0xFF8B3A2E)
                )
            }
        }
    }
}

@Composable
fun CheckoutBottomBar(totalPrice: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEFB8C8), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(R.string.cart_page_total),
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF5C4438)
            )
            Text(
                text = "R$%.2f".format(totalPrice),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF3A2D4D)
            )
        }
        Button(
            onClick = { /* fun */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF8F6)),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = stringResource(R.string.cart_page_checkout),
                color = Color(0xFF8B3A2E)
            )
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Preview(showBackground = true)
@Composable
fun OrdersScreenPreview() {
    OrdersScreen()
}
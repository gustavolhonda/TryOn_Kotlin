package com.example.atividadeavaliativa1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.atividadeavaliativa1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.register_page_title),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
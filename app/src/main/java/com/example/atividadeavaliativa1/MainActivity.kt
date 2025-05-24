package com.example.atividadeavaliativa1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.atividadeavaliativa1.ui.theme.AtividadeAvaliativa1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtividadeAvaliativa1Theme {
                App(startingRoute = "login")
            }
        }
    }
}
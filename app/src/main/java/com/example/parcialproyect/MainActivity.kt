package com.example.parcialproyect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels // ¡Importante importar esto!
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {

    // Usamos el delegado 'by viewModels' para una gestión profesional de memoria
    private val biteBoxViewModel by viewModels<BiteBoxViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Pasamos nuestro ViewModel profesional al sistema de navegación
                AppNavigation(viewModel = biteBoxViewModel)
            }
        }
    }
}
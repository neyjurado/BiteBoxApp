package com.example.parcialproyect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun PantallaLogin(navController: NavController) {
    var nombre by rememberSaveable { mutableStateOf("") }

    // Definición de la paleta de colores para el nuevo estilo
    val azulMarino = Color(0xFF0D1B2A)
    val verdeBoton = Color(0xFF2E7D32)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(azulMarino)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "GORDO'S NEY",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Ingresa tu nombre") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("catalogo/$nombre") },
                enabled = nombre.length > 3,
                colors = ButtonDefaults.buttonColors(
                    containerColor = verdeBoton,
                    contentColor = Color.White,
                    disabledContainerColor = verdeBoton.copy(alpha = 0.5f),
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text("Entrar")
            }
        }
    }
}
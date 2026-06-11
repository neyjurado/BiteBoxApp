package com.example.parcialproyect

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaCarrito(navController: NavController, viewModel: BiteBoxViewModel) {
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Tu Pedido", style = MaterialTheme.typography.headlineMedium)

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.carrito) { platillo ->//lista de platillos desde detalles
                    Text("${platillo.nombre} - $${platillo.precio}")
                }
            }

            Text("Total: $${viewModel.calcularTotal()}", style = MaterialTheme.typography.headlineSmall)

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.vaciarCarrito()
                    navController.navigate("login") // Volver al inicio tras confirmar
                }
            ) {
                Text("Confirmar Pedido")
            }
        }
    }
}

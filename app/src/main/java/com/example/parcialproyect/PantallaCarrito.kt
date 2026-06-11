package com.example.parcialproyect

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaCarrito(navController: NavController, viewModel: BiteBoxViewModel) {
    val carritoAgrupado = viewModel.carrito.groupBy { it }

    val azulMarino = Color(0xFF0D1B2A)
    val verdeBoton = Color(0xFF2E7D32)
    val fondoTarjeta = Color(0xFF1B2A40)

    Scaffold(
        containerColor = azulMarino
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {

            Text(
                text = "Tu Pedido",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(carritoAgrupado.toList()) { (platillo, listaRepetidos) ->
                    val cantidad = listaRepetidos.size
                    val subtotal = platillo.precio * cantidad

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = fondoTarjeta)
                    ) {
                        Text(
                            text = "${cantidad}x ${platillo.nombre} - $$subtotal",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: $${viewModel.calcularTotal()}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.vaciarCarrito()
                    navController.navigate("login")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = verdeBoton,
                    contentColor = Color.White
                )
            ) {
                Text("Confirmar Pedido")
            }
        }
    }
}
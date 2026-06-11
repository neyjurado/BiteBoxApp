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
    val carritoAgrupado = viewModel.carrito.groupBy { it }

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Tu Pedido", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(carritoAgrupado.toList()) { (platillo, listaRepetidos) ->
                    val cantidad = listaRepetidos.size
                    val subtotal = platillo.precio * cantidad

                    Text(
                        text = "${cantidad}x ${platillo.nombre} - $$subtotal",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: $${viewModel.calcularTotal()}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.vaciarCarrito()
                    navController.navigate("login")
                }
            ) {
                Text("Confirmar Pedido")
            }
        }
    }
}
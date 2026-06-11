package com.example.parcialproyect

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun PantallaDetalles(navController: NavController, platilloId: Int, viewModel: BiteBoxViewModel) {

    // Buscamos el platillo en el ViewModel usando el ID recibido
    val platillo = viewModel.obtenerPlatilloPorId(platilloId)

    Scaffold { padding ->
        if (platillo != null) {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                AsyncImage(
                    model = platillo.urlImagen,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(platillo.nombre, style = MaterialTheme.typography.headlineMedium)
                Text("Precio: $${platillo.precio}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))

                Text(platillo.descripcion, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.agregarAlCarrito(platillo)
                        navController.navigate("carrito")
                    }
                ) {
                    Text("Añadir al Carrito")
                }
            }
        } else {
            // Manejo por si el ID no existe
            Text("Platillo no encontrado")
        }
    }
}

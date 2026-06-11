package com.example.parcialproyect

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalles(navController: NavController, platilloId: Int, viewModel: BiteBoxViewModel) {
    // Buscamos el platillo seleccionado usando el ID recibido por la ruta
    val platillo = viewModel.obtenerPlatilloPorId(platilloId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("< Volver") // Permite regresar sin añadir nada
                    }
                }
            )
        }
    ) { padding ->
        if (platillo != null) {
            Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {

                // Imagen del platillo con Coil
                AsyncImage(
                    model = platillo.urlImagen,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(220.dp).padding(bottom = 16.dp)
                )

                Text(platillo.nombre, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "Categoría: ${platillo.categoria}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(platillo.descripcion, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Precio: $${platillo.precio}", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.weight(1f))

                // BOTÓN CRÍTICO DEL FLUJO
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // 1. Agrega el producto al estado global del ViewModel
                        viewModel.agregarAlCarrito(platillo)

                        // 2. RETORNO AUTOMÁTICO: Regresa al catálogo de inmediato
                        navController.popBackStack()
                    }
                ) {
                    Text("Añadir al Carrito")
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("El producto no se encuentra disponible.")
            }
        }
    }
}
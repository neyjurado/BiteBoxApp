package com.example.parcialproyect

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale // Importante para que la imagen se adapte
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalles(navController: NavController, platilloId: Int, viewModel: BiteBoxViewModel) {
    val azulMarino = Color(0xFF0D1B2A)
    val verdeBoton = Color(0xFF2E7D32)

    val platillo = viewModel.obtenerPlatilloPorId(platilloId)

    Scaffold(
        containerColor = azulMarino,
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Producto", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("⬅", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = azulMarino
                )
            )
        }
    ) { padding ->
        if (platillo != null) {
            Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {

                AsyncImage(
                    model = platillo.urlImagen,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(220.dp).padding(bottom = 16.dp)
                )

                Text(platillo.nombre, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Text(
                    text = "Categoría: ${platillo.categoria}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(platillo.descripcion, style = MaterialTheme.typography.bodyLarge, color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Precio: $${platillo.precio}", style = MaterialTheme.typography.headlineSmall, color = Color.White)

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        viewModel.agregarAlCarrito(platillo)

                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = verdeBoton,
                        contentColor = Color.White
                    )
                ) {
                    Text("Añadir al Carrito")
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("El producto no se encuentra disponible.", color = Color.White)
            }
        }
    }
}
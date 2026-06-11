package com.example.parcialproyect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun PantallaCatalogo(navController: NavController, nombreUsuario: String, viewModel: BiteBoxViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("carrito") }) {
                Text("Carrito")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text("Hola, $nombreUsuario", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                items(listOf("Todos", "Pizzas", "Hamburguesas")) { categoria ->
                    FilterChip(
                        selected = false,
                        onClick = { /* TODO: Implementar lógica de filtrado */ },
                        label = { Text(categoria) }
                    )
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(viewModel.catalogo) { platillo ->
                    Card(modifier = Modifier.fillMaxWidth().clickable {
                        navController.navigate("detalles/${platillo.id}")
                    }) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            AsyncImage(
                                model = platillo.urlImagen,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().height(150.dp).padding(bottom = 8.dp)
                            )
                            Text(platillo.nombre, style = MaterialTheme.typography.titleLarge)
                            Text("Precio: $${platillo.precio}")
                        }
                    }
                }
            }
        }
    }
}

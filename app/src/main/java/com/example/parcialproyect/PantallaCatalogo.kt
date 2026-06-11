package com.example.parcialproyect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogo(navController: NavController, nombreUsuario: String, viewModel: BiteBoxViewModel) {
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    val azulMarino = Color(0xFF0D1B2A)
    val verdeBoton = Color(0xFF2E7D32)
    val fondoTarjeta = Color(0xFF1B2A40)

    val platillosFiltrados = if (categoriaSeleccionada == "Todos") {
        viewModel.catalogo
    } else {
        viewModel.catalogo.filter { it.categoria == categoriaSeleccionada }
    }

    Scaffold(
        containerColor = azulMarino,
        topBar = {
            TopAppBar(
                title = { Text("Hola, $nombreUsuario") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = azulMarino,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("carrito") },
                containerColor = verdeBoton,
                contentColor = Color.White
            ) {
                Text("Carrito", modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                items(listOf("Todos", "Pizzas", "Hamburguesas")) { categoria ->
                    FilterChip(
                        selected = categoriaSeleccionada == categoria,
                        onClick = { categoriaSeleccionada = categoria },
                        label = { Text(categoria) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = verdeBoton,
                            selectedLabelColor = Color.White,
                            labelColor = Color.LightGray
                        )
                    )
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(platillosFiltrados) { platillo ->
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable {
                            navController.navigate("detalles/${platillo.id}")
                        },

                        colors = CardDefaults.cardColors(
                            containerColor = fondoTarjeta
                        )
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            AsyncImage(
                                model = platillo.urlImagen,
                                contentDescription = "Imagen de ${platillo.nombre}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth().height(180.dp).padding(bottom = 8.dp)
                            )

                            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                                Text(platillo.nombre, style = MaterialTheme.typography.titleLarge, color = Color.White)
                                Text(
                                    text = "Categoría: ${platillo.categoria}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.LightGray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Precio: $${platillo.precio}", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}
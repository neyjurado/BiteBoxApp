package com.example.parcialproyect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class) // Necesario para usar TopAppBar en Material 3
@Composable
fun PantallaCatalogo(navController: NavController, nombreUsuario: String, viewModel: BiteBoxViewModel) {
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    val platillosFiltrados = if (categoriaSeleccionada == "Todos") {
        viewModel.catalogo
    } else {
        viewModel.catalogo.filter { it.categoria == categoriaSeleccionada }
    }

    Scaffold(
        // REQUERIMIENTO 1: TopAppBar superior
        topBar = {
            TopAppBar(
                title = { Text("Hola, $nombreUsuario") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        // REQUERIMIENTO 5: Botón Flotante
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("carrito") }) {
                Text("Carrito")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {

            // REQUERIMIENTO 2: Filtros (Row)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                items(listOf("Todos", "Pizzas", "Hamburguesas")) { categoria ->
                    FilterChip(
                        selected = categoriaSeleccionada == categoria,
                        onClick = { categoriaSeleccionada = categoria },
                        label = { Text(categoria) }
                    )
                }
            }

            // REQUERIMIENTO 3 y 4: Lista (LazyColumn), Card atractiva, Imágenes Coil y Navegación
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(platillosFiltrados) { platillo ->
                    Card(modifier = Modifier.fillMaxWidth().clickable {
                        // Navegación pasando el ID
                        navController.navigate("detalles/${platillo.id}")
                    }) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Foto con Coil
                            AsyncImage(
                                model = platillo.urlImagen,
                                contentDescription = "Imagen de ${platillo.nombre}",
                                modifier = Modifier.fillMaxWidth().height(150.dp).padding(bottom = 8.dp)
                            )
                            // Textos requeridos: Nombre, Categoría y Precio
                            Text(platillo.nombre, style = MaterialTheme.typography.titleLarge)
                            Text(
                                text = "Categoría: ${platillo.categoria}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Precio: $${platillo.precio}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}
package com.example.parcialproyect

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(viewModel: BiteBoxViewModel) {
    val navController = rememberNavController()

    // El NavHost es el componente que cambia de pantalla
    NavHost(navController = navController, startDestination = "login") {

        // 1. Login
        composable("login") { PantallaLogin(navController) }

        // 2. Catálogo (Recibe el nombre del usuario)
        composable("catalogo/{nombreUsuario}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombreUsuario") ?: ""
            PantallaCatalogo(navController, nombre, viewModel)
        }

        // 3. Detalles (Recibe el ID del platillo)
        composable(
            route = "detalles/{platilloId}",
            arguments = listOf(navArgument("platilloId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("platilloId") ?: 0
            PantallaDetalles(navController, id, viewModel)
        }

        // 4. Carrito
        composable("carrito") { PantallaCarrito(navController, viewModel) }
    }
}

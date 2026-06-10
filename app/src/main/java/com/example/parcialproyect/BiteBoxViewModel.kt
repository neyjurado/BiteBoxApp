package com.example.parcialproyect

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BiteBoxViewModel : ViewModel() {

    // Lista principal "hardcoded"
    val catalogo = listOf(
        Platillo(
            id = 1,
            nombre = "Pizza Margarita",
            descripcion = "Clásica con tomate y albahaca",
            precio = 12.50,
            categoria = "Pizzas",
            urlImagen = "https://ejemplo.com/pizza1.jpg"
        ),
        Platillo(
            id = 2,
            nombre = "Pizza Pepperoni",
            descripcion = "Con rodajas de pepperoni",
            precio = 14.00,
            categoria = "Pizzas",
            urlImagen = "https://ejemplo.com/pizza2.jpg"
        ),
        Platillo(
            id = 3,
            nombre = "Pizza Hawaiana",
            descripcion = "Jamón y piña",
            precio = 13.50,
            categoria = "Pizzas",
            urlImagen = "https://ejemplo.com/pizza3.jpg"
        ),
        Platillo(
            id = 4,
            nombre = "Hamburguesa Clásica",
            descripcion = "Carne, queso y lechuga",
            precio = 8.50,
            categoria = "Hamburguesas",
            urlImagen = "https://ejemplo.com/hamb1.jpg"
        ),
        Platillo(
            id = 5,
            nombre = "Hamburguesa Doble",
            descripcion = "Doble carne y cheddar",
            precio = 10.50,
            categoria = "Hamburguesas",
            urlImagen = "https://ejemplo.com/hamb2.jpg"
        ),
        Platillo(
            id = 6,
            nombre = "Hamburguesa BBQ",
            descripcion = "Con salsa BBQ y cebolla",
            precio = 9.50,
            categoria = "Hamburguesas",
            urlImagen = "https://ejemplo.com/hamb3.jpg")
    )

    // Lista reactiva para el carrito
    private val _carrito = mutableStateListOf<Platillo>()
    val carrito: List<Platillo> get() = _carrito

    // Funciones de la aplicacion

    fun agregarAlCarrito(platillo: Platillo) {
        _carrito.add(platillo)
    }

    fun vaciarCarrito() {
        _carrito.clear()
    }

    fun calcularTotal(): Double {
        return _carrito.sumOf { it.precio }
    }

    fun obtenerPlatilloPorId(id: Int): Platillo? {
        return catalogo.find { it.id == id }
    }


}
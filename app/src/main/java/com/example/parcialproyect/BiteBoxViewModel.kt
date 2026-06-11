package com.example.parcialproyect

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BiteBoxViewModel : ViewModel() {

    // Lista principal "hardcoded"
    val catalogo = listOf(
        Platillo(
            id = 1,
            nombre = "Pizza Hawaiiana",
            descripcion = "Jamón y piña",
            precio = 13.50,
            categoria = "Pizzas",
            urlImagen = "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=500&auto=format&fit=crop"
        ),
        Platillo(
            id = 2,
            nombre = "Hamburguesa BBQ",
            descripcion = "Con salsa BBQ y cebolla",
            precio = 9.50,
            categoria = "Hamburguesas",
            urlImagen = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=500&auto=format&fit=crop"
        ),
        Platillo(
            id = 3,
            nombre = "Hamburguesa Clásica",
            descripcion = "Carne, queso y lechuga",
            precio = 8.50,
            categoria = "Hamburguesas",
            urlImagen = "https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?q=80&w=500&auto=format&fit=crop"
        ),
        Platillo(
            id = 4,
            nombre = "Hamburguesa Doble",
            descripcion = "Doble carne y cheddar",
            precio = 10.50,
            categoria = "Hamburguesas",
            urlImagen = "https://images.unsplash.com/photo-1586816001966-79b736744398?q=80&w=500&auto=format&fit=crop"
        ),
        Platillo(
            id = 5,
            nombre = "Hamburguesa mexicana",
            descripcion = "Carne, queso y guacamole",
            precio = 8.50,
            categoria = "Hamburguesas",
            urlImagen = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHZqORdCg0X31J4a0VAm_LJgYGZXXAloqNecDrGooVU6IK-oBTNJMQZaO7wmyL"
        ),
        Platillo(
            id = 6,
            nombre = "Pízza con carne",
            descripcion = " carne y queso",
            precio = 10.50,
            categoria = "Pizzas",
            urlImagen = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSglCPl4_l9aEyw5I_vsmX_gtJJGWjH-0BpUfYfl7ZiWxA-EMlSX9uYA6ogWsYD"
        ),
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
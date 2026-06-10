package com.example.parcialproyect

data class Platillo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String, // "Pizza" o "Hamburguesa"
    val urlImagen: String
)
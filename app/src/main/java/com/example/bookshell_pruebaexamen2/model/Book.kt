package com.example.bookshell_pruebaexamen2.model

data class Book(
    val title: String,
    val author: String,
    val isbn: String,
    val year: String = "1990",
    val publisher: String = "None",
    var isFavorite: Boolean = false
    // TODO: Se pueden añadir más campos si el enunciado lo pide
)
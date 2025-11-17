package com.example.bookshell_pruebaexamen2.rutas

sealed class Routes(val route: String) {
    object Login : Routes("LoginScreen")
    object Library : Routes("LibraryScreen")
    object Add : Routes("AddBookScreen")
    // TODO: Añadir más rutas si fueran necesarias
}
package com.example.bookshell_pruebaexamen2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshell_pruebaexamen2.model.Book
import com.example.bookshell_pruebaexamen2.rutas.Routes
import com.example.bookshell_pruebaexamen2.ui.AddBookScreen
import com.example.bookshell_pruebaexamen2.ui.LibraryScreen
import com.example.bookshell_pruebaexamen2.ui.LoginScreen
import com.example.bookshell_pruebaexamen2.ui.theme.BookShell_pruebaExamen2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookShell_pruebaExamen2Theme {
                var books by remember { mutableStateOf(mutableListOf<Book>(
                    Book("Harry Potter y la priedra filosofal", "JK Rowling", "123456789"),
                    Book ("Nunca Noche","Jay Karson","987654321"),
                    Book ("After","Anna","123987465"),
                    Book ("Todo lo que nunca fuimos","Alice Kellen","546732891")
                )) }

                val navControl = rememberNavController()
                NavHost(navController = navControl,
                    startDestination = Routes.Login.route) {

                    composable(route = Routes.Login.route) {
                        LoginScreen(navControl)
                    }

                    composable(Routes.Library.route) {
                        LibraryScreen(navControl, books) {
                            isbn ->
                            books = books.map { book ->
                                if (book.isbn == isbn)
                                    book.copy(isFavorite = !book.isFavorite)
                                else
                                    book
                            }.toMutableList()
                        }

                    }

                    composable(Routes.Add.route) {
                        AddBookScreen(navControl) { books.add(it) }
                    }
                }
            }
        }
    }
}
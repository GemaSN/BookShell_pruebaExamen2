package com.example.bookshell_pruebaexamen2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookshell_pruebaexamen2.model.Book
import com.example.bookshell_pruebaexamen2.rutas.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(navController: NavHostController, books: List<Book>, onToggleFavorite: (String) -> Unit) {
    var filterMode by remember { mutableStateOf("ALL") }
    var authorFilter by remember { mutableStateOf("") }

    val filteredBooks = when (filterMode) {
        "FAVORITES" -> books.filter { it.isFavorite }
        "AUTHOR" -> books.filter { it.author.contains(authorFilter, ignoreCase = true) }
        else -> books
    }

    Scaffold(
        topBar = {TopAppBar(title = {Text("Biblioteca (${filteredBooks.size})")})},
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = filterMode == "ALL",
                    onClick = {filterMode = "ALL"},
                    icon = {Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "All")},
                    label = {Text("Todos")}
                )

                NavigationBarItem(
                    selected = filterMode == "AUTHOR",
                    onClick = {filterMode = "AUTHOR"},
                    icon = {Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Author")},
                    label = {Text("Autor")}
                )

                NavigationBarItem(
                    selected = filterMode == "FAVORITES",
                    onClick = {filterMode = "FAVORITES"},
                    icon = {Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites")},
                    label = {Text("Favoritos")}
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.Add.route)
            }){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "AñadirLibro"
                    )
            }
        }


    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (filterMode == "AUTHOR") {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    value = authorFilter,
                    onValueChange = {authorFilter = it},
                    label = { Text("Filtrar autor") },
                )
            }
            LazyColumn {
                items(filteredBooks.size) {
                    BookCard(book = filteredBooks[it], onToggleFavorite = {isbn -> onToggleFavorite(isbn)})
                }
            }
        }
    }
}

@Composable
fun BookCard(book: Book, onToggleFavorite: (String) -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {

                Text(book.title)
                Text(book.author)
                Text(book.isbn)

            }
            IconButton(onClick = { onToggleFavorite(book.isbn) }) {
                val favIcon = if (book.isFavorite)
                    Icons.Default.Favorite
                else
                    Icons.Default.FavoriteBorder

                Icon(
                    favIcon,
                    contentDescription = "Favorito"
                )

            }
        }
    }
}

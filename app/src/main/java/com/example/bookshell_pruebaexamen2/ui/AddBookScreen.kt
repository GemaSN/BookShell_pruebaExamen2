package com.example.bookshell_pruebaexamen2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookshell_pruebaexamen2.model.Book
import com.example.bookshell_pruebaexamen2.rutas.Routes


@Composable
fun AddBookScreen(navController: NavHostController, onAddBook: (Book) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var author by rememberSaveable { mutableStateOf("") }
        var isbn by rememberSaveable { mutableStateOf("") }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = title,
            onValueChange = {title=it},
            singleLine = true,
            maxLines = 1,
            label = {Text("Titulo")},
            placeholder = {Text("Romper el hielo")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = author,
            onValueChange = {author=it},
            singleLine = true,
            maxLines = 1,
            label = {Text("Autor")},
            placeholder = {Text("Arturo Perez Reverte")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = isbn,
            onValueChange = {isbn=it},
            singleLine = true,
            maxLines = 1,
            label = {Text("ISBN")},
            placeholder = {Text("13579246")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onAddBook(Book(title,author,isbn))
            navController.navigate(Routes.Library.route)

        }) {
            Text("Add Book")
        }
    }
}

package com.example.bookshell_pruebaexamen2.ui

import android.R
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookshell_pruebaexamen2.rutas.Routes


@Composable
fun LoginScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 24.dp)) {
        Header(modifier = Modifier.align(Alignment.TopStart))
        Body(modifier = Modifier.align(Alignment.Center), navController = navController)
        Footer(modifier = Modifier.align(Alignment.BottomCenter))

    }
}

@Composable
fun Footer(modifier: Modifier) {
    Box(modifier = modifier){
        Text("Version 17.11.2025")
    }
}

@Composable
fun Header(modifier: Modifier) {
    Icon(imageVector = Icons.Default.Close,
        modifier = modifier,
        contentDescription = "IconoCerrar")
}

@Composable
fun Body(modifier: Modifier, navController: NavHostController) {
    var mail by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        Spacer(modifier = Modifier.height(12.dp))
        Email(mail){mail = it}
        Spacer(modifier = Modifier.height(12.dp))
        LoginButton(isValidEmail(mail), navController)
    }
}

@Composable
fun Email(email: String, onValueChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {onValueChange(it)},
        singleLine = true,
        maxLines = 1,
        label = {Text("Email")},
        placeholder = {Text("email@ejemplo.com")}
    )
}

fun isValidEmail(email:String) =
    Patterns.EMAIL_ADDRESS.matcher(email).matches()

@Composable
fun LoginButton(ena:Boolean, navController:NavHostController){
    Button(enabled = ena,
        onClick = { navController.navigate(Routes.Library.route) }) {
        Text("LOGIN")
    }
}


@Composable
fun Logo() {
//   Image(
//        painter = painterResource(R.drawable.logo),
//        contentDescription = "Logo"
//    )
//
}



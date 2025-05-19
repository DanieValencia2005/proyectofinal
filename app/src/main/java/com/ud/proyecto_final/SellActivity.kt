package com.ud.proyecto_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ud.proyecto_final.ui.theme.Proyecto_finalTheme




class SellActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_finalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SellScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SellScreen(modifier: Modifier = Modifier) {
    var bookTitle by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var condition by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Formulario de Venta de Libro", fontSize = 28.sp, modifier = Modifier.padding(bottom = 24.dp))

        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Título del libro") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Editorial") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Año de publicación") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = condition,
            onValueChange = { condition = it },
            label = { Text("Condición del libro") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notas adicionales") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(bottom = 12.dp)
        )
    }
}




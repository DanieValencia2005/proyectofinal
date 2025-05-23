package com.ud.proyecto_final

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ud.proyecto_final.ui.theme.Proyecto_finalTheme
import kotlinx.coroutines.launch

class BuyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_finalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BuyScreen(modifier = Modifier.padding(innerPadding)){
                        finish() // cerramos RegistroActivity
                    }
                }
            }
        }
    }
}

@Composable
fun BuyScreen(modifier: Modifier = Modifier, onBookSaved: () -> Unit) {
    var bookTitle by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context).bookDao() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Formulario de Compra de Libro", fontSize = 28.sp, modifier = Modifier.padding(bottom = 24.dp))

        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Título del libro") },
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Editorial") },
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Año de publicación") },
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Precio (o cantidad)") },
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notas adicionales") },
            modifier = Modifier.fillMaxWidth().height(120.dp).padding(bottom = 12.dp)
        )

        Button(
            onClick = {
                scope.launch {
                    try {
                        db.insertBook(
                            Book(
                                title = bookTitle,
                                author = author,
                                publisher = publisher,
                                year = year.toIntOrNull() ?: 0,
                                price = quantity.toDoubleOrNull() ?: 0.0,
                                condition = "nuevo", // puedes cambiar esto si necesitas otra lógica
                                notes = notes,
                                type = "compra"
                            )
                        )
                        Toast.makeText(context, "Libro guardado correctamente", Toast.LENGTH_SHORT).show()
                        context.startActivity(Intent(context, HomeActivity::class.java))
                        onBookSaved()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GUARDAR")
        }
    }
}

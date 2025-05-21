package com.ud.proyecto_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ud.proyecto_final.ui.theme.Proyecto_finalTheme
import kotlinx.coroutines.launch

@Entity(tableName = "ventas")
data class SellEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val autor: String,
    val editorial: String,
    val año: String,
    val precio: String,
    val condicion: String,
    val notas: String
)

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
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context).sellDao() }

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
        Text(
            text = "Formulario de Venta de Libro",
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Título del libro") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Editorial") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Año de publicación") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = condition,
            onValueChange = { condition = it },
            label = { Text("Condición del libro") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                scope.launch {
                    db.insertarVenta(
                        SellEntity(
                            titulo = bookTitle,
                            autor = author,
                            editorial = publisher,
                            año = year,
                            precio = price,
                            condicion = condition,
                            notas = notes
                        )
                    )
                    // Limpiar campos después de guardar
                    bookTitle = ""
                    author = ""
                    publisher = ""
                    year = ""
                    price = ""
                    condition = ""
                    notes = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar venta")
        }
    }
}





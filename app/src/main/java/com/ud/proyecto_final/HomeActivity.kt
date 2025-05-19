package com.ud.proyecto_final


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ud.proyecto_final.ui.theme.Proyecto_finalTheme
import android.content.Intent
import androidx.compose.ui.platform.LocalContext




class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_finalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Inicio",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                val intent = Intent(context, BuyActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("COMPRAR")
        }

        Button(
            onClick = {
                val intent = Intent(context, SellActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("VENDER")
        }

        Button(
            onClick = { /* Acción inventario */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("INVENTARIO")
        }
    }
}


package com.example.helloformcompose_javiergonzalezmederos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HelloForm()
                }
            }
        }
    }
}

@Composable
fun HelloForm() {
    // Estado del campo de texto (guardado entre rotaciones)
    var name by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }

    // Control del teclado
    val keyboardController = LocalSoftwareKeyboardController.current

    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de texto con contador (máx. 20 caracteres)
        OutlinedTextField(
            value = name,
            onValueChange = {
                if (it.length <= 20) name = it
            },
            label = { Text("Introduce tu nombre") },
            supportingText = { Text("${name.length}/20") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de saludo
        Button(
            onClick = {
                keyboardController?.hide() // Oculta el teclado
                message = if (name.isBlank()) {
                    "Introduce tu nombre"
                } else {
                    "👋 Hola, $name"
                }
            },
            enabled = name.isNotBlank(), // Deshabilitado si está vacío
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Saludar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Texto del resultado
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHelloForm() {
    MaterialTheme {
        HelloForm()
    }
}

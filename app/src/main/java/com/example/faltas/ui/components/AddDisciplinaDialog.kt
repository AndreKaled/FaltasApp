package com.example.faltas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun AddDisciplinaDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var ch by remember { mutableStateOf("") }

    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Card(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
            Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
                Text("Nova Disciplina", style = MaterialTheme.typography.titleLarge)

                androidx.compose.material3.OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome da matéria") }
                )

                androidx.compose.material3.OutlinedTextField(
                    value = ch,
                    onValueChange = { ch = it },
                    label = { Text("Carga Horária (Ex: 60)") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                    )
                )

                Row(modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
                    androidx.compose.material3.TextButton(onClick = onDismiss) { Text("Cancelar") }
                    androidx.compose.material3.TextButton(
                        onClick = {
                            val carga = ch.toIntOrNull() ?: 0
                            if (nome.isNotBlank() && carga > 0) onConfirm(nome, carga)
                        }
                    ) { Text("Salvar") }
                }
            }
        }
    }
}
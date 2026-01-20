package com.example.faltas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.faltas.model.Disciplina

@Composable
fun DisciplinaCard(
    d: Disciplina,
    onAdd: () -> Unit,
    onRemove: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ){
        val cardCor = when {
            d.estaReprovado -> MaterialTheme.colorScheme.errorContainer
            d.estaEmPerigo -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.surfaceVariant
        }

        Card( modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = cardCor)
        ){
            Row(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(modifier = Modifier.weight(1f)) {
                    Text(d.nome, style = MaterialTheme.typography.titleMedium)
                    Text("Carga Horária: ${d.CH}h")

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = "Faltas: ${d.faltas} / ${d.limiteFaltas}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (d.estaReprovado) Color.Red else Color.Unspecified
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onRemove, enabled = d.faltas > 0) {
                        Icon(Icons.Default.KeyboardArrowDown, "Remover falta")
                    }

                    Text(d.faltas.toString(), modifier = Modifier.padding(horizontal = 8.dp))

                    IconButton(onClick = onAdd) {
                        Icon(Icons.Default.KeyboardArrowUp, "Adicionar falta")
                    }
                }
            }
        }
    }
}
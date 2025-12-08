package com.example.faltas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaDisciplinas()
        }
    }
}

data class Disciplina(
    val id: Int,
    val nome: String,
    val CH: Int,
    val faltas: Int
){
    val limiteFaltas: Int
        get() = (CH * 0.25).toInt()
    val estaEmPerigo: Boolean
        get() = faltas >= (limiteFaltas - 4)
    val estaReprovado: Boolean
        get() = faltas > limiteFaltas
}

@Composable
fun DisciplinaCard(d: Disciplina, onAdd: () -> Unit, onRemove: () -> Unit){
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDisciplinas() {
    // Estado da lista
    val disciplinas = remember {
        mutableStateListOf(
            Disciplina(1, "Cálculo 1", 60, 0),
            Disciplina(2, "Física 1", 80, 2),
            Disciplina(3, "Algoritmos", 72, 1),
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Controle de Faltas") })
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(disciplinas) { index, disciplina ->
                DisciplinaCard(
                    d = disciplina,
                    onAdd = {
                        disciplinas[index] = disciplina.copy(faltas = disciplina.faltas + 1)
                    },
                    onRemove = {
                        if (disciplina.faltas > 0) {
                            disciplinas[index] = disciplina.copy(faltas = disciplina.faltas - 1)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTelaDisciplinas() {
    TelaDisciplinas()
}
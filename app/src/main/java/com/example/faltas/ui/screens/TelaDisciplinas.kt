package com.example.faltas.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.faltas.model.Disciplina
import com.example.faltas.ui.components.DisciplinaCard

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
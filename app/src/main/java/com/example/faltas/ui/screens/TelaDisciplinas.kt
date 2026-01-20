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
import com.example.faltas.repo.DisciplinaRepository
import com.example.faltas.ui.components.DisciplinaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDisciplinas() {
    val repository = remember { DisciplinaRepository() }
    val disciplinas = repository.getDisciplinas();

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
                        repository.adicionaFalta(disciplina.id)
                    },
                    onRemove = {
                        repository.removeFalta(disciplina.id)
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
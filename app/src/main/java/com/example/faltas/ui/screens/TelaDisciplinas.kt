package com.example.faltas.ui.screens

import DisciplinaRepository
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.faltas.db.AppDatabase
import com.example.faltas.model.Disciplina
import com.example.faltas.ui.components.DisciplinaCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDisciplinas() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val db = remember { Room.databaseBuilder(context,
        AppDatabase::class.java, "faltas.db").build()
    }
    val repository = remember {
        DisciplinaRepository(db.disciplinaDao())
    }
    val disciplinas by repository.allDisciplinas.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Controle de Faltas") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        val nova = Disciplina(0, "Nova Disciplina ${disciplinas.size + 1}", 60, 0)
                        repository.insert(nova)
                    }
                }
            ) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(disciplinas) { index, disciplina ->
                DisciplinaCard(
                    d = disciplina,
                    onAdd = { scope.launch{ repository.atualizarFaltas(disciplina.id, true) } },
                    onRemove = { scope.launch { repository.atualizarFaltas(disciplina.id, false) } }
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
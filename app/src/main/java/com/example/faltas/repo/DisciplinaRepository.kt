package com.example.faltas.repo

import androidx.compose.runtime.mutableStateListOf
import com.example.faltas.model.Disciplina

class DisciplinaRepository{
    private val disciplinas = mutableStateListOf(
        Disciplina(1, "Calculo 1", 60, 0),
        Disciplina(2,"Calculo 2", 80, 2),
        Disciplina(3,"Calculo 3", 72, 1)
        )

    fun getDisciplinas() = disciplinas

    fun adicionaFalta(id: Int){
        val index = disciplinas.indexOfFirst {it.id == id}
        if(index != -1) disciplinas[index] = disciplinas[index].copy(faltas = disciplinas[index].faltas + 1)
    }

    fun removeFalta(id: Int){
        val index = disciplinas.indexOfFirst {it.id == id}
        if(index != -1) disciplinas[index] = disciplinas[index].copy(faltas = disciplinas[index].faltas - 1)
    }
}
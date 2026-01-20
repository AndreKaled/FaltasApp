package com.example.faltas.model

import com.example.faltas.db.DisciplinaEntity

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

//extensoes pra converter entre modelo e entity
fun DisciplinaEntity.toModel() =  Disciplina(id, nome, CH, faltas)
fun Disciplina.toEntity() = DisciplinaEntity(id, nome, CH, faltas)
package com.example.faltas.model

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
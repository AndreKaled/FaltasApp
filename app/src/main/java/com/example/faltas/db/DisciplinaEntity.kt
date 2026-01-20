package com.example.faltas.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disciplinas")
data class DisciplinaEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0, // 0 indica que o room deve gerar o ID
    val nome: String,
    val CH: Int,
    val faltas: Int
)
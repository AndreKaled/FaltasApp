package com.example.faltas.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DisciplinaDao {
    @Query("SELECT * FROM disciplinas")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<DisciplinaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(disciplina: DisciplinaEntity)

    @Update
    suspend fun update(disciplina: DisciplinaEntity)
}
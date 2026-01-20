package com.example.faltas.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DisciplinaEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun disciplinaDao(): DisciplinaDao

}
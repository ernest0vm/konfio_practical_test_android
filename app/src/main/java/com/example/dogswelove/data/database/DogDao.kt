package com.example.dogswelove.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {

    @Query("SELECT * FROM DogEntity")
    suspend fun getAllDogs(): List<DogEntity>

    @Insert
    suspend fun insertDogs(dogs: List<DogEntity>)
}


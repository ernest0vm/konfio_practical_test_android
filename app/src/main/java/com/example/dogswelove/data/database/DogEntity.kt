package com.example.dogswelove.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DogEntity")
data class DogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dogName: String,
    val description: String,
    val age: Int,
    val image: String
)
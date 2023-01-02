package com.example.core_database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marvel_table")
data class MarvelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)

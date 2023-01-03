package com.example.core_model.marvel.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marvel_table")
data class MarvelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "comics") val comics: Int,
    @ColumnInfo(name = "series") val series: Int,
    @ColumnInfo(name = "stories") val stories: Int,
    @ColumnInfo(name = "events") val events: Int,
    @ColumnInfo(name = "urls") val urls: Int
)

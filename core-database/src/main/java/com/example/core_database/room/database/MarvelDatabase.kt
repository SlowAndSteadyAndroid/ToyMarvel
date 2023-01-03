package com.example.core_database.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_database.room.dao.MarvelDao
import com.example.core_model.marvel.entity.MarvelEntity


/**
 * @see MarvelEntity
 */
@Database(entities = [MarvelEntity::class], version = 2)
abstract class MarvelDatabase : RoomDatabase(){
    abstract fun marvelDao() : MarvelDao
}
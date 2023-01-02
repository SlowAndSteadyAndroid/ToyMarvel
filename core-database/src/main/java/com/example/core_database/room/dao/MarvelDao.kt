package com.example.core_database.room.dao

import androidx.room.*
import com.example.core_database.room.entity.MarvelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(item: MarvelEntity): Long

    @Delete
    suspend fun deleteBookmark(item: MarvelEntity): Int

    @Query("SELECT * FROM marvel_table")
    fun getAllBookmark(): Flow<List<MarvelEntity>>

}
package ru.vtinch.scramblegame.load.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWord(id: Int) : WordCache

    @Insert//(onConflict = REPLACE)
    suspend fun addWords(list: List<WordCache>)

    @Query("DELETE FROM words")
    fun clearAll()
}
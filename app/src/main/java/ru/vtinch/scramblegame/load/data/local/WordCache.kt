package ru.vtinch.scramblegame.load.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordCache(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "word")
    val word: String,
) {
}
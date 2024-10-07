package ru.vtinch.scramblegame.load.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordCache::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract fun dao(): WordDao
}
package ru.vtinch.scramblegame.load.data.local

import android.content.Context
import androidx.room.Room

interface CacheModule {

    fun dao(): WordDao

    class Base(applicationContext: Context): CacheModule{

        private val database = Room.databaseBuilder(
            applicationContext,
            WordDatabase::class.java,
            "app-db"
        ).build()

        override fun dao(): WordDao = database.dao()
    }
}
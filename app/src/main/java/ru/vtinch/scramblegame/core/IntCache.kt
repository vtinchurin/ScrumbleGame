package ru.vtinch.scramblegame.core

import android.content.SharedPreferences

interface IntCache {

    interface Save {
        fun save(index: Int)
    }

    interface Restore {
        fun restore(): Int
    }

    interface Mutable : Save, Restore

    class Base(
        private val key: String,
        private val sharedPrefs: SharedPreferences
    ) : Mutable {
        override fun save(index: Int) {
            sharedPrefs.edit().putInt(key, index).apply()
        }

        override fun restore(): Int {
            return sharedPrefs.getInt(key, 0)
        }
    }
}
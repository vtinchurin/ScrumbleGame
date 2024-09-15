package ru.vtinch.scramblegame.core

import android.content.SharedPreferences


class IntCache(
    private val key: String,
    private val sharedPrefs: SharedPreferences,
    private val defaultValue: Int,
) : Cache.Mutable<Int> {
    override fun save(value: Int) {
        sharedPrefs.edit().putInt(key, value).apply()
    }

    override fun restore(): Int {
        return sharedPrefs.getInt(key, defaultValue)
    }
}

package ru.vtinch.scramblegame.core

import android.content.SharedPreferences

class StringCache(
    private val key: String,
    private val sharedPrefs: SharedPreferences,
    private val defaultValue: String,
) : Cache.Mutable<String> {
    override fun save(value: String) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    override fun restore(): String {
        return sharedPrefs.getString(key, defaultValue) ?: defaultValue
    }
}
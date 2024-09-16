package ru.vtinch.scramblegame.core.cache

import android.content.SharedPreferences

class StringSetCache(
    private val key: String,
    private val sharedPrefs: SharedPreferences,
    private val defaultValue: Set<String>,
) : Cache.Mutable<Set<String>> {

    override fun save(value: Set<String>) {
        sharedPrefs.edit().putStringSet(key, value).apply()
    }

    override fun restore(): Set<String> {
        return sharedPrefs.getStringSet(key, defaultValue) ?: defaultValue
    }
}
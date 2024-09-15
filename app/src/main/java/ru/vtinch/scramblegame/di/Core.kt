package ru.vtinch.scramblegame.di

import android.content.SharedPreferences
import ru.vtinch.scramblegame.core.IntCache
import ru.vtinch.scramblegame.core.StringSetCache
import ru.vtinch.scramblegame.game.Strategy

class Core(
    sharedPreferences: SharedPreferences,
    val clearViewModel: ClearViewModel,
) {
    val index = IntCache("index", sharedPreferences, defaultValue = 0)
    val corrects = IntCache("corrects", sharedPreferences, defaultValue = 0)
    val skipped = IntCache("skipped", sharedPreferences, defaultValue = 0)
    val incorrect = IntCache("incorrect", sharedPreferences, defaultValue = 0)
    val stringsCache = StringSetCache("words", sharedPreferences, defaultValue = emptySet())
    val strategy = Strategy.Test()
}
package ru.vtinch.scramblegame.di

import android.content.SharedPreferences
import ru.vtinch.scramblegame.core.IntCache
import ru.vtinch.scramblegame.game.Strategy

class Core(
    sharedPreferences: SharedPreferences,
    val clearViewModel: ClearViewModel,
) {
    val index = IntCache.Base("index", sharedPreferences)
    val corrects = IntCache.Base("corrects", sharedPreferences)
    val skipped = IntCache.Base("skipped", sharedPreferences)
    val incorrect = IntCache.Base("incorrect", sharedPreferences)
    val strategy = Strategy.Test()
}
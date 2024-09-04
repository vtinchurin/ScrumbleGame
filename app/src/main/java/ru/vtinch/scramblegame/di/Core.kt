package ru.vtinch.scramblegame.di

import android.content.Context
import android.content.SharedPreferences
import ru.vtinch.scramblegame.IntCache
import ru.vtinch.scramblegame.core.ClearViewModel
import ru.vtinch.scramblegame.game.Strategy

class Core(
    context: Context,
    val clearViewModel: ClearViewModel,
) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("myApp",Context.MODE_PRIVATE)
    val index = IntCache.Base("index", sharedPreferences)
    val corrects = IntCache.Base("corrects", sharedPreferences)
    val skipped = IntCache.Base("skipped", sharedPreferences)
    val incorrect = IntCache.Base("incorrect", sharedPreferences)
    val strategy = Strategy.Game()
}
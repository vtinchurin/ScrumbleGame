package ru.vtinch.scramblegame.di

import android.content.Context
import ru.vtinch.scramblegame.core.cache.IntCache
import ru.vtinch.scramblegame.core.cache.StringCache
import ru.vtinch.scramblegame.load.data.local.CacheModule

class Core(
    context: Context,
    val clearViewModel: ClearViewModel,
) {
    private val sharedPreferences = context.getSharedPreferences("myApp", Context.MODE_PRIVATE)
    val cacheModule = CacheModule.Base(context)
    val isTest = false
    val wordsCount = 10
    val indexCache = IntCache("index", sharedPreferences, defaultValue = -1)
    val corrects = IntCache("corrects", sharedPreferences, defaultValue = 0)
    val skipped = IntCache("skipped", sharedPreferences, defaultValue = 0)
    val incorrect = IntCache("incorrect", sharedPreferences, defaultValue = 0)
    val question = StringCache("question", sharedPreferences, defaultValue = "")
}
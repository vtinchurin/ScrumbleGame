package ru.vtinch.scramblegame.di

import android.content.Context
import ru.vtinch.scramblegame.core.cache.IntCache
import ru.vtinch.scramblegame.load.data.local.CacheModule

class Core(
    context: Context,
    val clearViewModel: ClearViewModel,
) {
    private val sharedPreferences = context.getSharedPreferences("myApp", Context.MODE_PRIVATE)
    val cacheModule = CacheModule.Base(context)
    val isTest = false
    val index = IntCache("index", sharedPreferences, defaultValue = 0)
    val corrects = IntCache("corrects", sharedPreferences, defaultValue = 0)
    val skipped = IntCache("skipped", sharedPreferences, defaultValue = 0)
    val incorrect = IntCache("incorrect", sharedPreferences, defaultValue = 0)
}
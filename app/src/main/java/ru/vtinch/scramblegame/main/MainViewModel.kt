package ru.vtinch.scramblegame.main

import android.util.Log
import ru.vtinch.scramblegame.core.Screen
import ru.vtinch.scramblegame.core.cache.IntCache
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.game.GameScreen
import ru.vtinch.scramblegame.load.LoadScreen
import ru.vtinch.scramblegame.stats.StatsScreen

class MainViewModel(
    private val index: IntCache,
) : MyViewModel {
    fun init(isFirsRin: Boolean): Screen {
        return if (isFirsRin){
            if (index.restore() == -1) {
                Log.d("tvn", "index = -1")
                index.save(0)
                LoadScreen
            } else if (index.restore() == 10) {
                Log.d("tvn", "index = 10")
                StatsScreen
            } else{
                Log.d("tvn", "index 0..9")
                GameScreen
            }
        } else Screen.Empty
    }
}
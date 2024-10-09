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

    private var processDeath = true

    fun init(isFirsRin: Boolean): Screen {
        return if (isFirsRin){
            processDeath = false
            Log.d("tvn", "First run")
            if (index.restore() == -1) {
                index.save(0)
                LoadScreen
            } else if (index.restore() == 10) {
                StatsScreen
            } else{
                GameScreen
            }
        } else {
            if (processDeath) {
                Log.d("tvn", "Process Death")
                processDeath = false
            } else {
                Log.d("tvn", "Activity Death")
            }
            Screen.Empty
        }
    }
}
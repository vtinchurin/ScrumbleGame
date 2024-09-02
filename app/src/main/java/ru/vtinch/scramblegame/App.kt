package ru.vtinch.scramblegame

import android.app.Application
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.Strategy
import ru.vtinch.scramblegame.stats.StatsViewModel

class App : Application() {

    lateinit var gameViewModel: GameViewModel
    lateinit var statsViewModel: StatsViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPrefs = getSharedPreferences("UnscrambleApp", MODE_PRIVATE)
        val gameRepository = GameRepository.Base(
            IntCache.Base(key = "index", sharedPrefs),
            IntCache.Base(key = "corrects",sharedPrefs),
            IntCache.Base(key = "skipped",sharedPrefs),
            IntCache.Base(key = "incorrect",sharedPrefs),
            strategy = Strategy.Test()
        )
        gameViewModel = GameViewModel(gameRepository = gameRepository, liveDataWrapper = UiStateLiveDataWrapper.Base())

        statsViewModel = StatsViewModel(gameRepository = gameRepository)
    }
}
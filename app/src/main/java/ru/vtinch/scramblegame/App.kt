package ru.vtinch.scramblegame

import android.app.Application
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.Strategy
import ru.vtinch.scramblegame.game.UiStateLiveDataWrapper
import ru.vtinch.scramblegame.stats.StatsRepository
import ru.vtinch.scramblegame.stats.StatsUiStateLiveDataWrapper
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
        val statsRepository = StatsRepository.Base(
            IntCache.Base(key = "corrects",sharedPrefs),
            IntCache.Base(key = "skipped",sharedPrefs),
            IntCache.Base(key = "incorrect",sharedPrefs),
        )
        gameViewModel = GameViewModel(gameRepository = gameRepository, liveDataWrapper = UiStateLiveDataWrapper.Base())

        statsViewModel = StatsViewModel(gameRepository = statsRepository, liveDataWrapper = StatsUiStateLiveDataWrapper.Base())
    }
}
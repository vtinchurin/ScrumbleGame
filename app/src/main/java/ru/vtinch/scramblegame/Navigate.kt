package ru.vtinch.scramblegame

import ru.vtinch.scramblegame.game.GameScreen
import ru.vtinch.scramblegame.stats.StatsScreen




    interface NavigateToGame {
        fun navigateToGame()
    }

    interface NavigateToStats {
        fun navigateToStats()
    }

    interface Navigate : NavigateToGame, NavigateToStats {

        fun navigate(screen: Screen)

        override fun navigateToGame() = navigate(GameScreen)

        override fun navigateToStats() = navigate(StatsScreen)

    }




package ru.vtinch.scramblegame.core

import ru.vtinch.scramblegame.game.GameScreen
import ru.vtinch.scramblegame.game.NavigateToGame
import ru.vtinch.scramblegame.load.LoadScreen
import ru.vtinch.scramblegame.load.NavigateToLoad
import ru.vtinch.scramblegame.stats.NavigateToStats
import ru.vtinch.scramblegame.stats.StatsScreen


interface Navigation : NavigateToGame, NavigateToStats, NavigateToLoad {

    fun navigate(screen: Screen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToStats() = navigate(StatsScreen)

    override fun navigateToLoad() = navigate(LoadScreen)
}

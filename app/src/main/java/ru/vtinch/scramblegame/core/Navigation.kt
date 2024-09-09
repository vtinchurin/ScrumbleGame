package ru.vtinch.scramblegame.core

import ru.vtinch.scramblegame.game.GameScreen
import ru.vtinch.scramblegame.game.NavigateToGame
import ru.vtinch.scramblegame.stats.NavigateToStats
import ru.vtinch.scramblegame.stats.StatsScreen


interface Navigation : NavigateToGame, NavigateToStats {

    fun navigate(screen: Screen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToStats() = navigate(StatsScreen)

}

package ru.vtinch.scramblegame.stats.di

import ru.vtinch.scramblegame.di.ProvideViewModel
import ru.vtinch.scramblegame.di.AbstractProvideViewModel
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.stats.StatsViewModel

class ProvideStatsViewModel(
    private val core: Core,
    nextChain: ProvideViewModel,
): AbstractProvideViewModel(
    core,nextChain, StatsViewModel::class.java
) {
    override fun module(): Module<*> = StatsModule(core)
}
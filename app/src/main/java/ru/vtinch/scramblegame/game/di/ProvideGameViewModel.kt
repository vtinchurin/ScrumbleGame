package ru.vtinch.scramblegame.game.di

import ru.vtinch.scramblegame.di.AbstractProvideViewModel
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.di.ProvideViewModel
import ru.vtinch.scramblegame.game.GameViewModel

class ProvideGameViewModel(
    private val core:Core,
    nextChain: ProvideViewModel,
):AbstractProvideViewModel(
    nextChain,GameViewModel::class.java
) {
    override fun module(): Module<*> = GameModule(core)
}
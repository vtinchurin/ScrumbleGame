package ru.vtinch.scramblegame.game.di

import ru.vtinch.scramblegame.di.AbstractProvideViewModel
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.di.ProvideViewModel
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.load.di.LoadModule
import ru.vtinch.scramblegame.load.di.TestLoadModule

class ProvideGameViewModel(
    private val core:Core,
    nextChain: ProvideViewModel,
):AbstractProvideViewModel(
    nextChain,GameViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> {
        return if (core.isTest)
            TestGameModule(core)
        else
            GameModule(core)
    }

}
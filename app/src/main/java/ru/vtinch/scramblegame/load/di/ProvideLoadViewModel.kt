package ru.vtinch.scramblegame.load.di

import ru.vtinch.scramblegame.di.AbstractProvideViewModel
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.ProvideViewModel
import ru.vtinch.scramblegame.load.LoadViewModel

class ProvideLoadViewModel(
    private val core: Core,
    nextChain: ProvideViewModel,
) : AbstractProvideViewModel(
    nextChain,
    LoadViewModel::class.java
) {
    override fun module() = LoadModule(core)
}
package ru.vtinch.scramblegame.main.di

import ru.vtinch.scramblegame.di.AbstractProvideViewModel
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.di.ProvideViewModel
import ru.vtinch.scramblegame.main.MainViewModel

class ProvideMainViewModel(
    private val core: Core,
    nextChain: ProvideViewModel
) : AbstractProvideViewModel(nextChain, MainViewModel::class.java){
    override fun module(): Module<out MyViewModel> = MainModule(core)
}
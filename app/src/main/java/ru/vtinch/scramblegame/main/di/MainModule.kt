package ru.vtinch.scramblegame.main.di

import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.main.MainViewModel

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel(): MainViewModel {
        return MainViewModel(
            core.indexCache
        )
    }
}
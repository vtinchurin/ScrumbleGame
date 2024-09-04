package ru.vtinch.scramblegame.core

import androidx.lifecycle.ViewModel
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.game.di.ProvideGameViewModel
import ru.vtinch.scramblegame.stats.di.ProvideStatsViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(
        core: Core,
    ) : ProvideViewModel {
        private var chain: ProvideViewModel = Error

        init {
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideStatsViewModel(core, chain)

        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return chain.viewModel(viewModelClass)
        }
    }


    object Error : ProvideViewModel {
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            throw IllegalStateException("error")
        }
    }
}
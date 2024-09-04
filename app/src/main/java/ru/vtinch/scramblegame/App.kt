package ru.vtinch.scramblegame

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.vtinch.scramblegame.core.ClearViewModel
import ru.vtinch.scramblegame.core.ProvideViewModel
import ru.vtinch.scramblegame.core.ViewModelFactory
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.Strategy
import ru.vtinch.scramblegame.game.UiStateLiveDataWrapper
import ru.vtinch.scramblegame.stats.StatsRepository
import ru.vtinch.scramblegame.stats.StatsUiStateLiveDataWrapper
import ru.vtinch.scramblegame.stats.StatsViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory
    //todo DI

    private val clear : ClearViewModel = object : ClearViewModel {
        override fun clear(viewModelClass: Class<out ViewModel>) {
            factory.clear(viewModelClass)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val core = Core(this,clear)
        factory = ViewModelFactory.Base(ProvideViewModel.Base(core = core))
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }
}
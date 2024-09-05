package ru.vtinch.scramblegame

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.ProvideViewModel
import ru.vtinch.scramblegame.di.ViewModelFactory
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.MyViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory

    private val clear : ClearViewModel = object : ClearViewModel {
        override fun clear(viewModelClass: Class<out MyViewModel>) {
            factory.clear(viewModelClass)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE)
        val core = Core(sharedPreferences,clear)
        factory = ViewModelFactory.Base(ProvideViewModel.Base(core = core))
    }

    override fun <T : MyViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }
}
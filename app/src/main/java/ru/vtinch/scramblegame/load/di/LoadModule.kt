package ru.vtinch.scramblegame.load.di

import com.google.gson.Gson
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.load.LoadRepository
import ru.vtinch.scramblegame.load.LoadViewModel
import ru.vtinch.scramblegame.load.UiObservable

class LoadModule(
    private val core: Core
) : Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {
        return LoadViewModel(
            repository = LoadRepository.Base(
                Gson(),
                stringCache = core.stringsCache,
            ),
            observable = UiObservable.Base()
        )
    }
}
package ru.vtinch.scramblegame.load.di

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.load.LoadRepository
import ru.vtinch.scramblegame.load.LoadViewModel
import ru.vtinch.scramblegame.load.data.remote.HandleError

class TestLoadModule(private val core: Core): Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {
        return LoadViewModel(
            repository = LoadRepository.Test(),
            runAsync = RunAsync.Base(),
            clearViewModel = core.clearViewModel,
            observable = UiObservable.Base(),
            handleError = HandleError.Ui()
        )
    }
}
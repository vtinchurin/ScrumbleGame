package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.load.data.remote.HandleError

class LoadViewModel(
    private val repository: LoadRepository,
    private val runAsync: RunAsync,
    private val clearViewModel: ClearViewModel,
    observable: UiObservable<LoadUiState>,
    private val handleError: HandleError.Ui
) : MyViewModel.Abstract<LoadUiState>(observable) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.updateUi(LoadUiState.Progress)
            runAsync.handleAsync(
                coroutineScope = viewModelScope, {
                    try {
                        repository.load()
                        clearViewModel.clear(this::class.java)
                        LoadUiState.Success
                    } catch (e: Exception) {
                        val res = handleError.handleError(e)
                        LoadUiState.Error(res)
                    }
                }) {
                observable.updateUi(it)
            }
        }
    }

}

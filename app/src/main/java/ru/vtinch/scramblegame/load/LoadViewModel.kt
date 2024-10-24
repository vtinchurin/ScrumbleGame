package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.load.data.remote.HandleError

class LoadViewModel(
    private val repository: LoadRepository,
    private val clearViewModel: ClearViewModel,
    private val handleError: HandleError<Int>,
    runAsync: RunAsync,
    observable: UiObservable<LoadUiState>,
) : MyViewModel.ObservableAsync<LoadUiState>(observable, runAsync) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.updateUi(LoadUiState.Progress)
            handleAsync {
                try {
                    repository.load()
                    clearViewModel.clear(this::class.java)
                    LoadUiState.Success
                } catch (e: Exception) {
                    val res = handleError.handleError(e)
                    LoadUiState.Error(res)
                }
            }
        }
    }
}
package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    observable: UiObservable<LoadUiState>,
    private val runAsync: RunAsync,
    private val clearViewModel: ClearViewModel,
) : MyViewModel.Abstract<LoadUiState>(observable) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.updateUi(LoadUiState.Progress)
            runAsync.handleAsync(
                coroutineScope = viewModelScope, {
                    val result = repository.load()
                    if (result == LoadResult.Success) {
                        clearViewModel.clear(this::class.java)
                        LoadUiState.Success
                    } else LoadUiState.Error
                }) {
                observable.updateUi(it)
            }
        }
    }

}

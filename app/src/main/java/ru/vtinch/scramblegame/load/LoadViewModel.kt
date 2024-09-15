package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.di.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable,
) : MyViewModel.AbstractViewModel() {

    fun load(isFirstRun: Boolean = false) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            handleAsync({
                val result = repository.load()
                if (result == LoadResult.Success) LoadUiState.Navigate
                else LoadUiState.Error
            }) {
                observable.postUiState(it)
            }
        }
    }


    fun startUpdate(observer: (LoadUiState) -> Unit) {
        observable.register(observer)
    }

    fun stopUpdate() {
        observable.unregister()
    }

}

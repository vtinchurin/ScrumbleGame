package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.di.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable,
) : MyViewModel {

    fun load(isFirstRun: Boolean = false) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            repository.load {
                when (it) {
                    is LoadResult.Success -> observable.postUiState(LoadUiState.Navigate)
                    is LoadResult.Error -> observable.postUiState(LoadUiState.Error)
                }
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

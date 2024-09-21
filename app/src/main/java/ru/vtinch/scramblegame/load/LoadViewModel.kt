package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.customLiveData.UiObservable
import ru.vtinch.scramblegame.core.customLiveData.UiObserver
import ru.vtinch.scramblegame.di.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable<LoadUiState>,
    private val runAsync: RunAsync
) : MyViewModel {

    fun load(isFirstRun: Boolean = true){
        if (isFirstRun) {
         observable.updateUi(LoadUiState.Progress)
            runAsync.handleAsync({
                val result = repository.load()
                if (result == LoadResult.Success) {
                    LoadUiState.Success
                }
                else LoadUiState.Error
            }){
                observable.updateUi(it)
            }
        }
    }




    fun startUpdate(observer: UiObserver<LoadUiState>) {
        observable.update(observer)
    }

    fun stopUpdate() {
        observable.update()
    }

}

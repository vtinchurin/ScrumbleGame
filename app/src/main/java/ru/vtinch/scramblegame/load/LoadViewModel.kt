package ru.vtinch.scramblegame.load

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.customLiveData.UiObservable
import ru.vtinch.scramblegame.core.customLiveData.UiObserver
import ru.vtinch.scramblegame.di.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable<LoadUiState>,
    private val runAsync: RunAsync
) : MyViewModel {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load(isFirstRun: Boolean = true){
        if (isFirstRun) {
         observable.updateUi(LoadUiState.Progress)
            runAsync.handleAsync(
                coroutineScope = viewModelScope, {
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

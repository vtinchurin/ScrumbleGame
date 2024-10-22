package ru.vtinch.scramblegame.stats

import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val clearViewModel: ClearViewModel,
    observable: UiObservable<StatsUiState>,
) : MyViewModel.Observable<StatsUiState>(observable) {

    fun update(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            val (a, b, c) = statsRepository.getScore()
            observable.updateUi(StatsUiState.Default(a, b, c))
        } else observable.updateUi(StatsUiState.Empty)
    }

    fun newGame() {
        statsRepository.clear()
        clearViewModel.clear(this::class.java)
        observable.updateUi(StatsUiState.Navigate)
    }
}

package ru.vtinch.scramblegame.stats

import androidx.lifecycle.LiveData
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val liveDataWrapper: StatsUiStateLiveDataWrapper.Mutable,
    private val clearViewModel: ClearViewModel,
) : MyViewModel, StatsUiStateLiveDataWrapper.Read {

    init {
        //Log.d("vm","create Stats VM")
    }


    fun update(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            val (a, b, c) = statsRepository.getScore()
            liveDataWrapper.update(StatsUiState.Default(a, b, c))
        } else liveDataWrapper.update(StatsUiState.Empty)
    }



    fun newGame(){
        statsRepository.clear()
        clearViewModel.clear(StatsViewModel::class.java)
        liveDataWrapper.update(StatsUiState.Navigate)
    }

    override fun liveData(): LiveData<StatsUiState> {
        return liveDataWrapper.liveData()
    }

}

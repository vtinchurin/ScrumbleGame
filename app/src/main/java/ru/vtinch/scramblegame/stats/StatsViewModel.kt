package ru.vtinch.scramblegame.stats

import androidx.lifecycle.LiveData
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val liveDataWrapper: StatsUiStateLiveDataWrapper.Mutable,
    private val clearViewModel: ClearViewModel,
): MyViewModel,StatsUiStateLiveDataWrapper.Read {

    init {
        //Log.d("vm","create Stats VM")
    }


    fun update(){
        val (a,b,c) = statsRepository.getScore()
        liveDataWrapper.update(StatsUiState.Default(a,b,c))
        statsRepository.clear()
    }



    fun newGame(){
        clearViewModel.clear(StatsViewModel::class.java)
        liveDataWrapper.update(StatsUiState.Navigate)
    }

    override fun liveData(): LiveData<StatsUiState> {
        return liveDataWrapper.liveData()
    }

}

package ru.vtinch.scramblegame.stats

import androidx.lifecycle.LiveData

class StatsViewModel(private val gameRepository: StatsRepository,
    private val liveDataWrapper: StatsUiStateLiveDataWrapper.Mutable):StatsUiStateLiveDataWrapper.Read {


    fun update(){
        val (a,b,c) = gameRepository.getScore()
        liveDataWrapper.update(StatsUiState.Default(a,b,c))
    }


    fun newGame(){
        gameRepository.clear()
        liveDataWrapper.update(StatsUiState.Start)
    }

    override fun liveData(): LiveData<StatsUiState> {
        return liveDataWrapper.liveData()
    }

}

package ru.vtinch.scramblegame.stats

import ru.vtinch.scramblegame.GameRepository

class StatsViewModel(private val gameRepository: GameRepository) {

    fun update():Triple<Int,Int,Int>{
        val (a,b,c) = gameRepository.getScore()
        return Triple(a,b,c)
    }


    fun newGame(){
        gameRepository.newGame()
    }

}

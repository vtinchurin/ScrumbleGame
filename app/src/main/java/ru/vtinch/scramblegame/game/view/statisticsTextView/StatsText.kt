package ru.vtinch.scramblegame.game.view.statisticsTextView

import ru.vtinch.scramblegame.game.view.questionTextView.TextUiState

interface StatsText {
    fun update(state: StatsUiState)
    fun setData(data :Triple<Int,Int,Int>)
}
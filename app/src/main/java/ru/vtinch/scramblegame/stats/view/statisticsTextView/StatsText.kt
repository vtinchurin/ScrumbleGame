package ru.vtinch.scramblegame.stats.view.statisticsTextView

interface StatsText {
    fun update(state: StatsTextUiState)
    fun setData(data :Triple<Int,Int,Int>)
}
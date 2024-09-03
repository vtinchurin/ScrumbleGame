package ru.vtinch.scramblegame.stats.view.statisticsTextView

import java.io.Serializable

interface StatsTextUiState :Serializable {

    fun update(state: StatsText)

    class Base(
        private val correct:Int,
        private val incorrect:Int,
        private val skipped:Int,
        ): StatsTextUiState {
        override fun update(state: StatsText) {
            state.setData(Triple(correct,incorrect,skipped))
        }
    }
}
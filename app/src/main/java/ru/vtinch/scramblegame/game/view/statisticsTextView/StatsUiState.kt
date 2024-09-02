package ru.vtinch.scramblegame.game.view.statisticsTextView

import java.io.Serializable

interface StatsUiState :Serializable {

    fun update(state: StatsText)

    class Base(
        private val correct:Int,
        private val incorrect:Int,
        private val skipped:Int,
        ):StatsUiState {
        override fun update(state: StatsText) {
            state.setData(Triple(correct,incorrect,skipped))
        }
    }
}
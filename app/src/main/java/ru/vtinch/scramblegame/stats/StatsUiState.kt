package ru.vtinch.scramblegame.stats

import android.widget.Button
import ru.vtinch.scramblegame.NavigateToGame
import ru.vtinch.scramblegame.stats.view.statisticsTextView.StatsTextView
import ru.vtinch.scramblegame.stats.view.statisticsTextView.StatsTextUiState

interface StatsUiState {

    fun show(
        text: StatsTextView,
        button: Button,
    ) = Unit

    fun navigate(navigate: NavigateToGame) = Unit

    class Default(
        private val correct: Int,
        private val incorrect: Int,
        private val skipped: Int) :
        StatsUiState {
        override fun show(text: StatsTextView, button: Button) {
            text.update(
                StatsTextUiState.Base(
                    correct = correct,
                    incorrect = incorrect,
                    skipped = skipped,
                )
            )
        }
    }

    object Start : StatsUiState {
        override fun navigate(navigate: NavigateToGame) {
            navigate.navigateToGame()
        }
    }
}
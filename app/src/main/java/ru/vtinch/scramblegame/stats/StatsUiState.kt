package ru.vtinch.scramblegame.stats

import android.widget.Button
import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.stats.view.statisticsTextView.StatsTextUiState
import ru.vtinch.scramblegame.stats.view.statisticsTextView.StatsTextView

interface StatsUiState {

    fun show(
        text: StatsTextView,
        button: Button,
    ) = Unit

    fun navigate(navigate: Navigation) = Unit

    data class Default(
        private val correct: Int,
        private val incorrect: Int,
        private val skipped: Int,
    ) :StatsUiState {
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

    object Empty:StatsUiState

    object Navigate : StatsUiState {
        override fun navigate(navigate: Navigation) {
            navigate.navigateToLoad()
        }
    }
}
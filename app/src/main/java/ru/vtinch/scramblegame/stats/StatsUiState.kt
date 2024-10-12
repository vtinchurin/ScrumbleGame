package ru.vtinch.scramblegame.stats

import android.widget.Button
import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.stats.view.textView.StatsText
import ru.vtinch.scramblegame.stats.view.textView.StatsTextState

interface StatsUiState {

    fun show(
        text: StatsText,
        button: Button,
    ) = Unit

    fun navigate(navigate: Navigation) = Unit

    data class Default(
        private val correct: Int,
        private val incorrect: Int,
        private val skipped: Int,
    ) : StatsUiState {
        override fun show(text: StatsText, button: Button) {
            text.update(StatsTextState.Default(correct, incorrect, skipped))
        }
    }

    object Empty : StatsUiState

    object Navigate : StatsUiState {
        override fun navigate(navigate: Navigation) {
            navigate.navigateToLoad()
        }
    }
}
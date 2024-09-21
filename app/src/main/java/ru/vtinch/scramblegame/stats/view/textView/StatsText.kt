package ru.vtinch.scramblegame.stats.view.textView

import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewUi

interface StatsText : CustomView.SetStatistic {
    data class Default(
        private val correct: Int,
        private val incorrect: Int,
        private val skipped: Int,
    ) : CustomViewUi.CastTo<StatsText>() {
        override val callback: (StatsText) -> Unit = {
            it.update(Triple(correct, incorrect, skipped))
        }
    }
}
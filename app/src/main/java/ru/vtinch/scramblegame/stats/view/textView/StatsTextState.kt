package ru.vtinch.scramblegame.stats.view.textView

import java.io.Serializable

interface StatsTextState : Serializable {

    fun update(view: StatsText)

    data class Default(
        private val correct: Int,
        private val incorrect: Int,
        private val skipped: Int,
    ) : StatsTextState {
        override fun update(view: StatsText) {
            val data = Triple(correct, incorrect, skipped)
            view.update(data)
        }
    }
}
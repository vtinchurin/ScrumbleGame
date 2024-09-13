package ru.vtinch.scramblegame.stats

import android.widget.FrameLayout
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractPage
import ru.vtinch.scramblegame.game.ButtonUi

class StatisticsPage(
    private val correct: Int,
    private val incorrect: Int,
    private val skipped: Int) : AbstractPage(containerId = R.id.stats_root, classType = FrameLayout::class.java) {

    private val statsUi = StatsUi(
        correct = this.correct,
        incorrect = this.incorrect,
        skipped = this.skipped,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher,
    )

    private val newGameUi = ButtonUi(
        id = R.id.new_game_button,
        text = R.string.new_game,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertInitialState(){
        statsUi.assertInitialState()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.doesNotExist()
    }

}

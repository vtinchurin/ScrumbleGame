package ru.vtinch.scramblegame.stats

import android.widget.FrameLayout
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractPage
import ru.vtinch.scramblegame.core.elements.ButtonUi
import ru.vtinch.scramblegame.core.elements.StatsTextUi

class StatisticsPage(
    private val correct: Int,
    private val incorrect: Int,
    private val skipped: Int) : AbstractPage(containerId = R.id.stats_root, classType = FrameLayout::class.java) {

    private val statsUi = StatsTextUi.Base(
        id = R.id.statistics_text,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher,
    )

    private val newGameUi: ButtonUi = ButtonUi.Base(
        id = R.id.new_game_button,
        text = R.string.new_game,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertInitialState(){
        statsUi.assertText("STATISTICS\n\n\nCorrect: $correct\nIncorrect: $incorrect\nSkipped: $skipped")
        newGameUi.assertVisible()
        newGameUi.assertEnabled()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.doesNotExist()
    }

}

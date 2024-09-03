package ru.vtinch.scramblegame.stats

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.game.ButtonUi

class StatisticsPage(
    private val correct: Int,
    private val incorrect: Int,
    private val skipped: Int) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.stats_root))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java))

    private val statsUi = StatsUi(
        correct = correct,
        incorrect = incorrect,
        skipped = skipped,
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
        statsUi.assertInitialState(correct = correct, incorrect = incorrect, skipped = skipped)
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.assertDoesNotExist()
    }

}

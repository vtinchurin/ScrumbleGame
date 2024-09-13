package ru.vtinch.scramblegame.stats

import android.view.View
import android.widget.TextView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractTextUi
import ru.vtinch.scramblegame.core.Ui

class StatsUi(
    private val correct: Int,
    private val incorrect: Int,
    private val skipped: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>,
):AbstractTextUi(
    matchers = listOf(
        containerIdMatcher,
        containerClassTypeMatcher,
        withId(R.id.statistics_text),
        isAssignableFrom(TextView::class.java),
    )
) {

    fun assertInitialState() {
        interaction.check(matches(withText("STATISTICS\n\n\nCorrect: $correct\nIncorrect: $incorrect\nSkipped: $skipped")))
    }
}
/*
STATISTICS\n\n\nCorrect: 0 \nIncorrect: 0 \nSkipped: 4

STATISTICS\n\n\nCorrect: 0\nIncorrect: 0\nSkipped: 4
 */
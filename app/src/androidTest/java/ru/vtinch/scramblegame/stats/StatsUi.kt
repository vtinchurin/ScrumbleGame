package ru.vtinch.scramblegame.stats

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R

class StatsUi(
    correct: Int,
    incorrect: Int,
    skipped: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>,
) {


    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.statistics_text),
            isAssignableFrom(TextView::class.java),
        )
    )

    fun assertInitialState(correct: Int, incorrect: Int, skipped: Int) {
        interaction.check(matches(withText("STATISTICS\n\n\nCorrect: $correct \nIncorrect: $incorrect \nSkipped: $skipped")))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }
}

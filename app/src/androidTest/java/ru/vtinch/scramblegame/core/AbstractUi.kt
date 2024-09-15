package ru.vtinch.scramblegame.core

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.core.matchers.AssertText
import ru.vtinch.scramblegame.core.matchers.Enabled
import ru.vtinch.scramblegame.core.matchers.Existence
import ru.vtinch.scramblegame.core.matchers.Visibility

abstract class AbstractUi(
    matchers: List<Matcher<View>>
) : Visibility, Existence, Enabled, AssertText {
    protected val interaction: ViewInteraction = onView(
        allOf(matchers)
    )

    override fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    override fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    override fun doesNotExist() {
        interaction.check(ViewAssertions.doesNotExist())
    }

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }

    override fun assertEnabled() {
        interaction.check(matches(isEnabled()))
    }

    override fun assertNotEnabled() {
        interaction.check(matches(isNotEnabled()))
    }

    override fun assertText(text: String) {
        interaction.check(matches(withText(text)))
    }
}

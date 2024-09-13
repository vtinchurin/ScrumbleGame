package ru.vtinch.scramblegame.core

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

abstract class Ui(
    matchers: List<Matcher<View>>
)
{
    protected val interaction: ViewInteraction = onView(
        allOf(matchers)
    )
}


abstract class AbstractTextUi(
        matchers: List<Matcher<View>>,
    ) : Ui(matchers),Visibility, Existence {

        override fun assertVisible() {
            interaction.check(matches(isDisplayed()))
        }

        override fun assertNotVisible() {
            interaction.check(matches(not(isDisplayed())))
        }

        override fun doesNotExist() {
            interaction.check(ViewAssertions.doesNotExist())
        }
    }

    abstract class AbstractButtonUi(
        matchers: List<Matcher<View>>,
    ) : Ui(matchers),Visibility, Enabled {


        fun click() {
            interaction.perform(androidx.test.espresso.action.ViewActions.click())
        }

        override fun assertVisible() {
            interaction.check(matches(isDisplayed()))
        }

        override fun assertNotVisible() {
            interaction.check(matches(not(isDisplayed())))
        }

        override fun assertEnabled() {
            interaction.check(matches(isEnabled()))
        }

        override fun assertNotEnabled() {
            interaction.check(matches(isNotEnabled()))
        }
    }

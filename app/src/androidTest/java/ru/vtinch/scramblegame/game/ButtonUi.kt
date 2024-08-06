package ru.vtinch.scramblegame.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

class ButtonUi(
    id: Int,
    text: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(text),
            isAssignableFrom(AppCompatButton::class.java)
        )
    )

    fun assertEnabled() {
        interaction.check(matches(isEnabled()))
    }


    fun assertNotEnabled() {
        interaction.check(matches(isNotEnabled()))
    }

    fun assertInvisible() {
        interaction.check(matches(not(isEnabled())))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun click() {
        TODO("Not yet implemented")
    }


}

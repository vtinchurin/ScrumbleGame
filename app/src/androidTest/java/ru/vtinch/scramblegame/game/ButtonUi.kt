package ru.vtinch.scramblegame.game

import android.view.View
import android.widget.Button
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
import ru.vtinch.scramblegame.core.AbstractButtonUi
import ru.vtinch.scramblegame.core.Ui

class ButtonUi(
    id: Int,
    text: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
):AbstractButtonUi(
    matchers = listOf(
        withId(id),
        withText(text),
        isAssignableFrom(Button::class.java),
        containerIdMatcher,
        containerClassTypeMatcher
    )
) {
}

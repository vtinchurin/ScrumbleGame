package ru.vtinch.scramblegame.core.elements

import android.view.View
import android.widget.Button
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.core.AbstractUi
import ru.vtinch.scramblegame.core.matchers.Enabled
import ru.vtinch.scramblegame.core.matchers.Existence
import ru.vtinch.scramblegame.core.matchers.Visibility

interface ButtonUi : Enabled, Existence, Visibility {
    fun click()


    class Base(
        id: Int,
        text: Int,
        containerIdMatcher: Matcher<View>,
        containerClassTypeMatcher: Matcher<View>
    ) : AbstractUi(
        matchers = listOf(
            withId(id),
            withText(text),
            isAssignableFrom(Button::class.java),
            containerIdMatcher,
            containerClassTypeMatcher
        )
    ), ButtonUi
}
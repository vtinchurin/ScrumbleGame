package ru.vtinch.scramblegame.core.elements

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.core.AbstractUi
import ru.vtinch.scramblegame.core.matchers.AssertText
import ru.vtinch.scramblegame.core.matchers.Existence
import ru.vtinch.scramblegame.core.matchers.Visibility

interface DefaultTextUi : Visibility, Existence, AssertText {

    class Base(
        id: Int,
        textId: Int,
        containerClassTypeMatcher: Matcher<View>,
        containerIdMatcher: Matcher<View>,
    ) : AbstractUi(
        matchers = listOf(
            withId(id),
            withText(textId),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            containerClassTypeMatcher
        )
    ), DefaultTextUi
}
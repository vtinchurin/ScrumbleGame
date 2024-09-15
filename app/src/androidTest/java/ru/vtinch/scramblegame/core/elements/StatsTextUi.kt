package ru.vtinch.scramblegame.core.elements

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.core.AbstractUi

interface StatsTextUi : DefaultTextUi {

    class Base(
        id: Int,
        containerClassTypeMatcher: Matcher<View>,
        containerIdMatcher: Matcher<View>,
    ) : AbstractUi(
        matchers = listOf(
            withId(id),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            containerClassTypeMatcher
        )
    ), StatsTextUi
}
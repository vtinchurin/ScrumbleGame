package ru.vtinch.scramblegame.load

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.core.AbstractTextUi

class ErrorUi(
    id: Int,
    textId: Int,
    containerClassTypeMatcher: Matcher<View>,
    containerIdMatcher: Matcher<View>,
) : AbstractTextUi(
    matchers = listOf(
        withId(id),
        withText(textId),
        isAssignableFrom(TextView::class.java),
        containerIdMatcher,
        containerClassTypeMatcher
    )
) {


}

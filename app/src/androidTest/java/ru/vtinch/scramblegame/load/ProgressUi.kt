package ru.vtinch.scramblegame.load

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractTextUi

class ProgressUi(
    containerClassTypeMatcher: Matcher<View>,
    containerIdMatcher: Matcher<View>,
) : AbstractTextUi(
    matchers = listOf(
        withId(R.id.progressUi),
        containerIdMatcher,
        containerClassTypeMatcher
    )
) {

}

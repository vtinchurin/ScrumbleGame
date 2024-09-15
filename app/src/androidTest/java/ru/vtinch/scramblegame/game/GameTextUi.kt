package ru.vtinch.scramblegame.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractUi
import ru.vtinch.scramblegame.core.BackgroundDrawableMatcher
import ru.vtinch.scramblegame.core.elements.GameTextUi

class GameTextUi(
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractUi(
    matchers = listOf(
        containerIdMatcher,
        containerClassTypeMatcher,
        withId(R.id.answerText),
        isAssignableFrom(TextView::class.java),
    )
), GameTextUi {

    override fun assertBg(bgResId: Int) {
        interaction.check(matches(BackgroundDrawableMatcher(bgResId)))
    }

}

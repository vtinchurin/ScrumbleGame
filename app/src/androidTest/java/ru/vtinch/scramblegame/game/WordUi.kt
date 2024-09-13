package ru.vtinch.scramblegame.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractTextUi
import ru.vtinch.scramblegame.core.Ui

class
WordUi(
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
): AbstractTextUi(
    matchers = listOf(
        containerIdMatcher,
        containerClassTypeMatcher,
        withId(R.id.answerText),
        isAssignableFrom(TextView::class.java),
    )
) {

    fun assertText(text: String){
        interaction.check(matches(withText(text)))
    }

    fun assertBg(bgResId: Int) {
        interaction.check(matches(BackgroundDrawableMatcher(bgResId)))
    }


}

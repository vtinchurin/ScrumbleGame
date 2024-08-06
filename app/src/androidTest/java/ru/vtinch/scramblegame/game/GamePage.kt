package ru.vtinch.scramblegame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.vtinch.scramblegame.R

class GamePage(
    private val word: String,
) {
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val wordUi = WordUi(
        text = word,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val inputUi = InputUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val skipUi = ButtonUi(
        R.string.skip,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val checkUi = ButtonUi(
        R.string.next,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val nextUi = ButtonUi(
        R.string.next,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertInitialState() {
        wordUi.assertStrikeColor(color = R.color.gray)
        skipUi.assertEnabled()
        nextUi.assertGone()
        checkUi.assertNotEnabled()
    }

    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assetCorrectPredictionState() {
        skipUi.assertEnabled()
        checkUi.assertEnabled()
        nextUi.assertGone()
    }

    fun assertCorrectAnswerState() {
        wordUi.assertStrikeColor(color = R.color.green)
        checkUi.assertGone()
        skipUi.assertInvisible()
        nextUi.assertVisible()

    }

    fun clickCheck() {
        checkUi.click()
    }

    fun clickNext() {
        nextUi.click()
    }

    fun clickSkip() {
        skipUi.click()
    }

    fun assertIncorrectAnswerState() {
        wordUi.assertStrikeColor(color = R.color.red)
        checkUi.assertNotEnable()
        skipUi.assertVisible()
        nextUi.assertGone()
    }

    fun removeLastLetter() {
        inputUi.removeLetter()
    }

}

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
        id = R.id.skipButton,
        text =
        R.string.skip,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val checkUi = ButtonUi(
        id = R.id.checkButton,
        text =R.string.check,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        text =
        R.string.next,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertInitialState() {
        wordUi.assertText(word)
        wordUi.assertBg(bgResId = R.drawable.bg_gray)
        checkUi.assertNotEnabled()
        skipUi.assertEnabled()
        nextUi.assertInvisible()
    }

    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assetCorrectPredictionState() {
        checkUi.assertEnabled()
        skipUi.assertEnabled()
        nextUi.assertInvisible()
    }

    fun assertCorrectAnswerState() {
        wordUi.assertText(word.reversed())
        wordUi.assertBg(bgResId = R.drawable.bg_green)
        checkUi.assertInvisible()
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
        wordUi.assertText(word)
        wordUi.assertBg(bgResId = R.drawable.bg_red)
        checkUi.assertNotEnabled()
        skipUi.assertVisible()
        nextUi.assertInvisible()
    }

    fun removeLastLetter() {
        inputUi.removeLetter()
    }

    fun assertNotVisible() {
        wordUi.assertDoesNotExist()
    }

}

package ru.vtinch.scramblegame.game

import android.widget.LinearLayout
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.AbstractPage

class GamePage(
    private val word: String,
)  : AbstractPage(containerId = R.id.rootLayout, classType = LinearLayout::class.java){

    private val wordUi = WordUi(
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
        nextUi.assertNotVisible()
    }

    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assertCorrectPredictionState() {
        checkUi.assertEnabled()
        skipUi.assertEnabled()
        nextUi.assertNotVisible()
    }

    fun assertCorrectAnswerState() {
        wordUi.assertText(word.reversed())
        wordUi.assertBg(bgResId = R.drawable.bg_green)
        checkUi.assertNotVisible()
        skipUi.assertNotVisible()
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
        nextUi.assertNotVisible()
    }

    fun removeLastLetter() {
        inputUi.removeLetter()
    }

    fun assertNotVisible() {
        wordUi.doesNotExist()
    }

}

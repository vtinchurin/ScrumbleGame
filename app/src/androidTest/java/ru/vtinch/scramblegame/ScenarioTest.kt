package ru.vtinch.scramblegame

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.easycode.zerotoheroandroidtdd.waitTillDisplayed
import ru.vtinch.scramblegame.game.BackgroundDrawableMatcher
import ru.vtinch.scramblegame.game.GamePage


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup(){
        gamePage = GamePage(word = "input".reversed())
    }

    @Test
    fun caseNumber1() {
        gamePage.assertInitialState()
        gamePage.addInput(text = "inpu")
        gamePage.assertInitialState()
        gamePage.addInput(text = "t")
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        gamePage.assertCorrectAnswerState()
        gamePage.clickNext()

        gamePage = GamePage(word = "world".reversed())
        gamePage.assertInitialState()

    }

    @Test
    fun caseNumber2(){
        gamePage.assertInitialState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "world".reversed())
        gamePage.assertInitialState()
        gamePage.addInput(text = "worl")
        gamePage.assertInitialState()
        gamePage.addInput(text = "d")
        gamePage.assetCorrectPredictionState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "prediction".reversed())
        gamePage.assertInitialState()
        gamePage.addInput(text = "predictiot")
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "snow".reversed())
        gamePage.assertInitialState()
        gamePage.addInput(text = "sno")
        gamePage.assertInitialState()
        gamePage.removeLastLetter()
        gamePage.removeLastLetter()
        gamePage.removeLastLetter()
        gamePage.assertInitialState()
        gamePage.addInput(text = "show")
        gamePage.assetCorrectPredictionState()
        gamePage.removeLastLetter()
        gamePage.assertInitialState()
        gamePage.addInput(text = "w")
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
    }

    @Test
    fun case3(){
        gamePage.assertInitialState()
        gamePage.addInput(text = "12345")
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
        onView(withId(R.id.inputLayout)).perform(waitTillDisplayed(R.id.inputEditText,3000))
//        gamePage.waitWhileInit(3000)
        gamePage.assertInitialState()
       //BackgroundDrawableMatcher(R.drawable.bg_gray).matches(R.id.answerText)
    }
}
package ru.vtinch.scramblegame

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.vtinch.scramblegame.game.GamePage
import ru.vtinch.scramblegame.load.LoadPage
import ru.vtinch.scramblegame.main.MainActivity
import ru.vtinch.scramblegame.stats.StatisticsPage


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage


    private fun ScenarioTest.doWithRecreate(block: () -> Unit) {
        activityScenarioRule.scenario.recreate()
        block.invoke()
        activityScenarioRule.scenario.recreate()
        block.invoke()
        activityScenarioRule.scenario.recreate()
    }

    @Before
    fun setup() {
        gamePage = GamePage(word = "input".reversed())
    }

    @After
    fun close() {
        val sharedPreferences = ApplicationProvider
            .getApplicationContext<Context>()
            .getSharedPreferences("myApp", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    @Test
    fun caseNumber1() {
        initialLoading()
        doWithRecreate { }
        gamePage.assertInitialState()
        gamePage.addInput(text = "inpu")
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "t")
        doWithRecreate { gamePage.assertCorrectPredictionState() }
        gamePage.clickCheck()
        doWithRecreate { gamePage.assertCorrectAnswerState() }
        gamePage.clickNext()
        gamePage = GamePage(word = "world".reversed())
        doWithRecreate { gamePage.assertInitialState() }
    }

    @Test
    fun caseNumber2() {
        initialLoading()
        doWithRecreate { gamePage.assertInitialState()}
        gamePage.clickSkip()
        gamePage = GamePage(word = "world".reversed())
        doWithRecreate { gamePage.assertInitialState()}
        gamePage.addInput(text = "worl")
        doWithRecreate { gamePage.assertInitialState()}
        gamePage.addInput(text = "d")
        doWithRecreate { gamePage.assertCorrectPredictionState() }
        gamePage.clickSkip()
        gamePage = GamePage(word = "prediction".reversed())
        doWithRecreate { gamePage.assertInitialState()}
        gamePage.addInput(text = "predictiot")
        doWithRecreate { gamePage.assertCorrectPredictionState()}
        gamePage.clickCheck()
        doWithRecreate { gamePage.assertIncorrectAnswerState()}
        gamePage.waitTillError()
        gamePage.clickSkip()
        gamePage = GamePage(word = "snow".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "sno")
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.removeLastLetter()
        gamePage.removeLastLetter()
        gamePage.removeLastLetter()
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "show")
        activityScenarioRule.scenario.recreate()
        doWithRecreate { gamePage.assertCorrectPredictionState()}
        gamePage.removeLastLetter()
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "w")
        doWithRecreate { gamePage.assertCorrectPredictionState()}
        gamePage.clickCheck()
        doWithRecreate { gamePage.assertIncorrectAnswerState()}
    }

    @Test
    fun all_skipped_answers() {
        initialLoading()
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.clickSkip()
        gamePage = GamePage(word = "world".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.clickSkip()
        gamePage = GamePage(word = "prediction".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.clickSkip()
        gamePage = GamePage(word = "snow".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.clickSkip()
        doWithRecreate { gamePage.assertNotVisible()}

        val statisticsPage = StatisticsPage(correct = 0, incorrect = 0, skipped = 4)
        doWithRecreate { statisticsPage.assertInitialState() }
        statisticsPage.clickNewGame()
        statisticsPage.assertNotVisible()

        initialLoading()
        gamePage = GamePage(word = "input".reversed())
        doWithRecreate { gamePage.assertInitialState() }
    }

    @Test
    fun all_incorrect_answers() {
        initialLoading()
        doWithRecreate { gamePage.assertInitialState()}
        gamePage.addInput(text = "intup")
        doWithRecreate {  gamePage.assertCorrectPredictionState()}
        gamePage.clickCheck()
        doWithRecreate { gamePage.assertIncorrectAnswerState()}
        gamePage.waitTillError()
        gamePage.clickSkip()
        gamePage = GamePage(word = "world".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "wrdlo")
        doWithRecreate { gamePage.assertCorrectPredictionState()}
        gamePage.clickCheck()
        doWithRecreate { gamePage.assertIncorrectAnswerState()}
        gamePage.waitTillError()
        gamePage.clickSkip()
        gamePage = GamePage(word = "prediction".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "predictoin")
        doWithRecreate { gamePage.assertCorrectPredictionState()}
        gamePage.clickCheck()
        doWithRecreate { gamePage.assertIncorrectAnswerState()}
        gamePage.waitTillError()
        gamePage.clickSkip()
        gamePage = GamePage(word = "snow".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "nswo")
        doWithRecreate { gamePage.assertCorrectPredictionState()}
        gamePage.clickCheck()

        doWithRecreate { gamePage.assertIncorrectAnswerState()}
        gamePage.clickSkip()
        doWithRecreate {  gamePage.assertNotVisible()}

        val statisticsPage = StatisticsPage(correct = 0, incorrect = 4, skipped = 4)
        doWithRecreate { statisticsPage.assertInitialState() }
        statisticsPage.clickNewGame()
        doWithRecreate { statisticsPage.assertNotVisible() }
        initialLoading()
        gamePage = GamePage(word = "input".reversed())
        doWithRecreate { gamePage.assertInitialState() }
    }

    @Test
    fun all_correct_answers() {
        initialLoading()
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "input")
        doWithRecreate { gamePage.assertCorrectPredictionState() }
        gamePage.clickCheck()
        activityScenarioRule.scenario.recreate()
        doWithRecreate { gamePage.assertCorrectAnswerState() }
        gamePage.clickNext()
        gamePage = GamePage(word = "world".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "world")
        doWithRecreate { gamePage.assertCorrectPredictionState() }
        gamePage.clickCheck()
        activityScenarioRule.scenario.recreate()
        doWithRecreate { gamePage.assertCorrectAnswerState() }
        gamePage.clickNext()
        gamePage = GamePage(word = "prediction".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "prediction")
        doWithRecreate { gamePage.assertCorrectPredictionState() }
        gamePage.clickCheck()
        activityScenarioRule.scenario.recreate()
        doWithRecreate { gamePage.assertCorrectAnswerState() }
        gamePage.clickNext()
        gamePage = GamePage(word = "snow".reversed())
        doWithRecreate { gamePage.assertInitialState() }
        gamePage.addInput(text = "snow")
        doWithRecreate { gamePage.assertCorrectPredictionState() }
        gamePage.clickCheck()
        activityScenarioRule.scenario.recreate()
        doWithRecreate { gamePage.assertCorrectAnswerState() }
        gamePage.clickNext()
        doWithRecreate { gamePage.assertNotVisible() }

        val statisticsPage = StatisticsPage(correct = 4, incorrect = 0, skipped = 0)
        doWithRecreate { statisticsPage.assertInitialState() }
        statisticsPage.clickNewGame()
        doWithRecreate { statisticsPage.assertNotVisible() }
        initialLoading()
        gamePage = GamePage(word = "input".reversed())
        doWithRecreate { gamePage.assertInitialState() }
    }

    @Test
    fun initialLoading() {
        val loadPage = LoadPage()

        doWithRecreate { loadPage.assertLoading() }

        loadPage.waitTillError()

        doWithRecreate { loadPage.assertErrorState() }

        loadPage.clickRetry()

        doWithRecreate { loadPage.assertLoading()}

        gamePage.waitTillLoad()

    }
}
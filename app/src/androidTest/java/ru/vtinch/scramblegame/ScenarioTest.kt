package ru.vtinch.scramblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.vtinch.scramblegame.game.GamePage
import ru.vtinch.scramblegame.stats.StatisticsPage


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
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "t")
        activityScenarioRule.scenario.recreate()
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        activityScenarioRule.scenario.recreate()
        gamePage.assertCorrectAnswerState()
        gamePage.clickNext()

        gamePage = GamePage(word = "world".reversed())
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

    }

    @Test
    fun caseNumber2(){
        gamePage.assertInitialState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "world".reversed())
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "worl")
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "d")
        activityScenarioRule.scenario.recreate()
        gamePage.assetCorrectPredictionState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "prediction".reversed())
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "predictiot")
        activityScenarioRule.scenario.recreate()
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectAnswerState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "snow".reversed())
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "sno")
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.removeLastLetter()
        gamePage.removeLastLetter()
        gamePage.removeLastLetter()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "show")
        activityScenarioRule.scenario.recreate()
        gamePage.assetCorrectPredictionState()
        gamePage.removeLastLetter()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.addInput(text = "w")
        gamePage.assetCorrectPredictionState()
        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
    }

    @Test
    fun all_skipped_answers(){
        gamePage.assertInitialState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "world".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "prediction".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage = GamePage(word = "snow".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
        gamePage.clickSkip()
        gamePage.assertNotVisible()

        val statisticsPage = StatisticsPage(correct = 0, incorrect = 0,skipped =4)
        statisticsPage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        statisticsPage.assertInitialState()
        statisticsPage.clickNewGame()
        statisticsPage.assertNotVisible()

        gamePage = GamePage(word = "input".reversed())
        gamePage.assertInitialState()
    }



}
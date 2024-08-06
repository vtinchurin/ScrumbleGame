package ru.vtinch.scramblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage:GamePage

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
        gamePage.assertInitialState()
        gamePage.clickSkip()
        gamePage = GamePage(word = "fold".reversed())
        gamePage.assertInitialState()
        gamePage.addInput(text = "fold")
        gamePage.assetCorrectPredictionState()
        gamePage.removeLastLetter()
        gamePage.assertIncorrectAnswerState()
        gamePage.addInput(text = "d")
        gamePage.assetCorrectPredictionState()

    }
}
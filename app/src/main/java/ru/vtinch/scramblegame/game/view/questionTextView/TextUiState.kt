package ru.vtinch.scramblegame.game.view.questionTextView

import  ru.vtinch.scramblegame.R
import java.io.Serializable

interface TextUiState: Serializable {
    fun update(textUiState: QuestionText)

    abstract class Abstract(
        private val bgResId: Int,
    ) : TextUiState {

        override fun update(textUiState: QuestionText) {
            textUiState.setBg(bgResId)
        }
    }

    object Initial : Abstract(R.drawable.bg_gray)
    object Correct : Abstract(R.drawable.bg_green)
    object Incorrect : Abstract(R.drawable.bg_red)

}

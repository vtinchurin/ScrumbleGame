package ru.vtinch.scramblegame.game.view.questionTextView

import  ru.vtinch.scramblegame.R
import java.io.Serializable

interface TextUiState: Serializable {
    fun update(textUiState: QuestionText)

    abstract class Abstract(
        private val text:String,
        protected val bgResId: Int,
    ) : TextUiState {

        override fun update(textUiState: QuestionText) {
            textUiState.setText(text)
            textUiState.setBg(bgResId)
        }
    }

    class Initial(question: String) : Abstract(question,R.drawable.bg_gray)
    class Correct(answer: String) : Abstract(answer,R.drawable.bg_green)
    object Incorrect : Abstract("",R.drawable.bg_red){
        override fun update(textUiState: QuestionText) {
            textUiState.setBg(bgResId)
        }
    }

}

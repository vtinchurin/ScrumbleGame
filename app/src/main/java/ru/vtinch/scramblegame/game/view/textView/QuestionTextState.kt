package ru.vtinch.scramblegame.game.view.textView

import ru.vtinch.scramblegame.R
import java.io.Serializable

interface QuestionTextState : Serializable {

    fun update(view: QuestionText)

    abstract class Abstract(
        private val text: String,
        protected val bgResId: Int,
    ) : QuestionTextState {
        override fun update(view: QuestionText) {
            view.update(text)
            view.update(bgResId)
        }
    }

    data class Initial(private val question: String) :
        Abstract(text = question, bgResId = R.drawable.bg_gray)

    data class Correct(private val answer: String) :
        Abstract(text = answer, bgResId = R.drawable.bg_green)

    data object Incorrect : Abstract(text = "", bgResId = R.drawable.bg_red) {
        override fun update(view: QuestionText) {
            view.update(bgRes = bgResId)
        }
    }

    object Default : Abstract(text = "", bgResId = R.drawable.bg_gray)
}
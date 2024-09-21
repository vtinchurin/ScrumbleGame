package ru.vtinch.scramblegame.game.view.textView

import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewUi

interface QuestionText : CustomView.UpdateText, CustomView.SetBackground {
    abstract class Abstract(
        private val text: String,
        protected val resId: Int,
    ) : CustomViewUi.CastTo<QuestionText>() {
        override val callback: (QuestionText) -> Unit = {
            it.update(text)
            it.updateBgRes(resId)
        }
    }

    data class Initial(private val question: String) : Abstract(question, R.drawable.bg_gray)
    data class Correct(private val answer: String) : Abstract(answer, R.drawable.bg_green)
    object Incorrect : Abstract("", R.drawable.bg_red) {
        override val callback: (QuestionText) -> Unit = {
            it.updateBgRes(resId = resId)
        }
    }
}
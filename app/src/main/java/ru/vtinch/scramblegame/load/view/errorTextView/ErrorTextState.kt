package ru.vtinch.scramblegame.load.view.errorTextView

import android.view.View
import ru.vtinch.scramblegame.R
import java.io.Serializable

interface ErrorTextState : Serializable {

    fun update(view: ErrorText)

    abstract class Abstract(
        protected val visibility: Int,
        private val textResId: Int = R.string.no_connection
    ) : ErrorTextState {
        override fun update(view: ErrorText) {
            view.setTextRes(textResId)
            view.update(visibility)
        }
    }

    data object Gone : Abstract(View.GONE)
    data class Default(private val resId: Int) : Abstract(View.VISIBLE, textResId = resId)
}
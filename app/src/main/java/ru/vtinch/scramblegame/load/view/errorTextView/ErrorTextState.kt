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
    data object Default : Abstract(View.VISIBLE)
    data class Custom(private val text: String) : Abstract(View.VISIBLE) {
        override fun update(view: ErrorText) {
            view.update(text)
            view.update(visibility)
        }
    }
}
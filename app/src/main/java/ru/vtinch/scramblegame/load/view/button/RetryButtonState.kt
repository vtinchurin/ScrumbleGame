package ru.vtinch.scramblegame.load.view.button

import android.view.View
import java.io.Serializable

interface RetryButtonState : Serializable {

    fun update(view: RetryButton)

    abstract class Abstract(
        private val visibility: Int,
    ) : RetryButtonState {
        override fun update(view: RetryButton) {
            view.update(visibility)
        }
    }

    data object Visible : Abstract(View.VISIBLE)
    data object Gone : Abstract(View.GONE)
}
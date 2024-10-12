package ru.vtinch.scramblegame.load.view.progressView

import android.view.View
import java.io.Serializable

interface LoadProgressState : Serializable {

    fun update(view: LoadProgress)

    abstract class Abstract(
        private val visibility: Int,
    ) : LoadProgressState {
        override fun update(view: LoadProgress) {
            view.update(visibility)
        }
    }

    data object Visible : Abstract(View.VISIBLE)
    data object Gone : Abstract(View.GONE)
}
package ru.vtinch.scramblegame.load.view.progressView

import android.view.View
import java.io.Serializable

interface LoadProgressUiState : Serializable {

    fun update(data: LoadProgress)

    abstract class Abstract(
        private val visibility: Int
    ) : LoadProgressUiState {
        override fun update(data: LoadProgress) {
            data.update(visibility = visibility)
        }
    }

    class Visible : Abstract(View.VISIBLE)
    class Gone : Abstract(View.GONE)

}
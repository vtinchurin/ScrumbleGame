package ru.vtinch.scramblegame.load.view.button

import android.view.View
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewUi

interface RetryButton : CustomView.UpdateVisibility {

    abstract class Abstract(
        private val visibility: Int,
    ) : CustomViewUi {
        override fun <T : CustomView> update(button: T) {
            (button as RetryButton).update(visibility)
        }
    }

    object Visible : Abstract(View.VISIBLE)
    object Gone : Abstract(View.GONE)
}
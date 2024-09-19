package ru.vtinch.scramblegame.game.view.button

import android.view.View
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewUi

interface GameButton :CustomView.UpdateVisibility, CustomView.UpdateDisabled {

    abstract class Abstract(
        private val visibility: Int,
        private val isEnabled : Boolean
    ):CustomViewUi{
        override fun <T : CustomView> update(button: T) {
            (button as GameButton).update(visibility)
            (button as GameButton).update(isEnabled)
        }
    }

    object Enabled:Abstract(visibility = View.VISIBLE,true)
    object Disabled:Abstract(visibility = View.VISIBLE,false)
    object Invisible:Abstract(visibility = View.INVISIBLE,false)
    object Gone:Abstract(visibility = View.GONE,false)
}
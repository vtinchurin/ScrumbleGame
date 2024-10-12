package ru.vtinch.scramblegame.game.view.button

import android.view.View
import java.io.Serializable

interface GameButtonState : Serializable {

    fun update(view: GameButton)

    abstract class Abstract(
        private val visibility: Int,
        private val isEnabled: Boolean,
    ) : GameButtonState {
        override fun update(view: GameButton) {
            view.update(visibility)
            view.update(isEnabled)
        }
    }

    data object Enabled : Abstract(View.VISIBLE, true)
    data object Disabled : Abstract(View.VISIBLE, false)
    data object Invisible : Abstract(View.INVISIBLE, false)
    data object Gone : Abstract(View.GONE, false)

}
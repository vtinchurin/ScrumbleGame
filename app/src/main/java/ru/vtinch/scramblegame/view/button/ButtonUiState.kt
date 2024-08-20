package ru.vtinch.scramblegame.view.button

import android.view.View
import java.io.Serializable

interface ButtonUiState : Serializable {

    fun update(checkButton: CustomButton)

    abstract class Abstract(
        private val isEnabled: Boolean,
        private val visibility: Int,
    ) : ButtonUiState {

        override fun update(checkButton: CustomButton) {
            checkButton.setEnable(isEnabled)
            checkButton.setsVisibility(visibility)
        }
    }

    object Gone : Abstract(true, View.GONE)
    object Enabled : Abstract(true, View.VISIBLE)
    object Disabled : Abstract(false, View.VISIBLE)
    object Invisible : Abstract(false, View.INVISIBLE)

}

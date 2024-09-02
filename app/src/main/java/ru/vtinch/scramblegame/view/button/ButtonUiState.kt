package ru.vtinch.scramblegame.view.button

import android.view.View
import java.io.Serializable

interface ButtonUiState : Serializable {

    fun update(checkButton: CustomButton)

    abstract class Abstract(
        private val isEnabled: Boolean = true,
        private val visibility: Int = View.VISIBLE,
    ) : ButtonUiState{

        override fun update(checkButton: CustomButton) {
            checkButton.setEnable(isEnabled)
            checkButton.setsVisibility(visibility)
        }
    }

    object Gone : Abstract(visibility = View.GONE)

    object Enabled : Abstract()

    object Disabled : Abstract(false)

    object Invisible : Abstract(visibility =  View.INVISIBLE)
}

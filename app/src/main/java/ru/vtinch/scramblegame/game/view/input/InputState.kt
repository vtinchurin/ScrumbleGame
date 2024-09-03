package ru.vtinch.scramblegame.game.view.input

import android.view.View
import java.io.Serializable

interface InputState : Serializable {

    fun update(userInput: UpdateInput)

    abstract class Abstract(
        private val visibility: Int,
        private val isEnabled: Boolean,
    ) : InputState {

        override fun update(userInput: UpdateInput) {
            userInput.update(visibility)
            userInput.update(isEnabled)
        }

    }

    object Initial : Abstract(View.VISIBLE, true) {
        override fun update(userInput: UpdateInput) {
            super.update(userInput)
            userInput.update("")
        }
    }

    object Correct : Abstract(View.INVISIBLE, true)

    object Incorrect : Abstract(View.VISIBLE, false){
        override fun update(userInput: UpdateInput) {
            userInput.update("")
            super.update(userInput)
        }
    }

}
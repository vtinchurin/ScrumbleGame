package ru.vtinch.scramblegame.game.view.button

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton


interface GameButton {

    fun update(state: GameButtonState)

    fun update(visibility: Int)

    fun update(isEnabled: Boolean)

    class Base : MaterialButton, GameButton {

        private lateinit var state: GameButtonState

        constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        )

        override fun update(state: GameButtonState) {
            this.state = state
            state.update(this)
        }

        override fun update(visibility: Int) {
            this.visibility = visibility
        }

        override fun update(isEnabled: Boolean) {
            this.isEnabled = isEnabled
        }

        /**
         * You need remove nullable and first "return",
         * if you use Material Views
         */

        override fun onSaveInstanceState(): Parcelable {
            super.onSaveInstanceState().let {
                val savedState = GameButtonSavedState(it)
                savedState.save(state)
                return savedState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val restoredState = state as GameButtonSavedState
            super.onRestoreInstanceState(restoredState.superState)
            update(restoredState.restore())
        }
    }
}


package ru.vtinch.scramblegame.load.view.progressView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.progressindicator.CircularProgressIndicator


interface LoadProgress {

    fun update(state: LoadProgressState)

    fun update(visibility: Int)

    class Base : CircularProgressIndicator, LoadProgress {

        private lateinit var state: LoadProgressState

        constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        )

        override fun update(state: LoadProgressState) {
            this.state = state
            state.update(this)
        }

        override fun update(visibility: Int) {
            this.visibility = visibility
        }

        /**
         * You need remove nullable and first "return",
         * if you use Material Views
         */

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val savedState = LoadProgressSavedState(it)
                savedState.save(state)
                return savedState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val restoredState = state as LoadProgressSavedState
            super.onRestoreInstanceState(restoredState.superState)
            update(restoredState.restore())
        }
    }
}


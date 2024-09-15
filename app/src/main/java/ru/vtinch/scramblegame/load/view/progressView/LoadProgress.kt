package ru.vtinch.scramblegame.load.view.progressView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.ProgressBar

interface LoadProgress {

    fun update(state: LoadProgressUiState)
    fun update(visibility: Int)

    abstract class AbstractProgress : ProgressBar, LoadProgress {

        private lateinit var state: LoadProgressUiState

        constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        )

        override fun update(visibility: Int) {
            this.visibility = visibility
        }

        override fun update(state: LoadProgressUiState) {
            this.state = state
            state.update(this)
        }

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val savedState = ProgressSavedState(it)
                savedState.save(state)
                return savedState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val restoredState = state as ProgressSavedState
            super.onRestoreInstanceState(restoredState.superState)
            update(restoredState.restore())
        }
    }
}


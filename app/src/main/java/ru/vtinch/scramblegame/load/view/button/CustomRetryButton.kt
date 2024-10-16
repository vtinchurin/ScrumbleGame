package ru.vtinch.scramblegame.load.view.button

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class CustomRetryButton : MaterialButton, RetryButton {

    private var state: RetryButtonState = RetryButtonState.Gone

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(state: RetryButtonState) {
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

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState().let {
            val savedState = RetryButtonSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as RetryButtonSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }
}
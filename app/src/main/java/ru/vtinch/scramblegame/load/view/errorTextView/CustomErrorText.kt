package ru.vtinch.scramblegame.load.view.errorTextView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomErrorText : AppCompatTextView, ErrorText {

    private var state: ErrorTextState = ErrorTextState.Gone

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText(): Boolean = true

    override fun update(state: ErrorTextState) {
        this.state = state
        state.update(this)
    }

    override fun update(text: String) {
        this.text = text
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun setTextRes(textResId: Int) {
        this.text = resources.getString(textResId)
    }

    /**
     * You need remove nullable and first "return",
     * if you use Material Views
     */

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ErrorTextSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ErrorTextSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }
}
package ru.vtinch.scramblegame.presentation_core.button

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import ru.vtinch.scramblegame.presentation_core.CustomSavedState
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewState

abstract class AbstractAppCompatButton : AppCompatButton, CustomView {

    private lateinit var state: CustomViewState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(state: CustomViewState) {
        this.state = state
        state.update(this)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = CustomSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as CustomSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }
}
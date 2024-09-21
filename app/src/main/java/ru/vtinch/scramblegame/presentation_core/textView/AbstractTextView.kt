package ru.vtinch.scramblegame.presentation_core.textView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import ru.vtinch.scramblegame.presentation_core.CustomSavedState
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewUi

abstract class AbstractTextView : androidx.appcompat.widget.AppCompatTextView, CustomView.Mutable {

    private lateinit var state: CustomViewUi

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(state: CustomViewUi) {
        this.state = state
        state.update(this)
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun update(isEnabled: Boolean) {
        this.isEnabled = isEnabled
    }

    override fun update(text: String) {
        this.text = text
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
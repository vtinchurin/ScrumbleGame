package ru.vtinch.scramblegame.presentation_core.button

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import ru.vtinch.scramblegame.presentation_core.CustomSavedState
import ru.vtinch.scramblegame.presentation_core.CustomViewUi
import ru.vtinch.scramblegame.presentation_core.MutableView

abstract class AbstractMaterialButton : MaterialButton, MutableView {

    private lateinit var state: CustomViewUi

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(text: String) {
        this.text = text
    }

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

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState().let {
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
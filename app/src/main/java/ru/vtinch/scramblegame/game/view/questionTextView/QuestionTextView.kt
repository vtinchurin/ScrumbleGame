package ru.vtinch.scramblegame.game.view.questionTextView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.graphics.component1
import ru.vtinch.scramblegame.R


class QuestionTextView : androidx.appcompat.widget.AppCompatTextView, QuestionText {

    private lateinit var state : TextUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText() = true

    override fun update(state: TextUiState) {
        this.state = state
        state.update(this)
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun setBg(bgResId: Int) {
        this.setBackgroundResource(bgResId)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = QuestionSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as QuestionSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

}



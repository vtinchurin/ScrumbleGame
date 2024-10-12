package ru.vtinch.scramblegame.game.view.textView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet


interface QuestionText {

    fun update(state: QuestionTextState)

    fun update(text: String)

    fun update(bgRes: Int)

    class Base : androidx.appcompat.widget.AppCompatTextView, QuestionText {

        private lateinit var state: QuestionTextState

        constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        )

        override fun update(state: QuestionTextState) {
            this.state = state
            state.update(this)
        }

        override fun update(text: String) {
            this.text = text
        }

        override fun update(bgRes: Int) {
            this.setBackgroundResource(bgRes)
        }

        /**
         * You need remove nullable and first "return",
         * if you use Material Views
         */

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val savedState = QuestionTextSavedState(it)
                savedState.save(state)
                return savedState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val restoredState = state as QuestionTextSavedState
            super.onRestoreInstanceState(restoredState.superState)
            update(restoredState.restore())
        }
    }
}


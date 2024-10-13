package ru.vtinch.scramblegame.stats.view.textView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ru.vtinch.scramblegame.R

class CustomStatsText : AppCompatTextView, StatsText {

    private lateinit var state: StatsTextState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText(): Boolean = true

    override fun update(state: StatsTextState) {
        this.state = state
        state.update(this)
    }

    override fun update(data: Triple<Int, Int, Int>) {
        this.text = resources.getString(R.string.stats, data.first, data.second, data.third)
    }


    /**
     * You need remove nullable and first "return",
     * if you use Material Views
     */

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = StatsTextSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as StatsTextSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }
}
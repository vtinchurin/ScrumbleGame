package ru.vtinch.scramblegame.game.view.statisticsTextView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import ru.vtinch.scramblegame.R

class StatsTextView : androidx.appcompat.widget.AppCompatTextView,StatsText {

    private lateinit var state : StatsUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText() = true

    override fun update(state: StatsUiState) {
        this.state = state
        state.update(this)
    }

    override fun setData(data: Triple<Int, Int, Int>) {
        this.text = resources.getString(R.string.stats,data.first,data.second,data.third)
    }


    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = StatsSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as StatsSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

}



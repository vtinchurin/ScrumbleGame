package ru.vtinch.scramblegame.stats.view.textView

import android.content.Context
import android.util.AttributeSet
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.presentation_core.textView.AbstractTextView

class CustomStatsTextView : AbstractTextView, StatsText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(data: Triple<Int, Int, Int>) {
        this.text = resources.getString(R.string.stats, data.first, data.second, data.third)
    }

}
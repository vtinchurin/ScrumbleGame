package ru.vtinch.scramblegame.view.questionTextView

import android.content.Context
import android.util.AttributeSet


class QuestionTextView : androidx.appcompat.widget.AppCompatTextView, QuestionText.MutableText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText() = true

    override fun setText(text: String) {
        this.text = text
    }

    override fun setBg(bgResId: Int) {
        this.setBackgroundResource(bgResId)
    }

}



package ru.vtinch.scramblegame.game.view.textView

import android.content.Context
import android.util.AttributeSet
import ru.vtinch.scramblegame.presentation_core.textView.AbstractTextView

class CustomQuestionTextView : AbstractTextView, QuestionText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun updateBgRes(resId: Int) {
        this.setBackgroundResource(resId)
    }
}
package ru.vtinch.scramblegame.load.view.errorTextView

import android.content.Context
import android.util.AttributeSet
import ru.vtinch.scramblegame.presentation_core.textView.AbstractTextView

class CustomErrorText :AbstractTextView, ErrorText {
        constructor(context: Context):super(context)
        constructor(context: Context,attrs: AttributeSet):super(context,attrs)
        constructor(context: Context,attrs:AttributeSet,defStyleAttr: Int):super(context,attrs,defStyleAttr)

        override fun update(visibility: Int) {
                this.visibility = visibility
        }
}
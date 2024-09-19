package ru.vtinch.scramblegame.load.view.button

import android.content.Context
import android.util.AttributeSet
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.presentation_core.button.AbstractMaterialButton

class CustomRetryButton : AbstractMaterialButton, RetryButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
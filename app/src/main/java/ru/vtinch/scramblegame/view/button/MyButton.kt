package ru.vtinch.scramblegame.view.button

import android.content.Context
import android.util.AttributeSet


class MyButton : CustomButton.AbstractButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
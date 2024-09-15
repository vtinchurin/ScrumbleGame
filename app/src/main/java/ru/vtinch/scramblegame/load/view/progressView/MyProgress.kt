package ru.vtinch.scramblegame.load.view.progressView

import android.content.Context
import android.util.AttributeSet

class MyProgress : LoadProgress.AbstractProgress {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
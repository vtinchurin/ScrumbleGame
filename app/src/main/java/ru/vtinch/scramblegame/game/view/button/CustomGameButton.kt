package ru.vtinch.scramblegame.game.view.button

import android.content.Context
import android.util.AttributeSet
import ru.vtinch.scramblegame.presentation_core.button.AbstractMaterialButton

class CustomGameButton : AbstractMaterialButton, GameButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun update(isEnabled: Boolean) {
        this.isEnabled = isEnabled
    }
}
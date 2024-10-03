package ru.vtinch.scramblegame.load.view.errorTextView

import android.view.View
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewState

interface ErrorText:CustomView.UpdateVisibility {

    abstract class Abstract(
        private val visibility: Int,
    ) : CustomViewState.CastTo<ErrorText>(){

        override val callback: (ErrorText) -> Unit = {
            it.update(visibility)
        }
    }

    data object Visible : Abstract(View.VISIBLE)
    data object Gone : Abstract(View.GONE)
}
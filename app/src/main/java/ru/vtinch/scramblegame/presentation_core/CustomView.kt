package ru.vtinch.scramblegame.presentation_core

import androidx.annotation.DrawableRes

interface CustomView {

    fun update(state: CustomViewState)

    /**
     * Common things for all views
     */

    interface UpdateVisibility : CustomView {
        fun update(visibility: Int)
    }

    interface UpdateDisabled : CustomView {
        fun update(isEnabled: Boolean)
    }

    interface UpdateText : CustomView {
        fun update(text: String)
    }

    /**
     * Special
     */

    interface SetStatistic : CustomView {
        fun update(data: Triple<Int, Int, Int>)
    }

    interface SetBackground : CustomView {
        fun updateBgRes(@DrawableRes resId: Int)
    }
}

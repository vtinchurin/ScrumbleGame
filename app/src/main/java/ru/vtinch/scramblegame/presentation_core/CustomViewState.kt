package ru.vtinch.scramblegame.presentation_core

import java.io.Serializable

interface CustomViewState : Serializable {

    fun update(button: CustomView)

    abstract class CastTo<K : CustomView> : CustomViewState {

            abstract val callback: (K) -> Unit

        override fun update(button: CustomView) {
                callback.invoke(button as K)
            }
        }
    }



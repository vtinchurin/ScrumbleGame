package ru.vtinch.scramblegame.presentation_core

import java.io.Serializable

interface CustomViewUi : Serializable {

    fun update(button: CustomView)

    abstract class CastTo<K : CustomView> : CustomViewUi {

            abstract val callback: (K) -> Unit

        override fun update(button: CustomView) {
                callback.invoke(button as K)
            }
        }
    }



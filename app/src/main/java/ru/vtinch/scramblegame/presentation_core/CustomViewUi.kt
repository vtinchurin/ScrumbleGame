package ru.vtinch.scramblegame.presentation_core

import java.io.Serializable

interface CustomViewUi : Serializable {

    fun <T : CustomView> update(button: T)

    interface CastTo<K : CustomView> : CustomViewUi {

        abstract class Abstract<K : CustomView> : CastTo<K> {

            abstract val callback: (K) -> Unit

            override fun <T : CustomView> update(button: T) {
                callback.invoke(button as K)
            }
        }
    }
}


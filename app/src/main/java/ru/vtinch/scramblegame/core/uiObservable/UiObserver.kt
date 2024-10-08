package ru.vtinch.scramblegame.core.uiObservable

interface UiObserver<T : Any> : Update<T> {

    fun isEmpty(): Boolean = false

    class Empty<T : Any> : UiObserver<T> {

        override fun isEmpty() = true

        override fun updateUi(data: T) = Unit
    }

}






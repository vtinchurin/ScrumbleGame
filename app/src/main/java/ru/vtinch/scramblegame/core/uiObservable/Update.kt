package ru.vtinch.scramblegame.core.uiObservable

interface Update<T : Any> {

    fun updateUi(data: T)

}
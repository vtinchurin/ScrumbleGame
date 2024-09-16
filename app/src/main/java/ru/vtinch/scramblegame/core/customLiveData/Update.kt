package ru.vtinch.scramblegame.core.customLiveData

interface Update<T : Any> {

    fun updateUi(data: T)
}
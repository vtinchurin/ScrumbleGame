package ru.vtinch.scramblegame.di

fun interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}


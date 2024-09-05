package ru.vtinch.scramblegame.di

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}


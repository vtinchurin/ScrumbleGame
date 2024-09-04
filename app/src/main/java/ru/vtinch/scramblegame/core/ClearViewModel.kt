package ru.vtinch.scramblegame.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out ViewModel>)
}


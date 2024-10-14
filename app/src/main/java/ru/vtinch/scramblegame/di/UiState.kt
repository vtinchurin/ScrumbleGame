package ru.vtinch.scramblegame.di

import ru.vtinch.scramblegame.core.Navigation

interface UiState {

    fun navigate(navigate: Navigation) = Unit

}
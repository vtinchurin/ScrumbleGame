package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.LiveDataWrapper


interface UiStateLiveDataWrapper {

    interface Read: LiveDataWrapper.Read<GameUiState>
    interface Update: LiveDataWrapper.Update<GameUiState>
    interface Mutable : Read, Update


    class Base: Mutable, LiveDataWrapper.Abstract<GameUiState>()
}
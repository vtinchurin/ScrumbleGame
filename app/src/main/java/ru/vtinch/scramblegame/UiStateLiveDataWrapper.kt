package ru.vtinch.scramblegame

import ru.vtinch.scramblegame.game.GameUiState
import ru.vtinch.scramblegame.game.LiveDataWrapper


interface UiStateLiveDataWrapper {

    interface Read: LiveDataWrapper.Read<GameUiState>
    interface Update: LiveDataWrapper.Update<GameUiState>
    interface Mutable : Read, Update


    class Base: Mutable, LiveDataWrapper.Abstract<GameUiState>()
}
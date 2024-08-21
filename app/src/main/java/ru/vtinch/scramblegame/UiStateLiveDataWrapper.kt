package ru.vtinch.scramblegame

import android.util.Log


interface UiStateLiveDataWrapper {

    interface Read: LiveDataWrapper.Read<UiState>
    interface Update: LiveDataWrapper.Update<UiState>
    interface Mutable : Read, Update


    class Base: Mutable, LiveDataWrapper.Abstract<UiState>()
}
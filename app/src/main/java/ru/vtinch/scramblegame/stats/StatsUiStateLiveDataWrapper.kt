package ru.vtinch.scramblegame.stats

import ru.vtinch.scramblegame.core.LiveDataWrapper

interface StatsUiStateLiveDataWrapper {

    interface Read: LiveDataWrapper.Read<StatsUiState>
    interface Update: LiveDataWrapper.Update<StatsUiState>
    interface Mutable : Read, Update


    class Base: Mutable, LiveDataWrapper.Abstract<StatsUiState>()
}
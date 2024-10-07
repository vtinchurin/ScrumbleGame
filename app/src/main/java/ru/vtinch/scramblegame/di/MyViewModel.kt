package ru.vtinch.scramblegame.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

interface MyViewModel{

    abstract class Abstract(): MyViewModel{
        
        protected val viewModelScope = CoroutineScope(SupervisorJob()+ Dispatchers.Main.immediate)

    }
}

package ru.vtinch.scramblegame.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.core.uiObservable.UiObserver

interface MyViewModel {

    interface Async<T : Any> : MyViewModel {

        fun startUpdate(observer: UiObserver<T>)

        fun stopUpdate()

    }

    abstract class Abstract<T : Any>(
        protected val observable: UiObservable<T>,
    ) : Async<T> {

        protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

        override fun startUpdate(observer: UiObserver<T>) {
            observable.update(observer)
        }

        override fun stopUpdate() {
            observable.update()
        }
    }
}

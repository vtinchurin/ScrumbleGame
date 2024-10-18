package ru.vtinch.scramblegame.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.core.uiObservable.UiObserver

interface MyViewModel {

    interface Async<T : Any> : MyViewModel {

        fun startUpdate(observer: UiObserver<T>)

        fun stopUpdate()

    }

    abstract class ObservableAsync<T : Any>(
        protected val observable: UiObservable<T>,
        protected val runAsync: RunAsync,
    ) : Async<T> {

        protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

        protected fun handleAsync(heavy: suspend () -> T) {
            runAsync.handleAsync(viewModelScope, heavy) {
                observable.updateUi(it)
            }
        }

        override fun startUpdate(observer: UiObserver<T>) {
            observable.update(observer)
        }

        override fun stopUpdate() {
            observable.update()
        }
    }

    abstract class Observable<T : Any>(
        protected val observable: UiObservable<T>,
    ) : Async<T> {

        override fun startUpdate(observer: UiObserver<T>) {
            observable.update(observer)
        }

        override fun stopUpdate() {
            observable.update()
        }
    }
}

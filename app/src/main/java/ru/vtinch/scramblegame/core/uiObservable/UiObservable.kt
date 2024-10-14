package ru.vtinch.scramblegame.core.uiObservable

interface UiObservable<T : Any> : Update<T> {

    fun update(observer: UiObserver<T> = UiObserver.Empty())

    abstract class Single<T : Any> : UiObservable<T> {

        protected var cachedData: T? = null
        private var cachedObserver: UiObserver<T> = UiObserver.Empty()

        override fun update(observer: UiObserver<T>) {
            cachedObserver = observer
            if (cachedData != null) {
                cachedObserver.updateUi(cachedData!!)
                cachedData = null
            }
        }

        override fun updateUi(data: T) {
            if (cachedObserver.isEmpty())
                cachedData = data
            else
                cachedObserver.updateUi(data)
        }
    }

    class Base<T : Any> : Single<T>()
}
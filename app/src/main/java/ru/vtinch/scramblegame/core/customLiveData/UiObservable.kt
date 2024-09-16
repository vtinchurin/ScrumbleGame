package ru.vtinch.scramblegame.core.customLiveData

interface UiObservable<T : Any> : Update<T> {

    fun update(observer: UiObserver<T> = UiObserver.Empty())

    class Single<T : Any> : UiObservable<T> {

        private var cashedData: T? = null
        private var cashedObserver: UiObserver<T> = UiObserver.Empty()

        override fun update(observer: UiObserver<T>) {
            cashedObserver = observer
            if (cashedData != null) {
                cashedObserver.updateUi(cashedData!!)
                cashedData = null
            }
        }

        override fun updateUi(data: T) {
            if (cashedObserver.isEmpty())
                cashedData = data
            else
                cashedObserver.updateUi(data)
        }
    }
}
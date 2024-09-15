package ru.vtinch.scramblegame.di

import android.os.Handler
import android.os.Looper

interface MyViewModel {

    abstract class AbstractViewModel : MyViewModel {


        protected fun <T : Any> handleAsync(
            heavyOperation: () -> T,
            uiUpdate: (T) -> Unit
        ) {
            Thread {
                val result = heavyOperation.invoke()
                Handler(Looper.getMainLooper()).post {
                    uiUpdate.invoke(result)
                }
            }.start()


        }
    }
}
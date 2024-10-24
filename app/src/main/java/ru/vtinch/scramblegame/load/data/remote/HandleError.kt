package ru.vtinch.scramblegame.load.data.remote

import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.load.DomainException
import java.io.IOException


interface HandleError<T : Any> {

    fun handleError(e: Exception): T

    class Data : HandleError<Exception> {
        override fun handleError(e: Exception): Exception {
            return when (e) {
                is IOException -> DomainException.NoInternetConnectionException()
                else -> DomainException.ServiceUnavailable()
            }
        }
    }

    class Ui : HandleError<Int> {
        override fun handleError(e: Exception): Int {
            return when (e) {
                is DomainException.NoInternetConnectionException -> e.toResource()
                else -> R.string.service_unavailable
            }
        }
    }
}
package ru.vtinch.scramblegame.load

import android.util.Log
import kotlinx.coroutines.delay
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.cache.Cache
import ru.vtinch.scramblegame.load.data.local.CacheDataSource
import ru.vtinch.scramblegame.load.data.remote.CloudDataSource
import ru.vtinch.scramblegame.load.data.remote.HandleError

interface LoadRepository {

    suspend fun load()

    class Base(
        private val cloudDataSource: CloudDataSource,
        private val cacheDataSource: CacheDataSource.Save,
        private val index: Cache.Save<Int>,
        private val handleError: HandleError<Exception>
    ) : LoadRepository {

        init {
            Log.d("tvn", "Init LoadRepo")
        }

        override suspend fun load() {

            /**
             *              Old version without Retrofit2
             *             val connection = URL(url).openConnection() as HttpURLConnection
             *             try {
             *                 val data = connection.inputStream.bufferedReader().use { it.readText() }
             *                 val response = gson.fromJson(data, WordService::class.java)
             *                 stringCache.save(response.toSet())
             *                 return LoadResult.Success
             *             } catch (e: Exception) {
             *                 return LoadResult.Error
             *             } finally {
             *                 connection.disconnect()
             *             }
             */
            try {
                val data = cloudDataSource.getWords()
                cacheDataSource.save(data)
                index.save(0)
            } catch (e: Exception) {
                throw handleError.handleError(e)
            }


        }
    }

    class Test : LoadRepository {

        private var error = true

        override suspend fun load() {
            delay(1500)
            if (error) {
                error = false
                throw DomainException.NoInternetConnectionException()
            }
        }
    }
}

interface DomainException {
    fun toResource(): Int

    abstract class Abstract : Exception(), DomainException

    class NoInternetConnectionException : Abstract() {
        override fun toResource() = R.string.no_connection

    }

    class ServiceUnavailable : Abstract() {
        override fun toResource() = R.string.service_unavailable
    }
}
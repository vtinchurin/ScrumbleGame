package ru.vtinch.scramblegame.load

import kotlinx.coroutines.delay
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.cache.Cache
import ru.vtinch.scramblegame.load.data.local.CacheDataSource
import ru.vtinch.scramblegame.load.data.remote.CloudDataSource
import ru.vtinch.scramblegame.load.data.remote.HandleError

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

interface LoadRepository {

    suspend fun load()

    class Base(
        private val cloudDataSource: CloudDataSource,
        private val cacheDataSource: CacheDataSource.Save,
        private val index: Cache.Save<Int>,
        private val handleError: HandleError<Exception>,
    ) : LoadRepository {

        override suspend fun load() {
            try {
                val data = cloudDataSource.getWords()
                cacheDataSource.save(data)
                index.save(0)
            } catch (e: Exception) {
                throw handleError.handleError(e)
            }
        }
    }

    class Test(
        private val index: Cache.Save<Int>,
    ) : LoadRepository {

        private var error = true

        override suspend fun load() {
            delay(1500)
            if (error) {
                error = false
                throw DomainException.NoInternetConnectionException()
            } else index.save(0)
        }
    }
}

interface DomainException {
    fun toResource(): Int

    abstract class Abstract(
        private val resId: Int,
    ) : Exception(), DomainException {
        override fun toResource() = resId
    }

    class NoInternetConnectionException : Abstract(R.string.no_connection)

    class ServiceUnavailable : Abstract(R.string.service_unavailable)
}
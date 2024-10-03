package ru.vtinch.scramblegame.load

import kotlinx.coroutines.delay
import ru.vtinch.scramblegame.core.cache.Cache

interface LoadRepository {

    suspend fun load(): LoadResult

    class Base(
        private val service: WordService,
        private val stringCache: Cache.Mutable<Set<String>>,
    ) : LoadRepository {

        override suspend fun load(): LoadResult {

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
                val result = service.load().execute()
                if (result.isSuccessful) {
                    val response = result.body()?.words
                    stringCache.save(response!!.toSet())
                    return LoadResult.Success
                } else
                    return LoadResult.Error
            } catch (e: Exception) {
                return LoadResult.Error
            }
        }
    }

    class Test : LoadRepository {

        private var error = true

        override suspend fun load(): LoadResult {

            return if (error){
                error = false
                LoadResult.Error
            } else{
                delay(1500)
                LoadResult.Success
            }

        }
    }
}




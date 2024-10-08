package ru.vtinch.scramblegame.load

import android.util.Log
import kotlinx.coroutines.delay
import ru.vtinch.scramblegame.load.data.local.WordCache
import ru.vtinch.scramblegame.load.data.local.WordDao
import ru.vtinch.scramblegame.load.data.remote.WordService

interface LoadRepository {

    suspend fun load(): LoadResult

    class Base(
        private val service: WordService,
        private val dao: WordDao,
    ) : LoadRepository {

        init {
            Log.d("tvn", "Init LoadRepo")
        }

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
                    val list = response!!.mapIndexed { index, word ->
                        WordCache(index, word)
                    }
                    dao.addWords(list)
                    return LoadResult.Success
                } else
                    Log.d("tvn", "Empty Body")
                return LoadResult.Error
            } catch (e: Exception) {
                Log.d("tvn", e.message.toString())
                return LoadResult.Error
            }
        }
    }

    class Test : LoadRepository {

        private var error = true

        override suspend fun load(): LoadResult {

            return if (error) {
                error = false
                LoadResult.Error
            } else {
                delay(1500)
                LoadResult.Success
            }

        }
    }
}




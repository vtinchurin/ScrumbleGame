package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.core.cache.Cache

interface LoadRepository {

    fun load(): LoadResult

    class Base(
        private val service: WordService,
        private val stringCache: Cache.Mutable<Set<String>>,
    ) : LoadRepository {

        override fun load(): LoadResult {
//            val connection = URL(url).openConnection() as HttpURLConnection
//            try {
//                val data = connection.inputStream.bufferedReader().use { it.readText() }
//                val response = gson.fromJson(data, Response::class.java)
//                stringCache.save(response.toSet())
//                return LoadResult.Success
//            } catch (e: Exception) {
//                return LoadResult.Error
//            } finally {
//                connection.disconnect()
//            }
            try {
                val result = service.load().execute()
                if (result.isSuccessful) {
                    val response = result.body()?.toSet()
                    stringCache.save(response!!)
                    return LoadResult.Success
                } else
                    return LoadResult.Error
            } catch (e: Exception) {
                return LoadResult.Error
            }
        }
    }
}




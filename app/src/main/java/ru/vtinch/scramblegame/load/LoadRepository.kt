package ru.vtinch.scramblegame.load

import com.google.gson.Gson
import ru.vtinch.scramblegame.core.Cache
import java.net.HttpURLConnection
import java.net.URL

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)

    class Base(
        private val gson: Gson,
        private val stringCache: Cache.Mutable<Set<String>>,
    ) : LoadRepository {

        private val url: String = "https://random-word-api.vercel.app/api?words=4"
        override fun load(resultCallback: (LoadResult) -> Unit) {
            val connection = URL(url).openConnection() as HttpURLConnection
            try {
                val data = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(data, Response::class.java)
                stringCache.save(response.toSet())
                resultCallback.invoke(LoadResult.Success())
            } catch (e: Exception) {
                resultCallback.invoke(LoadResult.Error())
            } finally {
                connection.disconnect()
            }
        }
    }
}

class Response : ArrayList<String>()

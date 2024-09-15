package ru.vtinch.scramblegame.load

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URL

class LoadRepositoryTest {
    //https://random-word-api.herokuapp.com/word
    @Test
    fun test() {
        val url = "https://random-word-api.vercel.app/api?words=4"
        val connection = URL(url).openConnection() as HttpURLConnection

        try {
            val data = connection.inputStream.bufferedReader().use { it.readText() }
            assertTrue(data.isNotEmpty())

            val gson = Gson()
            val response = gson.fromJson(data, MyResponse::class.java)
            assertEquals(4, response.size)
            println(response.toSet())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
    }
}

private class MyResponse : ArrayList<String>()
package ru.vtinch.scramblegame.load

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordService {
    @GET("api")
    fun load(@Query("words") words:Int = 10): Call<WordListDTO>
}
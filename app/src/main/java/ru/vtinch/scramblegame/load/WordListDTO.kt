package ru.vtinch.scramblegame.load

import com.google.gson.annotations.SerializedName

data class WordListDTO(
    @SerializedName("data")
    val words: List<String>,
    @SerializedName("status")
    val status: Int
)
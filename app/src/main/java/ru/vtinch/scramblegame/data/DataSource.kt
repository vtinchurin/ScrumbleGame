package ru.vtinch.scramblegame.data

class DataSource {
    private val list = listOf("input", "world", "prediction", "snow", "horse","nevermore","dog")
    private var index = 0

    fun getData(): String{
        if (index>list.size-1) index = 0
        val currentIndex = index
        index++
        return list[currentIndex]
    }
}
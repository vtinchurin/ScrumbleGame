package ru.vtinch.scramblegame

interface Repository {

    fun getQuestion(): String
    fun getAnswer(): String
    fun next()

    class Base(): Repository{

        private val words = listOf("input","world","prediction","snow")
        private var index=0

        override fun getQuestion(): String {
            return words[index].reversed()
        }

        override fun getAnswer(): String {
            return words[index]
        }

        override fun next() {
            index++
            if (index == words.size)
                index = 0
        }

    }
}
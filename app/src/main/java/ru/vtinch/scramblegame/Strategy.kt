package ru.vtinch.scramblegame

interface Strategy {
    fun getQuestion(word: String): String
    class Game : Strategy {
        override fun getQuestion(word: String): String {
            return word.toCharArray().reversed().shuffled().joinToString("")
        }
    }

    class Test : Strategy {
        override fun getQuestion(word: String): String {
            return word.reversed()
        }
    }
}
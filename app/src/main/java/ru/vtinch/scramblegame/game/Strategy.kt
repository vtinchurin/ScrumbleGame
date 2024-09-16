package ru.vtinch.scramblegame.game

interface Strategy {
    fun getQuestion(word: String): String
    object Game : Strategy {
        override fun getQuestion(word: String): String {
            return word.toCharArray().reversed().shuffled().joinToString("")
        }
    }

    object Test : Strategy {
        override fun getQuestion(word: String): String {
            return word.reversed()
        }
    }
}
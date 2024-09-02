package ru.vtinch.scramblegame

import kotlin.contracts.contract

interface Repository {

    fun getQuestion(): String
    fun getAnswer(): String
    fun next()

    class Base(
        private val index: IntCache.Mutable,
        private val strategy: Strategy,
    ) : Repository {

        private var _index = index.restore()
        private val words = listOf("input", "world", "prediction", "snow","development")


        override fun getQuestion(): String {
            return strategy.getQuestion(words[_index])
        }

        override fun getAnswer(): String {
            return words[_index]
        }

        override fun next() {
            _index++
            if (_index == words.size)
                _index = 0
            index.save(_index)
        }

    }
}

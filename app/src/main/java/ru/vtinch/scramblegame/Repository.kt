package ru.vtinch.scramblegame

interface Repository {

    fun getQuestion(): String
    fun getAnswer(): String
    fun next()

    class Base(
        private val index: IntCache.Mutable
    ) : Repository {

        private var _index = index.restore()
        private val words = listOf("input","world","prediction","snow")


        override fun getQuestion(): String {
            return words[_index].reversed()
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